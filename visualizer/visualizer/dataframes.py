from typing import Dict, List
import pandas as pd

from visualizer.subscriber import Subscriber


class MainDataframe:
    def __init__(self, subscriber: Subscriber):
        self.subsciber = subscriber
        self.dfs = {}

    def get_dataframe(self, topic: str):
        self.update_data()
        if topic not in self.dfs.keys():
            return None
        return self.dfs[topic]

    def update_data(self):
        msgs = self.subsciber.get_messages_brief(100, 2)
        topics = set(msg["topic"] for msg in msgs)
        for t in topics:
            self.add_messages(t, [x for x in msgs if x["topic"] == t])

    def add_messages(self, topic: str, messages: List[Dict]):
        curr_row = pd.DataFrame(data=messages)

        if topic not in self.dfs.keys():
            self.dfs[topic] = curr_row
        else:
            self.dfs[topic] = pd.concat(
                [self.dfs[topic], curr_row],
                axis=0,
            )
