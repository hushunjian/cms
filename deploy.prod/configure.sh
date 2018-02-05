#!/usr/bin/env bash
BASE_DIR=$(cd -P -- "$(dirname -- "$0")"; pwd -P)
cd $BASE_DIR
if [ -d apache-tomcat-8.0.26 ]; then 
    rm -rf apache-tomcat-8.0.26
fi
tar -xf ../repository/apache-tomcat-8.0.26.tar.gz
ln -nsf apache-tomcat-8.0.26 tomcat
\cp server.xml tomcat/conf
\cp catalina.sh tomcat/bin
cd -
