### maven
mvn idea:clean -Dmaven.test.skip=true -DsocksProxyHost=127.0.0.1 -DsocksProxyPort=7777
mvn idea:idea -Dmaven.test.skip=true -DsocksProxyHost=127.0.0.1 -DsocksProxyPort=7777
mvn package -Dmaven.test.skip=true -DsocksProxyHost=127.0.0.1 -DsocksProxyPort=7777

#在lb或lb-t上需要建立socket5动态代理接口,用来做邮件转发
ssh -p 22 -N -f -g -D 0.0.0.0:5555 m2m@127.0.0.1

#在lb或lb-t上需要建立HTTP和HTTPS代理服务
systemctl start squid

#CMS需求，实时显示用户的最近操作时间，将数据放在Redis中，方便查询


本项目所属公司已解散，在项目经理及公司的许可下带出学习，不涉及机密。
