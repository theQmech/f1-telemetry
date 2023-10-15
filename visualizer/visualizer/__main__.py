from .singletons import app, car_telemetry_subscriber, lap_data_subscriber
from .view import create_layout

app.layout = create_layout()

car_telemetry_subscriber.start()
lap_data_subscriber.start()
app.run(host="0.0.0.0", port="8050", debug=True)
