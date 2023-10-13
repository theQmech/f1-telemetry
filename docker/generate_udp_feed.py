import os
import socket
import time

sink_host = str(os.environ["SINK_HOST"])
sink_port = int(os.environ["SINK_PORT"])
wait_ms = float(os.environ["WAIT_MS"])

for i in range(14_000):
  sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
  fname = f"/packet_dump/{i}.bin"
  with open(fname, 'rb') as f:
    raw_data = f.read()
    sock.sendto(raw_data, (sink_host, sink_port))
    print(f"Sent file {fname} {len(raw_data)}B to {sink_host}:{sink_port}")
    time.sleep(wait_ms / 1000)
