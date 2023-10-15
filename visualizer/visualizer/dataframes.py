from typing import Dict, List
import pandas as pd

from visualizer.subscriber import Subscriber


class MainDataframe:
    def __init__(
        self, car_telemetry_subscriber: Subscriber, lap_data_subscriber: Subscriber
    ):
        self.car_telemetry_subscriber = car_telemetry_subscriber
        self.lap_data_subscriber = lap_data_subscriber
        self.last_frame = 0
        self.dfs = {}

        self.lap_dists = []
        self.speeds = []

    def get_updated_data(self, n: int):
        for _ in range(n):
            a, b = self.get_next_data_point()
            self.lap_dists.append(a)
            self.speeds.append(b)

        return self.lap_dists, self.speeds

    def get_next_data_point(self):
        lap_data = self.lap_data_subscriber.get_next_messages(1, 1.0)[0]
        lp_frame = lap_data["header"]["frameIdentifier"]
        car_telemetry_data = self.car_telemetry_subscriber.get_next_messages(1, 1.0)[0]
        ct_frame = car_telemetry_data["header"]["frameIdentifier"]
        while abs(lp_frame - ct_frame) >= 10:
            if lp_frame < ct_frame:
                lap_data = self.lap_data_subscriber.get_next_messages(1, 1.0)[0]
                lp_frame = lap_data["header"]["frameIdentifier"]
            else:
                car_telemetry_data = self.car_telemetry_subscriber.get_next_messages(
                    1, 1.0
                )[0]
                ct_frame = car_telemetry_data["header"]["frameIdentifier"]

        carIdx = lap_data["timeTrialPBCarIdx"]
        rivalCarIdx = lap_data["timeTrialRivalCarIdx"]
        lap_distance = lap_data["lapData"][carIdx]["lapDistance"]
        lp_frame = lap_data["header"]["frameIdentifier"]
        self.last_frame = lp_frame

        car_telemetry_data = self.car_telemetry_subscriber.get_next_messages(1, 1.0)[0]
        ct_frame = car_telemetry_data["header"]["frameIdentifier"]
        speed = car_telemetry_data["carTelemetryData"][carIdx]["speed"]
        throttle = car_telemetry_data["carTelemetryData"][carIdx]["throttle"]
        brake = car_telemetry_data["carTelemetryData"][carIdx]["brake"]
        return (lap_distance, speed)

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
            self.dfs[topic] = pd.concat([self.dfs[topic], curr_row], axis=0)
