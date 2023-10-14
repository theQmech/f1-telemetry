from .subscriber import Subscriber
from .dataframes import MainDataframe
from dash import Dash

subscriber = Subscriber(["f1telemetry.car_telemetry", "f1telemetry.lap_data"])
main_dataframe = MainDataframe(subscriber)
app = Dash("Time Trial lap performance")
