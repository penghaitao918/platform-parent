package com.fanfandou.common.util;

import com.fanfandou.common.base.BaseLogger;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import javax.net.ssl.X509TrustManager;



/**
 * Created by wudi.
 * Descreption:网络请求工具类(HttpClient 4.5)
 * Date:2016/3/21
 */
public class HttpUtils extends BaseLogger {

    private static final String log = "HttpUtils";
    private static Certificate cert = null;
    private static SSLConnectionSocketFactory socketFactory;//私密连接工厂

    private static TrustManager manager = new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    };

    /**
     * 是否忽略证书.https网站一般情况下使用了安全系数较低的SHA-1签名，因此首先我们在调用SSL之前需要重写验证方法，取消检测SSL。
     */
    private static void enableSSl(boolean isIgnore) {
        try {
            SSLContext context = SSLContext.getInstance("TLS");
            if (isIgnore) {
                context.init(null, new TrustManager[]{manager}, null);
            } else {
                context.init(null, new TrustManager[]{httpsManager}, null);
            }
            socketFactory = new SSLConnectionSocketFactory(context, NoopHostnameVerifier
                    .INSTANCE);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

    /**
     * 证书获取
     * @param certificateUrl 证书地址.
     */
    private static void initHttpsCertificate(String certificateUrl) {
        try {
            FileInputStream fis = new FileInputStream(certificateUrl);
            BufferedInputStream bis = new BufferedInputStream(fis);

            CertificateFactory cf = CertificateFactory.getInstance("X.509");

            while (bis.available() > 0) {
                cert = cf.generateCertificate(bis);
            }
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        }
    }

    private static TrustManager httpsManager = new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String s) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[] { (X509Certificate) cert };
        }
    };

    /**
     * httpClient get http 请求.
     *
     * @param url     请求路径.
     * @param values  请求参数.
     * @return response 请求结果状态.
     */
    public static String doGet(String url, List<NameValuePair> values) throws IOException {
        RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
        //最新版的httpClient使用实现类的是closeableHTTPClient,以前的default作废了.设置可关闭的httpclient
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(config).build();

        StringBuilder urlBuffer = new StringBuilder(url);
        if (!url.contains("?")) {
            urlBuffer.append("?");
        }

        if (values != null) {
            for (NameValuePair nameValuePair : values) {
                urlBuffer.append("&").append(nameValuePair.getName()).append("=")
                        .append(URLEncoder.encode(nameValuePair.getValue(), "UTF-8"));
            }
        }

        HttpGet get = new HttpGet(urlBuffer.toString());
        CloseableHttpResponse response = httpClient.execute(get);
        return getResponseContent(response);
    }

    /**
     * httpClient post http 请求.
     *
     * @param url     请求路径.
     * @param values  请求参数.
     * @return response 请求结果状态.
     */
    public static String doPost(String url, List<NameValuePair> values
    ) throws IOException {
        RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(config).build();

        HttpPost post = new HttpPost(url);

        if (values != null) {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(values,"gbk");
            //StringEntity entity = new StringEntity(values);
            post.setEntity(entity);
        }
        CloseableHttpResponse response = httpClient.execute(post);
        return getResponseContent(response);

    }

    public static String doPost(String url, List<NameValuePair> values, String charset) throws IOException {
        RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(config).build();

        HttpPost post = new HttpPost(url);

        if (charset == null) {
            charset = "UTF-8";
        }

        if (values != null) {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(values,charset);
            //StringEntity entity = new StringEntity(values);
            post.setEntity(entity);
        }
        CloseableHttpResponse response = httpClient.execute(post);
        return getResponseContent(response);

    }





    /**
     * httpClient get https 请求.
     *
     * @param url     请求路径.
     * @param values  请求参数.
     * @return response 请求结果状态.
     */
    public static String ignoreGetForHttps(String url, List<NameValuePair> values, boolean isIgnoreSSL) throws
            IOException {
        enableSSl(isIgnoreSSL);
        RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT)
                .setExpectContinueEnabled(true).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM,
                        AuthSchemes.DIGEST)).setProxyPreferredAuthSchemes(Collections.singletonList(AuthSchemes.BASIC))
                .build();
        //创建可用Scheme
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE).register("https", socketFactory).build();
        //创建ConnectionManager，添加Connection配置信息
        PoolingHttpClientConnectionManager connectionManager = new
                PoolingHttpClientConnectionManager(socketFactoryRegistry);

        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager)
                .setDefaultRequestConfig(config).build();

        StringBuilder urlBuffer = new StringBuilder(url);
        if (!url.contains("?")) {
            urlBuffer.append("?");
        }

        if (values != null) {
            for (NameValuePair nameValuePair : values) {
                urlBuffer.append("&").append(nameValuePair.getName()).append("=")
                        .append(URLEncoder.encode(nameValuePair.getValue(), "UTF-8"));
            }
        }

        HttpGet get = new HttpGet(urlBuffer.toString());

        CloseableHttpResponse response = httpClient.execute(get);

        return getResponseContent(response);
    }



    /**
     * httpClient get https 请求.
     *
     * @param url     请求路径.
     * @return response 请求结果状态.
     */
    public static String ignoreGetForHttps(String url, boolean isIgnoreSSL) throws
            IOException {
        enableSSl(isIgnoreSSL);
        RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT)
                .setExpectContinueEnabled(true).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM,
                        AuthSchemes.DIGEST)).setProxyPreferredAuthSchemes(Collections.singletonList(AuthSchemes.BASIC))
                .build();
        //创建可用Scheme
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE).register("https", socketFactory).build();
        //创建ConnectionManager，添加Connection配置信息
        PoolingHttpClientConnectionManager connectionManager = new
                PoolingHttpClientConnectionManager(socketFactoryRegistry);

        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager)
                .setDefaultRequestConfig(config).build();

        HttpGet get = new HttpGet(url);

        CloseableHttpResponse response = httpClient.execute(get);

        return getResponseContent(response);
    }


    /**
     * httpClient post https 请求.
     *
     * @param url     请求路径.
     * @param values  请求参数.
     * @return response 请求结果状态.
     */
    public static String ignorePostForHttps(String url, List<NameValuePair> values,  boolean isIgnoreSSL)
            throws IOException {
        enableSSl(isIgnoreSSL);
        //首先设置全局的标准cookie策略
        RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT)
                .setExpectContinueEnabled(true).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM,
                        AuthSchemes.DIGEST)).setProxyPreferredAuthSchemes(Collections.singletonList(AuthSchemes.BASIC))
                .build();

        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE).register("https", socketFactory).build();

        PoolingHttpClientConnectionManager connectionManager = new
                PoolingHttpClientConnectionManager(socketFactoryRegistry);

        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager)
                .setDefaultRequestConfig(config).build();


        HttpPost post = new HttpPost(url);

        if (values != null) {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(values,Charset.forName("utf-8"));
            post.setEntity(entity);
        }
        CloseableHttpResponse response = httpClient.execute(post);
        return getResponseContent(response);
    }

    /**
     * 获取请求返回值.
     */
    private static String getResponseContent(CloseableHttpResponse response)
            throws IOException {
        StringBuilder returnBuffer = new StringBuilder();
        BufferedReader reader = null;
        try {
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                HttpEntity httpEntity = response.getEntity();
                httpEntity = new BufferedHttpEntity(httpEntity);
                return EntityUtils.toString(httpEntity);
            }
        } finally {
            response.close();
        }
        return null;
    }


}
