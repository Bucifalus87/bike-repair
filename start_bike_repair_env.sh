#!/bin/bash

echo "Starting Mobile Bike Repair containers..."

podman start bike_postgres
podman start zookeeper_bike_repair
podman start kafka_bike_repair

echo "All containers started:"

podman ps

