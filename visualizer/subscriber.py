import os
from typing import List, Dict
import uuid
from confluent_kafka import Consumer
import json
import time

BOOTSTRAP_SERVERS = os.environ['BOOTSTRAP_SERVERS']
GROUP_ID = uuid.uuid4().hex

class Subscriber:
    def __init__(self, topics: List[str]):
        self.conf = {
            'bootstrap.servers': BOOTSTRAP_SERVERS,
            'group.id': GROUP_ID,
            'session.timeout.ms': 10_000,
        }

        self.consumer = Consumer(self.conf)
        print (f'Subscriber on {self.conf}')

        while True:
            available_topics = self.consumer.list_topics().topics.keys()
            if set(topics) <= set(available_topics):
                self.consumer.subscribe(topics)
                break
            time.sleep(1)
        print (f'Subscibed to {topics}')
    
    def tail_messages(self):
        while True:
            print ('Polling messages')
            messages = self.consumer.consume(50, 10)
            print(f"Received {len(messages)} messages")

            for msg in messages:

                topic = msg.topic()
                if msg.error():
                    print (f'Consumer error: {msg.error()}')
                else:
                    content = json.loads(msg.value().decode('utf-8'))
                    self.print_msg_info(topic, content)

    def print_msg_info(self, topic: str, content: Dict):
        playerIdx = content['header']['playerCarIndex']
        info = {
                'topic': topic,
                'frame': content['header']['frameIdentifier'],
                'time': content['header']['sessionTime'],
                'playerIdx': playerIdx,
            }

        if topic == 'f1telemetry.car_telemetry':
            info['speed'] = content['carTelemetryData'][playerIdx]['speed']
            info['throttle'] = content['carTelemetryData'][playerIdx]['throttle']
            info['brake'] = content['carTelemetryData'][playerIdx]['brake']
            print(info)
        elif topic == 'f1telemetry.lap_data':
            info['lapDistance'] = content['lapData'][playerIdx]['lapDistance']
            print(info)
        else:
            print ('Unknown message type')
