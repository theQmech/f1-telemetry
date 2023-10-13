import os
import uuid
from confluent_kafka import Consumer
import json

BOOTSTRAP_SERVERS = os.environ['BOOTSTRAP_SERVERS']
GROUP_ID = uuid.uuid4().hex

class Subscriber:
    def __init__(self, topic):
        self.topic = topic

        print ("broker", BOOTSTRAP_SERVERS)

        self.conf = {
            'bootstrap.servers': BOOTSTRAP_SERVERS,
            'group.id': GROUP_ID,
            'session.timeout.ms': 10_000,
        }

        self.consumer = Consumer(self.conf)
        self.consumer.subscribe([topic])

        print ('Consumer connected')
    
    def tail_messages(self):
        while True:
            print ('Polling messages')
            msg = self.consumer.poll(10_000)

            print(msg.key(), msg.topic(), msg.value())
