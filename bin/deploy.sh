#!/bin/bash
PROJECT_HOME=~/ffd_platform
PACKAGE_HOME="${PROJECT_HOME}/package"
VERSION=`cat ${PACKAGE_HOME}/version`
if [ ! -d ${PACKAGE_HOME}/${VERSION} ]; then
    echo "the directory ${VERSION} isn't exist !"
    exit 1
fi

echo "Begin to deploy the version ${VERSION} ... "
echo "Stop all servers ..."
${PROJECT_HOME}/service/bin/stopall.sh
sleep 1
${PROJECT_HOME}/service/bin/stopTomcat.sh platform
sleep 1
${PROJECT_HOME}/service/bin/stopTomcat.sh admin
sleep 1
echo "Stop [OK]"

echo "Backup the current version ..."
[ -d "${PACKAGE_HOME}/backup" ] || mkdir -p ${PACKAGE_HOME}/backup
rm -rf ${PACKAGE_HOME}/backup/*
mv ${PROJECT_HOME}/service/* ${PACKAGE_HOME}/backup/
mv ${PROJECT_HOME}/webserv/tomcat.platform/webapps/* ${PACKAGE_HOME}/backup/
mv ${PROJECT_HOME}/webserv/tomcat.admin/webapps/* ${PACKAGE_HOME}/backup/
echo "Backup [OK]"

echo "Deploy the new version ${VERSION} ..."
cp ${PACKAGE_HOME}/${VERSION}/admin*.war ${PROJECT_HOME}/webserv/tomcat.admin/webapps/admin.war
cp ${PACKAGE_HOME}/${VERSION}/platform*.war ${PROJECT_HOME}/webserv/tomcat.platform/webapps/platform.war
tar -zxvf ${PACKAGE_HOME}/${VERSION}/platform-parent-platform-server.tar.gz -C ${PROJECT_HOME}/service
chmod +x ${PROJECT_HOME}/service/bin/*.sh
echo "Deploy [OK]"

echo "Start all servers ..."
${PROJECT_HOME}/service/bin/startall.sh
sleep 1
${PROJECT_HOME}/webserv/tomcat.platform/bin/startup.sh
sleep 1
${PROJECT_HOME}/webserv/tomcat.admin/bin/startup.sh
echo "Start [OK]"
echo "End to deploy the version ${VERSION} ..."