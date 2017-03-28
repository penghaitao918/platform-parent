#!/bin/bash

if [ -z "$1" ]; then
    echo "Usage: $0 server_name"
    echo "eg: $0 game"
    exit 1
fi

PROJECT_HOME=~/ffd_platform
SERVER_NAME=""

#确保server name正确，以免参数填写错误
for i in $(cat "${PROJECT_HOME}/service/bin/server_name.txt");do
    if [ ${i} = $1 ]; then
        SERVER_NAME="$1"
    fi
done
if [ -z "$SERVER_NAME" ]; then
    echo "ERROR: The $1 is not in the server_name.txt"
    exit 1
fi

LOG_NAME="${SERVER_NAME}-service"
MAIN_CLASS="com.alibaba.dubbo.container.Main"
JAR_NAME=`ls ${PROJECT_HOME}/service/*${SERVER_NAME}*.jar`
CLASSPATH="${PROJECT_HOME}/service/lib/*:${JAR_NAME}"

#64位堆内存最好设置为2g，业务繁忙时1g是不够用的
JAVA_MEM_OPTS=""
BITS=`java -version 2>&1 | grep -i 64-bit`
if [ -n "$BITS" ]; then
    JAVA_MEM_OPTS=" -server -Xmx1g -Xms1g -Xmn256m -XX:PermSize=128m -Xss256k -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 "
else
    JAVA_MEM_OPTS=" -server -Xms1g -Xmx1g -XX:PermSize=128m -XX:SurvivorRatio=2 -XX:+UseParallelGC "
fi

#检查是否已启动
PID=`ps -ef | grep java | grep "${LOG_NAME}" |awk '{print $2}'`
if [ -n "$PID" ]; then
    echo "ERROR: The $SERVER_NAME server already started!"
    echo "PID: $PID"
    exit 1
fi

echo "Starting $SERVER_NAME server ..."
java "-Dservice.log.name=${LOG_NAME}" -cp "${CLASSPATH}" "${MAIN_CLASS}" >/dev/null 2>&1 &

NEW_PID=`ps -ef | grep java | grep "${LOG_NAME}" | awk '{print $2}'`
if [ -n "$NEW_PID" ]; then
    echo "PID: $NEW_PID [OK]"
else
    echo "[Failed]"
fi
