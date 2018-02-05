drop table if exists  tmp_recent_tag;
drop table if exists  tmp_tag_topic_count;
drop table if exists  tmp_tag_user_count;
drop table if exists  tmp_tag_in_home;

CREATE TABLE tmp_recent_tag (
  id bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (id)
) ENGINE=MEMORY DEFAULT CHARSET=utf8mb4 comment='临时表-最近更新的标签';

CREATE TABLE tmp_tag_topic_count (
  id bigint(20) NOT NULL DEFAULT '0',
  topic_count bigint(20),
  PRIMARY KEY (id)
) ENGINE=MEMORY DEFAULT CHARSET=utf8mb4 comment='临时表-标签的王国数';

CREATE TABLE tmp_tag_user_count (
  id bigint(20) NOT NULL DEFAULT '0',
  user_count bigint(20),
  PRIMARY KEY (id)
) ENGINE=MEMORY DEFAULT CHARSET=utf8mb4 comment='临时表-标签的用户数';

CREATE TABLE tmp_tag_in_home (
  id bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  tag_id bigint(20) NOT NULL DEFAULT '0',
  tag_name varchar(200) DEFAULT NULL,
  is_sys int(11) NOT NULL DEFAULT '0',
  cover_img varchar(200) DEFAULT NULL,
  topic_count bigint(20) NOT NULL DEFAULT '0',
  user_count bigint(20) NOT NULL DEFAULT '0',
  created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=MEMORY AUTO_INCREMENT=26612 DEFAULT CHARSET=utf8mb4 comment='临时表-首页标签';
alter table tmp_tag_in_home add index idx_tag_id_and_is_sys(tag_id, is_sys);
