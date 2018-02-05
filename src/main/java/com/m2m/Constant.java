package com.m2m;


public class Constant {
    public static final String REGEX_PASSWORD = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[`\\-=\\[\\]\\\\;',./~!@#$%^&*()_+{}|:\"<>?])[A-Za-z0-9`\\-=\\[\\]\\\\;',./~!@#$%^&*()_+{}|:\"<>?]{8,20}$";
    //分隔符_下划线
    public static final String KEY_SEPARATOR_UNDERLINE = "_";
    //特殊活动王国key
    public static final String ACTIVITY_CORECIRCLE_SPECIAL_TOPIC_LIST_KEY = "ACTIVITY_CORECIRCLE_SPECIAL_TOPIC_LIST";
    //活动王国开关
    public static final String ACTIVITY_SPECIAL_TOPIC_HANDLE_KEY = "ACTIVITY_SPECIAL_TOPIC_HANDLE";
    //特殊王国相关配置前缀
    public static final String SPECIAL_TOPIC_KEY_PRE = "SPECIAL_TOPIC_";
    //特殊王国类型_文本
    public static final String SPECIAL_TOPIC_TYPE_TEXT = "TEXT";
    //特殊王国类型_图片
    public static final String SPECIAL_TOPIC_TYPE_IMAGE = "IMAGE";
    //特殊王国类型_视频
    public static final String SPECIAL_TOPIC_TYPE_VIDEO = "VIDEO";
    //特殊王国类型_音频
    public static final String SPECIAL_TOPIC_TYPE_AUDIO = "AUDIO";
    //特殊王国类型
    public static final String SPECIAL_TOPIC_TYPE_AT = "AT";
    //特殊王国类型_分享
    public static final String SPECIAL_TOPIC_TYPE_SHARE = "SHARE";
    //特殊王国类型_王国对外分享
    public static final String SPECIAL_TOPIC_TYPE_OUT_SHARE = "OUT_SHARE";
    //新增
    public static final String SPECIAL_TOPIC_ACTION_ADD = "ADD";
    //删除
    public static final String SPECIAL_TOPIC_ACTION_DEL = "DEL";
    public static final String SPECIAL_TOPIC_PERSON = "PERSON_";
    public static final String SPECIAL_TOPIC_KINGDOM = "KINGDOM_";
    //特殊王国操作
    public static final String SPECIAL_TOPIC_LIMIT = "KINGDOM_LIMIT";
    public static final String SPECIAL_TOPIC_USER_LIMIT_PRE = "SPECIAL_TOPIC_USER_LIMIT_";
    public static final String SPECIAL_TOPIC_HOT_LIMIT_DAY = "SPECIAL_TOPIC_HOT_LIMIT_DAY";
    public static final String SPECIAL_TOPIC_HOT_LIMIT_TOTAL = "SPECIAL_TOPIC_HOT_LIMIT_TOTAL";
    public static final String SPECIAL_TOPIC_HOT_DAY_PRE = "SPECIAL_TOPIC_HOT_DAY_";
    public static final String APP_CONFIG_KEY_PRE = "APP_CONFIG:";
    public static final String USER_REC_INIT_LIST_KEY_PRE = "USER_REC_INIT_LIST:";
    public static final String USER_REC_DAY_LIST_KEY_PRE = "USER_REC_DAY_LIST:";
    public static final String USER_REC_DATE_KEY = "USER_REC_DATE";
    public static final String CACHE_LOCK_PRE = "LOCK:";
    public static final String HUAWEI_ACCESSTOKEN_KEY = "HUAWEI_ACCESSTOKEN";
    public static Integer PRIVILEGE_USER = 1;
    public static Integer PRIVILEGE_ROLE = 2;
    public static Integer PRIVILEGE_FEATURE = 3;
    public static Integer PRIVILEGE_USER_FEATURE = 4;
    //七牛图片地址前缀
    public static final String QINIU_DOMAIN = "https://cdn.me-to-me.com";
    //榜单
    public static final String BILLBOARD_KEY_TARGET1 = "_target1";
    public static final String BILLBOARD_KEY_TARGET2 = "_target2";
    public static final String BILLBOARD_KEY_POPULAR_PEOPLE = "popular_people";
    public static final String BILLBOARD_KEY_JAY_PEOPLE = "jay_people";
    public static final String BILLBOARD_KEY_COLOURFUL_KINGDOM = "colourful_kingdom";
    public static final String BILLBOARD_KEY_LONELY_KINGDOM = "lonely_kingdom";
    //榜单模式
    //最活跃的米汤新鲜人
    public static final int MOSTACTIVE_USER = 1;
    //最受追捧的米汤大咖
    public static final int MOSTPOPULAR_USER = 2;
    //最爱叨逼叨的话痨国王
    public static final int TALKATIVE_KINGDOM = 3;
    //这里的互动最热闹
    public static final int HOT_INTERACION = 4;
    //最丰富多彩的王国
    public static final int COLORFUL_TOPIC = 5;
    //求安慰的孤独王国
    public static final int ALONE_TOPIC = 6;
    //最新更新的王国
    public static final int LATEST_UPDATE_KINGDOM = 7;
    //新注册的帅哥
    public static final int NEW_REGISTERED_GUY = 8;
    //新注册的美女
    public static final int NEW_REGISTERED_GIRL = 9;
    //新注册的用户
    public static final int NEW_REGISTERED_USER = 10;
    //炙手可热的米汤红人
    public static final int HOT_RICE_METOME_USER = 11;
    //王国价值最高
    public static final int MOST_VALUABLE_KINGDOM = 12;
    //王国价值增长最快
    public static final int FAST_INCR_VALUABLE_KINGDOM = 13;
    //标签[运动的时候最性感]王国价值最高
    public static final int MOST_VALUABLE_KINGDOM_OF_EXERCISE_SEX = 14;
    //标签[运动的时候最性感]王国价值增长最快
    public static final int FAST_INCR_VALUABLE_KINGDOM_OF_EXERCISE_SEX = 15;
    //标签[非典型性话唠]王国价值最高
    public static final int MOST_VALUABLE_KINGDOM_OF_NOT_TALKATIVE = 16;
    //标签[非典型性话唠]王国价值增长最快
    public static final int FAST_INCR_VALUABLE_KINGDOM_OF_TALKATIVE = 17;
    //标签[声音与光影]王国价值最高
    public static final int MOST_VALUABLE_KINGDOM_OF_VIDEOANDLIGHT = 18;
    //标签[声音与光影]王国价值增长最快
    public static final int FAST_INCR_VALUABLE_KINGDOM_OF_VIDEOANDLIGHT = 19;
    //标签[建筑不止是房子]王国价值最高
    public static final int MOST_VALUABLE_KINDOM_OF_BUILDING_MORETHAN_HOUSE = 20;
    //标签[建筑不止是房子]王国价值增长最快
    public static final int FAST_INCR_VALUABLE_KINGDOM_OF_BUILDING_MORETHAN_HOUSE = 21;
    //标签[寰球动漫游戏世界]王国价值最高
    public static final int MOST_VALUABLE_KINGDOM_OF_GAME_WORLD = 22;
    //标签[寰球动漫游戏世界]王国价值增长最快
    public static final int FAST_INCR_VALUABLE_KINGDOM_OF_GAME_WORLD = 23;
    //标签[玩物不丧志]王国价值最高
    public static final int MOST_VALUABLE_KINGDOM_OF_PLAY_LOST_NOTHING = 24;
    //标签[玩物不丧志]王国价值增长最快
    public static final int FAST_INCR_VALUABLE_KINGDOM_OF_PLAY_LOST_NOTHING = 25;
    //标签[铲屎官的日常]王国价值最高
    public static final int MOST_VALUABLE_KINGDOM_OF_POOPOFFICER = 26;
    //标签[铲屎官的日常]王国价值增长最快
    public static final int FAST_INCR_VALUABLE_KINGDOM_OF_POOPOFFICER = 27;
    //标签[旅行是我的态度]王国价值最高
    public static final int MOST_VALUABLE_KINGDOM_OF_TRAVEL_IS_MYATTITUDE = 28;
    //标签[旅行是我的态度]王国价值增长最快
    public static final int FAST_INCR_VALUABLE_KINGDOM_OF_TRAVEL_IS_MYATTITUDE = 29;
    //深夜食堂]王国价值最高
    public static final int MOST_VALUABLE_KINGDOM_OF_MIDNIGHT_DINER = 30;
    //标签[深夜食堂]王国价值增长最快
    public static final int FAST_INCR_VALUABLE_KINGDOM_OF_MIDINGHT_DINER = 31;
    //个人米汤币排行榜
    public static final int PERSONAL_RICE_LIST = 32;
    //对外分享次数用户榜单(8月7日0点后)
    public static final int NUMBER_OF_FOREIGN_SHARES_USER_LIST = 33;
    //外部阅读次数王国榜单(8月7日0点后)
    public static final int NUMBER_OF_FOREIGN_READING_TOPIC_LIST = 34;
    //正在抽奖的王国
    public static final int ATPRESENT_LOTTERY_TOPIC = 35;
    //优秀的新王国
    public static final int EXCELLENT_NEW_TOPIC = 36;

    //验证url正则表达式
    public static final String URL_REGEX = "^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+$";
    //验证颜色码正则表达式
    public static final String COLOUR_REGEX = "^#[0-9a-fA-F]{6}{1}$";
    //用户最后操作时间key值
    public static final String USER_LASTTIME = "USER:LASTTIME:";
}