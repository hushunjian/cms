package com.m2m.exception;

import java.util.HashMap;
import java.util.Map;

public class ErrorMapping {
    private static Map<Class, KV> HOLDER = new HashMap<Class, KV>();

    static {
        //客户端异常
        HOLDER.put(AuthenticationException.class, new KV(4001, "身份验证错误."));
        HOLDER.put(UserAlreadyExistsException.class, new KV(4003, "用户已经存在."));
        HOLDER.put(RoleAlreadyExistsException.class, new KV(4004, "角色已经存在."));
        HOLDER.put(FeatureAlreadyExistsException.class, new KV(4005, "功能已经存在."));
        HOLDER.put(TopicTagNotFoundException.class, new KV(4006, "王国标签不存在."));
        HOLDER.put(UserNotFindException.class, new KV(4007, "用户不存在"));
        HOLDER.put(TopicListedNotFindException.class, new KV(4008, "上市王国信息不存在"));
        HOLDER.put(TopicNotExistsException.class, new KV(4009, "王国不存在"));
        HOLDER.put(OldKingNotExistsException.class, new KV(4010, "原国王信息不存在"));
        HOLDER.put(NewKingNotExistsException.class, new KV(4011, "新国王信息不存在"));
        HOLDER.put(CanNotChangKingToYourSelfException.class, new KV(4012, "不能将王国转让给自己"));
        HOLDER.put(TimeParseFailException.class, new KV(4013, "日期格式转换错误"));
        HOLDER.put(TargetInformationNotExist.class, new KV(4014, "需要添加的目标信息不存在"));
        HOLDER.put(URLFormatErrorException.class, new KV(4015, "URL格式不正确"));
        HOLDER.put(ColourFormatErrorException.class, new KV(4016, "请输入类似与[#FFFFFF]格式的颜色码"));
        HOLDER.put(TagAlreadyExistsException.class, new KV(4017, "该标签名已存在"));
        HOLDER.put(InvalidPasswordException.class, new KV(4018, "用户密码不匹配，请重新输入"));
        HOLDER.put(NoExportDataException.class, new KV(4019, "暂无数据导出"));

        //服务器异常
        HOLDER.put(AccessControlException.class, new KV(5002, "访问控制错误."));
        HOLDER.put(IOException.class, new KV(5901, "IO异常."));
        HOLDER.put(FileNotFoundException.class, new KV(5902, "文件不存在."));
        HOLDER.put(URISyntaxException.class, new KV(5903, "URI语法错误."));
        HOLDER.put(QiNiuException.class, new KV(5904, "七牛错误."));
        HOLDER.put(RedisException.class, new KV(5905, "Redis数据库错误."));
        HOLDER.put(IMException.class, new KV(5906, "获取即时通讯令牌失败"));
    }

    public static Integer getCode(Class<? extends SystemException> clazz) {
        return HOLDER.get(clazz).status;
    }

    public static String getMessage(Class<? extends SystemException> clazz) {
        return HOLDER.get(clazz).message;
    }

    private static class KV {
        public Integer status;
        public String message;

        KV(Integer code, String message) {
            this.status = code;
            this.message = message;
        }
    }
}
