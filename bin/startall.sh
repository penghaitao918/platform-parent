#!/bin/bash

PROJECT_HOME=~/ffd_platform

for i in $(cat "${PROJECT_HOME}/service/bin/server_name.txt"); do
    ${PROJECT_HOME}/service/bin/start.sh ${i}
    sleep 1
done