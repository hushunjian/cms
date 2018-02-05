package com.m2m.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.m2m.Constant;
import com.m2m.entity.ExtImUser;
import com.m2m.entity.ExtSendSysMessage;
import com.m2m.exception.IOException;
import com.m2m.exception.NoSuchException;
import com.m2m.exception.SystemException;
import com.m2m.im.IMConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.DataOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

@Component
public class LocalConfigService {

    @Value("${h5.kingdom.url}")
    private String webappUrl;

    @Value("${im.app.key}")
    private String imAppKey;

    @Value("${im.app.secret}")
    private String imAppSecret;

    private static final String UTF_8 = "UTF-8";

    @Autowired
    private RedisService redisService;

    public String getWebappUrl() {
        return webappUrl;
    }

    public String getImAppKey() {
        return imAppKey;
    }

    public String getImAppSecret() {
        return imAppSecret;
    }

    public static String getUtf8() {
        return UTF_8;
    }

    public ExtImUser getIMUserToken(String userId, String name, String portraitUri) throws SystemException {
        StringBuilder sb = new StringBuilder();
        ExtImUser extImUser = null;
        try {
            sb.append("&userId=").append(URLEncoder.encode(userId.toString(), UTF_8));
            sb.append("&name=").append(URLEncoder.encode(name.toString(), UTF_8));
            sb.append("&portraitUri=").append(URLEncoder.encode(portraitUri.toString(), UTF_8));
            String body = sb.toString();
            if (body.indexOf("&") == 0) {
                body = body.substring(1, body.length());
            }
            HttpURLConnection conn = IMConnection.createPostHttpConnection(IMConnection.URL_API, imAppKey, imAppSecret,
                    "/user/getToken.json", "application/x-www-form-urlencoded");
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.write(body.getBytes(UTF_8));
            extImUser = JSON.parseObject(IMConnection.getResult(conn), ExtImUser.class);
        } catch (UnsupportedEncodingException e) {
            throw new NoSuchException();
        } catch (IOException e) {
            throw new com.m2m.exception.IOException();
        } finally {
            return extImUser;
        }
    }

    public ExtSendSysMessage sendSysMessage(String userId, String content) throws SystemException {
        StringBuilder sb = new StringBuilder();
        ExtSendSysMessage extSendSysMessage = null;
        try {
            String fromUserId = redisService.get(Constant.APP_CONFIG_KEY_PRE + "SELL_UID");
            sb.append("&fromUserId=").append(URLEncoder.encode(fromUserId.toString(), UTF_8));
            sb.append("&toUserId=").append(URLEncoder.encode(userId.toString(), UTF_8));
            JSONObject json = new JSONObject();
            json.put("content", content);
            sb.append("&content=").append(URLEncoder.encode(json.toJSONString(), UTF_8));
            sb.append("&objectName=").append(URLEncoder.encode("RC:TxtMsg", UTF_8));
            sb.append("&pushContent=").append(URLEncoder.encode(content, UTF_8));
            JSONObject json1 = new JSONObject();
            json1.put("pushData", content);
            sb.append("&pushData=").append(URLEncoder.encode(json1.toJSONString(), UTF_8));
            String body = sb.toString();
            if (body.indexOf("&") == 0) {
                body = body.substring(1, body.length());
            }
            HttpURLConnection conn = IMConnection.createPostHttpConnection(IMConnection.URL_API, imAppKey, imAppSecret,
                    "/message/private/publish.json", "application/x-www-form-urlencoded");
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.write(body.getBytes(UTF_8));
            extSendSysMessage = JSON.parseObject(IMConnection.getResult(conn), ExtSendSysMessage.class);
        } catch (UnsupportedEncodingException e) {
            throw new NoSuchException();
        } catch (IOException e) {
            throw new com.m2m.exception.IOException();
        } finally {
            return extSendSysMessage;
        }
    }
}
