insert into tmp_latest_tag_image(tag_id, topic_image_id)
select
tt.id as tag_id,
max(ti.id) as topic_image_id
from topic_tag tt, topic_tag_detail ttd, topic_image ti
where 1=1
and tt.id = ttd.tag_id
and ttd.topic_id = ti.topic_id
group by
tt.id

insert into tmp_latest_tag_image(tag_id, topic_image_id)
select
tt.id as tag_id,
max(ti.id) as topic_image_id
from topic_tag tt join topic_tag_detail ttd on (
tt.id = ttd.tag_id
) join topic_image ti on (
ttd.topic_id = ti.topic_id
)
left join tmp_latest_tag_image tlti on (
tt.id = tlti.tag_id
and ti.id = tlti.topic_image_id
)
where 1=1
and tlti.topic_image_id is null
group by
tt.id

select
t1.tag_id,
t3.tag,
t2.fid,
t2.image,
t0.fragment,
t0.type,
t0.content_type,
t2.extra,
t2.topic_id,
t2.create_time
from
tmp_topic_exhibition t0,
topic_tag_detail t1,
topic_image t2,
topic_tag t3,
topic tp
where t1.status = 0
and t0.tag_id = t3.id
and t0.topic_id = t2.topic_id
and t0.topic_image_id = t2.id
and t2.topic_id=tp.id and tp.rights!=2
and t1.topic_id =t2.topic_id
and t1.tag_id = t3.id