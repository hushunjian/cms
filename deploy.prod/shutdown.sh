#!/usr/bin/env bash
ssh m2m@jetty-1 "export JAVA_HOME=/usr/local/jdk1.8.0_121 && sh /data/cms/tomcat/bin/shutdown.sh"
