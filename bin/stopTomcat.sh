#!/bin/bash
if [ -z "$1" ]; then
    echo "Usage: $0 web_name"
    echo "eg: $0 admin"
    exit 1
fi

WEB_HOME=~/ffd_platform/webserv
TOMCAT_NAME="tomcat.$1"

echo "Stopping the ${TOMCAT_NAME} ..."
${WEB_HOME}/${TOMCAT_NAME}/bin/shutdown.sh
sleep 5
PID=`ps -ef | grep java | grep ${TOMCAT_NAME} | awk '{print $2}'`
if [ -z "$PID" ]; then
    echo "[OK]"
else
    echo "killing ..."
    kill -9 ${PID}
    echo "[OK]"
fi
