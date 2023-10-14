FROM python:3.10

ADD requirements.txt ./

RUN pip install -r requirements.txt

ADD visualizer ./visualizer

CMD ["python", "-u", "-m", "visualizer"]
