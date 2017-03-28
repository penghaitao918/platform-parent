#!/bin/bash
PID=`ps -ef | grep java | grep "$1-service" |awk '{print $2}'`
if [ -z "$PID" ]; then
    echo "ERROR: The $1 server does not started!"
    exit 1
fi
echo "Stopping the $1 server ..."
kill -9 ${PID}
if [ $? -eq 0 ]; then
    echo "[OK]"
else
    echo "[Failed]"
fi