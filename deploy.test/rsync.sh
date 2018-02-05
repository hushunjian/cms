#!/usr/bin/env bash
ssh m2m@jetty-t-1 "mkdir -p /data/cms"
rsync --delete -lurv apache-tomcat-8.0.26 m2m@jetty-t-1:/data/cms
ssh m2m@jetty-t-1 "cd /data/cms; ln -nsf apache-tomcat-8.0.26 tomcat"
