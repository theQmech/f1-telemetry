#!/bin/bash

sink_host=${SINK_HOST:?"sink host not defined"}
sink_port=${SINK_PORT:?"sink port not defined"}
wait_time=${WAIT_TIME:-"5"}

echo "Script params" $sink_host $sink_port $wait_time

for fname in /packet_dump/*.bin; do
  cat "$fname" | nc -u -q"$wait_time" "$sink_host" "$sink_port"
  echo "Sent " "$fname"
done