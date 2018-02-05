package com.m2m.im;

import com.m2m.exception.NoSuchException;
import org.apache.commons.codec.binary.Hex;

import javax.net.ssl.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class IMConnection {
    public static final String URL_API = "http://api.cn.ronghub.com";
    private static final String APP_KEY = "RC-App-Key";
    private static final String NONCE = "RC-Nonce";
    private static final String TIMESTAMP = "RC-Timestamp";
    private static final String SIGNATURE = "RC-Signature";
    private static SSLContext context = null;

    static {
        try {
            context = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            context.init(null, new TrustManager[]{tm}, null);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
//            throw new NoSuchException();
        } catch (KeyManagementException e) {
//            throw new NoSuchException();
            e.printStackTrace();
        }

        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
        });
        HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());

    }

    private static String hexSHA1(String value) {
        byte[] digest = new byte[1024];
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(value.getBytes("utf-8"));
            digest = md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchException();
        } catch (UnsupportedEncodingException e) {
            throw new NoSuchException();
        } finally {
            return String.valueOf(Hex.encodeHex(digest));
        }
    }

    public static HttpURLConnection createPostHttpConnection(String hostType,
                                                             String appKey,
                                                             String appSecret,
                                                             String uri,
                                                             String contentType) throws com.m2m.exception.IOException {
        HttpURLConnection connection = null;
        try {
            String nonce = String.valueOf(Math.random() * 1000000);
            String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
            StringBuilder toSign = new StringBuilder(appSecret).append(nonce).append(timestamp);
            String sign = hexSHA1(toSign.toString());
            uri = hostType + uri;
            URL url = new URL(uri);
            connection = (HttpURLConnection) url.openConnection();
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setInstanceFollowRedirects(true);
            connection.setConnectTimeout(30000);
            connection.setReadTimeout(30000);

            connection.setRequestProperty(APP_KEY, appKey);
            connection.setRequestProperty(NONCE, nonce);
            connection.setRequestProperty(TIMESTAMP, timestamp);
            connection.setRequestProperty(SIGNATURE, sign);
            connection.setRequestProperty("Content-Type", contentType);
        } catch (IOException e) {
            throw new com.m2m.exception.IOException();
        } finally {
            return connection;
        }
    }

    private static byte[] readInputStream(InputStream inStream) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;
    }

    public static String getResult(HttpURLConnection connection) throws com.m2m.exception.IOException {
        InputStream input = null;
        String result = null;
        try {
            if (connection.getResponseCode() == 200) {
                input = connection.getInputStream();
            } else {
                input = connection.getErrorStream();
            }
            result = new String(readInputStream(input), "UTF-8");
        } catch (IOException e) {
            throw new com.m2m.exception.IOException();
        } finally {
            return result;
        }
    }
}
