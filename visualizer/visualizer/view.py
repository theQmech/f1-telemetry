from .singletons import app, main_dataframe
from dash import html, dcc
from dash.dependencies import Input, Output
import plotly.express as px


def create_layout():
    return html.Div(
        [
            html.H1(
                children="Car Telemetry vs LapProgress", style={"textAlign": "center"}
            ),
            dcc.Graph(id="graph-content"),
            dcc.Interval(
                id="interval-component",
                interval=5 * 1000,  # in milliseconds
                n_intervals=0,
            ),
            # dcc.Store stores the intermediate value
            dcc.Store(id="intermediate-value"),
        ]
    )


# Multiple components can update everytime interval gets fired.
@app.callback(
    Output("graph-content", "figure"), Input("interval-component", "n_intervals")
)
def update_graph_live(n):
    print("tick", n, flush=True)
    df = main_dataframe.get_dataframe("f1telemetry.car_telemetry")
    print(df, flush=True)

    fig = px.line(df, x="time", y="speed")

    return fig
