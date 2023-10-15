from .subscriber import Subscriber
from .dataframes import MainDataframe
from dash import Dash

car_telemetry_subscriber = Subscriber("car_telemetry", "f1telemetry.car_telemetry")
lap_data_subscriber = Subscriber("lap_data", "f1telemetry.lap_data")
main_dataframe = MainDataframe(car_telemetry_subscriber, lap_data_subscriber)
app = Dash("Time Trial lap performance")
