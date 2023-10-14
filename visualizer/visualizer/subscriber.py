import os
from typing import List, Dict
import uuid
from confluent_kafka import Consumer
import json
import time

BOOTSTRAP_SERVERS = os.environ["BOOTSTRAP_SERVERS"]
GROUP_ID = uuid.uuid4().hex


class Subscriber:
    def __init__(self, topics: List[str]):
        self.conf = {
            "bootstrap.servers": BOOTSTRAP_SERVERS,
            "group.id": GROUP_ID,
            "session.timeout.ms": 10_000,
        }
        self.topics = topics
        self.retry_interval = 5
        self.consumer: Consumer = None

    def start(self):
        self.consumer = Consumer(self.conf)
        print(f"Subscriber on {self.conf}")

        while True:
            available_topics = self.consumer.list_topics().topics.keys()
            if set(self.topics) <= set(available_topics):
                self.consumer.subscribe(self.topics)
                break
            time.sleep(self.retry_interval)
            print(f"Required topics not ready. Retrying in {self.retry_interval}s")
        print(f"Subscibed to {self.topics}")

    def get_messages_brief(self, num_messages: int, timeout: float) -> List[Dict]:
        if self.consumer is None:
            print("Subscriber not started yet")
            return []

        results = []
        for msg in self.get_messages(num_messages, timeout):
            topic = msg.topic()
            if msg.error():
                print(f"Consumer error: {msg.error()}")
            else:
                content = json.loads(msg.value().decode("utf-8"))
                results.append(self.extract_message_info(topic, content))

        return results

    def get_messages(self, num_messages: int, timeout: float):
        print("Polling messages")
        results = self.consumer.consume(num_messages, timeout)
        print(f"Received {len(results)} messages")
        return results

    def extract_message_info(self, topic: str, content: Dict):
        playerIdx = content["header"]["playerCarIndex"]
        info = {
            "topic": topic,
            "frame": content["header"]["frameIdentifier"],
            "time": content["header"]["sessionTime"],
            "playerIdx": playerIdx,
        }

        if topic == "f1telemetry.car_telemetry":
            info["speed"] = content["carTelemetryData"][playerIdx]["speed"]
            info["throttle"] = content["carTelemetryData"][playerIdx]["throttle"]
            info["brake"] = content["carTelemetryData"][playerIdx]["brake"]
        elif topic == "f1telemetry.lap_data":
            info["lapDistance"] = content["lapData"][playerIdx]["lapDistance"]
        else:
            info["error"] = "Unknown message type"

        return info
