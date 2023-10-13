#!/bin/bash

sink_host=${SINK_HOST:?"sink host not defined"}
sink_port=${SINK_PORT:?"sink port not defined"}
wait_time=${WAIT_TIME:-"5"}

echo "Script params" $sink_host $sink_port $wait_time

for i in $(seq 0 14000); do
  fname="/packet_dump/""$i"".bin"
  nc -u -q"$wait_time" "$sink_host" "$sink_port" < "$fname"
  echo "Sent " "$fname"
done