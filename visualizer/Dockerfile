FROM python:3.10

ADD requirements.txt ./

RUN pip install -r requirements.txt

ADD *.py ./

CMD ["python", "-u", "app.py"]
