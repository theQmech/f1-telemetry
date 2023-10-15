import os
from typing import List, Dict
import uuid
from confluent_kafka import Consumer
import json
import time

BOOTSTRAP_SERVERS = os.environ["BOOTSTRAP_SERVERS"]
GROUP_ID = uuid.uuid4().hex


class Subscriber:
    def __init__(self, name: str, topic: str):
        self.name = name
        self.conf = {
            "bootstrap.servers": BOOTSTRAP_SERVERS,
            "group.id": GROUP_ID,
            "session.timeout.ms": 10_000,
        }
        self.topic = topic
        self.retry_interval = 5
        self._consumer: Consumer = None
        self._fetched = []

    def start(self):
        self._consumer = Consumer(self.conf)
        print(f"Subscriber {self.name}: connected with conf {self.conf}")

        while self.topic not in self._consumer.list_topics().topics.keys():
            print(
                f"Subscriber {self.name}: topic {self.topic} not ready. Retrying in {self.retry_interval}s"
            )
            time.sleep(self.retry_interval)
        self._consumer.subscribe([self.topic])

        print(f"Subscriber {self.name}: subscibed to {self.topic}")

        # Prefetch to overcome initial burst of demand
        self.__pull_messages(10, 1.0)

    def get_next_messages(self, num_messages: int, timeout: float) -> List[Dict]:
        while len(self._fetched) < num_messages:
            self.__pull_messages(10 * num_messages, timeout)

        results = self._fetched[:num_messages]
        self._fetched = self._fetched[num_messages:]

        return results

    def __pull_messages(self, num_messages: int, timeout: float):
        if self._consumer is None:
            print(f"Subscriber {self.name}: subscriber not yet ready")

        print(f"Subscriber {self.name}: Polling messages")
        messages = self._consumer.consume(num_messages, timeout)
        print(f"Subscriber {self.name}: Received {len(messages)} messages")
        self._fetched += self.__process_messages(messages)

    def __process_messages(self, messages) -> Dict:
        result = []
        for msg in messages:
            if msg.error():
                print(f"Subscriber {self.name}: message error {msg.error()}")
            else:
                content = json.loads(msg.value().decode("utf-8"))
                content["topic"] = msg.topic()
                result.append(content)
        return result
