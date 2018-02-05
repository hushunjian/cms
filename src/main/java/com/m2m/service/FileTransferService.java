package com.m2m.service;

import com.m2m.exception.QiNiuException;
import com.m2m.exception.SystemException;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.ProxyConfiguration;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.UUID;

@Service
public class FileTransferService extends BaseService {
    private static final Logger log = LoggerFactory.getLogger(FileTransferService.class);

    public static final String APPID = "wx06b8675378eb1a62";
    public static final String SECRET = "59114162f6c8f043cb3e9f204a78bede";
    private static final String ACCESS_KEY = "1XwLbO6Bmfeqyj7goM1ewoDAFHKiQOI8HvkvkDV0";
    private static final String SECRET_KEY = "9fmLV9tnplKRITWQV7QOQYANArqCNELd_SXtjwh9";
    private static final String BUCKET = "ifeeling";
    private static final String BUCKET_VIDEO = "m2m-video";
    private Auth auth;
    private UploadManager uploadManager;
    private BucketManager bucketManager;

    @Value("${proxy.host}")
    private String proxyHost;
    @Value("${proxy.port}")
    private int proxyPort;
    @Value("${proxy.status}")
    private String proxyStatus;

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

    @PostConstruct
    public void init() {
        Configuration configuration = new Configuration(Zone.zone0());
        if (!StringUtils.isEmpty(proxyStatus) && "enabled".equals(proxyStatus)) {
            log.info("采用代理proxyHost[{}],proxyPort[{}]", proxyHost, proxyPort);
            configuration.proxy = new ProxyConfiguration(proxyHost, proxyPort, null, null, Proxy.Type.HTTP);
        }

        auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        uploadManager = new UploadManager(configuration);
        bucketManager = new BucketManager(auth, configuration);
    }

    /**
     * 文件上传
     *
     * @param multipartFile
     */
    public void upload(MultipartFile multipartFile) {

    }

    public void upload(MultipartFile file, String key) throws SystemException {
        try {
            this.upload(file.getBytes(), key, 0);
        } catch (IOException e) {
            throw new com.m2m.exception.IOException();
        }
    }

    public void upload(byte[] bytes, String key) throws SystemException {
        this.upload(bytes, key, 0);
    }

    public void upload(byte[] data, String key, int type) throws SystemException {
        String bucket = BUCKET;
        if (type == 1) {
            bucket = BUCKET_VIDEO;
        }
        //上传到七牛后保存的文件名
        String token = auth.uploadToken(bucket);
        try {
            uploadManager.put(data, key, token);
        } catch (QiniuException e) {
            throw new QiNiuException();
        }
    }

    /**
     * 获取上传七牛后的图片key
     *
     * @param headimgurl
     * @return
     * @throws Exception
     */
    private String getQNImageKey(String headimgurl) throws Exception {
        URL url = new URL(headimgurl);
        //http://cdn.me-to-me.com/key七牛地址
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        // 输入流
        con.setRequestMethod("GET");
        con.setConnectTimeout(5 * 1000);
        InputStream is = con.getInputStream();
        //得到图片的二进制数据
        byte[] btImg = readInputStream(is);
        String key = UUID.randomUUID().toString();
        upload(btImg, key);
        return key;
    }

    public void deleteQiniuResource(String key) throws SystemException {
        try {
            bucketManager.delete(BUCKET, key);
        } catch (QiniuException e) {
            throw new QiNiuException();
        }
    }

}
