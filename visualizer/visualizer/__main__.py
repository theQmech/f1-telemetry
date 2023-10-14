from .singletons import app, subscriber
from .view import create_layout

app.layout = create_layout()

subscriber.start()
app.run(host="0.0.0.0", port="8050", debug=True)
