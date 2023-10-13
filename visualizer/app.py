from dash import Dash, html, dcc, callback, Output, Input
import plotly.express as px
import pandas as pd
from subscriber import Subscriber

df = pd.read_csv('https://raw.githubusercontent.com/plotly/datasets/master/gapminder_unfiltered.csv')

app = Dash(__name__)

app.layout = html.Div([
    html.H1(children='Title of Dash App', style={'textAlign':'center'}),
    dcc.Dropdown(df.country.unique(), 'Canada', id='dropdown-selection'),
    dcc.Graph(id='graph-content')
])

@callback(
    Output('graph-content', 'figure'),
    Input('dropdown-selection', 'value')
)
def update_graph(value):
    dff = df[df.country==value]
    return px.line(dff, x='year', y='pop')

if __name__ == '__main__':
    subscriber = Subscriber(['f1telemetry.car_telemetry', 'f1telemetry.lap_data'])
    subscriber.tail_messages()
    # app.run(debug=True)
