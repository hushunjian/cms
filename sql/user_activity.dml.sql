INSERT INTO tmp_daily_active_user (uid,url,access_time,access_interval)
SELECT 
A.uid,
A.request_uri AS url,
A.create_time AS access_time,
TIMESTAMPDIFF(SECOND,A.create_time,B.create_time) AS access_interval
FROM(
select a.*,(@i := @i + 1) as ord_num from app_http_access a,(select @i := 1) d 
WHERE create_time >='2018-1-3 00:00:00' AND create_time <='2018-1-3 23:59:59' order by uid,create_time
) AS A 
LEFT JOIN(
select a.*,(@j := @j + 1) as ord_num from app_http_access a,(select @j := 0) d 
WHERE create_time >='2018-1-3 00:00:00' AND create_time <='2018-1-3 23:59:59' order by uid,create_time
) AS B 
ON
A.ord_num = B.ord_num 
AND A.uid = B.uid
WHERE A.uid !=0

INSERT INTO daily_active_user (uid,feature_id,access_date,access_hour,access_count,access_sum_time)
SELECT 
t1.uid,
t2.feature_id,
DATE_FORMAT(t1.access_time,'%Y-%m-%d') AS access_date,
DATE_FORMAT(t1.access_time,'%H') AS access_hour,
count(feature_id) AS access_count,
sum(CASE WHEN t1.access_interval >1800 THEN 0 ELSE t1.access_interval END) AS access_sum_time
FROM 
tmp_daily_active_user t1,
app_url t2 
where
t1.url = t2.url
AND t1.access_time >='2018-1-3 00:00:00'
AND t1.access_time <='2018-1-3 23:59:59'
GROUP BY uid,feature_id,access_date,access_hour
ORDER BY uid,feature_id