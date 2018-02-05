#!/usr/bin/env bash
BASE_DIR=$(cd -P -- "$(dirname -- "$0")" && cd ..; pwd -P)
cd $BASE_DIR
    git pull
    mvn -Dmaven.test.skip=true -Pprod clean package
    if [ -d $BASE_DIR/deploy.prod/tomcat/webapps/cms ]; then
        rm -rf $BASE_DIR/deploy.prod/tomcat/webapps/cms
    fi
    if [ -d $BASE_DIR/deploy.prod/tomcat/webapps/cms.war ]; then
        rm -rf $BASE_DIR/deploy.prod/tomcat/webapps/cms.war
    fi
    ls target/cms-1.0.war
    \cp target/cms-1.0.war $BASE_DIR/deploy.prod/tomcat/webapps/cms.war
cd -
