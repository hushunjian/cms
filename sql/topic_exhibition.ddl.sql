drop table if exists tmp_latest_tag_image;
drop table if exists tmp_topic_exhibition;

CREATE TABLE tmp_latest_tag_image (
  id bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  tag_id bigint(20) NOT NULL DEFAULT '0',
  topic_image_id bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (id)
) ENGINE=MEMORY DEFAULT CHARSET=utf8mb4 comment='临时表-最新王国图片';
alter table tmp_latest_tag_image add index idx_tag_id_and_image_id (tag_id, topic_image_id);

CREATE TABLE tmp_topic_exhibition (
  id bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  tag_id bigint(20) NOT NULL DEFAULT '0',
  topic_id bigint(20) NOT NULL DEFAULT '0',
  topic_image_id bigint(20) NOT NULL DEFAULT '0',
  type int(10) NOT NULL DEFAULT '0',
  content_type int(11) NOT NULL,
  fragment text,
  created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment='临时表-最新王国外露信息';

alter table tmp_topic_exhibition add index idx_tag_id_and_image_id (tag_id, topic_id, topic_image_id);
alter table tmp_topic_exhibition add index idx_created_at (created_at);
