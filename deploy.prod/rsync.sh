ssh m2m@jetty-1 "mkdir -p /data/cms"
rsync -lurv apache-tomcat-8.0.26 m2m@jetty-1:/data/cms
ssh m2m@jetty-1 "cd /data/cms; ln -nsf apache-tomcat-8.0.26 tomcat"
