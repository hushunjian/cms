drop table if exists tmp_daily_active_user;
drop table if exists daily_active_user;
drop table if exists app_feature;
drop table if exists app_url;

CREATE TABLE `tmp_daily_active_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户日活表主键id',
  `uid` bigint(20) NOT NULL DEFAULT '-1' COMMENT '用户uid',
  `url` varchar(60) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '访问url',
  `access_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '访问时间',
  `access_interval` bigint(20) DEFAULT '0' COMMENT '访问间隔',
  PRIMARY KEY (`id`),
  KEY `idx_access_time` (`access_time`) USING BTREE,
  KEY `idx_access_interval` (`access_interval`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户日活统计表';

CREATE TABLE `daily_active_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日活统计表主键id',
  `uid` bigint(20) NOT NULL COMMENT '用户uid',
  `feature_id` bigint(20) DEFAULT NULL COMMENT '对应功能页id',
  `access_date` date NOT NULL COMMENT ' 访问时间-日期',
  `access_hour` int(2) NOT NULL DEFAULT '0' COMMENT '访问时间-小时',
  `access_count` bigint(10) NOT NULL COMMENT '访问总次数',
  `access_sum_time` bigint(20) DEFAULT NULL COMMENT '访问总时长',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_access_date` (`access_date`) USING BTREE,
  KEY `idx_access_hour` (`access_hour`),
  KEY `idx_access_count` (`access_count`),
  KEY `idx_access_sum_time` (`access_sum_time`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户日活统计结果表';


CREATE TABLE `app_feature` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'app页面主键id',
  `feature_name` varchar(60) NOT NULL COMMENT 'app页面名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='app页面功能表';

CREATE TABLE `app_url` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'url所属页面主键id',
  `url` varchar(60) NOT NULL COMMENT 'url',
  `feature_id` int(10) NOT NULL COMMENT '对应app页面id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COMMENT='app访问url表';
