package com.example.myapplication;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.http.SslError;
import android.os.Build;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Ja3WebView {
    private WebView wb;
    public Ja3WebView(Context ctx){
        wb= new WebView(ctx);

        WebSettings settings = wb.getSettings();

        //必须开启的设置
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowContentAccess(true);
        settings.setAllowFileAccess(true);

        //处理 Android 9+ 的网络安全配置
        WebView.setWebContentsDebuggingEnabled(true);
        wb.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                Log.w("SSL", "忽略 SSL 错误: " + error.toString());
                handler.proceed();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // 延迟确保检测完成
                view.postDelayed(() -> {
                    // 提取检测结果
                    view.evaluateJavascript("(function() {" +
                            "try {" +
                            "  return document.body.innerText;" +
                            "} catch(e) {" +
                            "  return 'Error: ' + e.message;" +
                            "}" +
                            "})()", new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String value) {
                            if (value != null && !value.equals("null")) {
                                String jsonStr = value.replaceAll("^\"|\"$", "");
                                // 处理转义字符
                                jsonStr = jsonStr.replace("\\n", "\n")
                                        .replace("\\\"", "\"")
                                        .replace("\\\\", "\\");

                                try {
                                    JSONObject responseJson = new JSONObject(jsonStr);
                                    JSONObject jsonObject = getWebViewVersionInfoAndMeagre(new JSONObject());
                                    jsonObject.put("response", responseJson);
                                    Log.d("Parsed JSON", jsonObject.toString(4));
                                    String ver = jsonObject.getString("versionCode");
                                    saveDataToFile(jsonObject.toString(), ver + "_ja3", ctx);
                                } catch (JSONException e) {
                                    Log.e("WebViewTLS", "JSON解析错误: " + e);
                                }
                            } else {
                                Log.e("WebViewTLS", "JavaScript返回null");
                            }
                        }
                    });
                }, 1000);  // 延迟1秒
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Log.e("WebViewTLS", "加载错误: " + description + " URL: " + failingUrl);
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                Log.e("WebViewTLS", "HTTP错误: " + errorResponse.getStatusCode());
            }
        });

    }
    public void load(String url){
        wb.loadUrl(url);
    }
    public WebView getWebView(){
        return wb;
    }
    private void saveDataToFile(String data, String filename,Context context) {
        try {
            // 获取应用专属的外部存储目录（无需权限）
            File externalFilesDir = context.getExternalFilesDir(null);
            File file = new File(externalFilesDir, filename);

            // 写入文件
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data.getBytes( StandardCharsets.UTF_8));
            fos.close();

            Log.d("File Path", "Saved to: " + file.getAbsolutePath());
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 获取WebView版本信息
    private JSONObject getWebViewVersionInfoAndMeagre(JSONObject job)  {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            PackageInfo webViewPackage = WebView.getCurrentWebViewPackage();
            if (webViewPackage != null) {
                try {
                    job.put("packageName",webViewPackage.packageName );
                    job.put("versionName",webViewPackage.versionName );
                    job.put("versionCode", String.valueOf(webViewPackage.versionCode));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                return job;

            }
        }
        return job;
    }
    public static void trustAllCertificates() {
        try {
            // 1. 创建信任所有证书的 TrustManager
            TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    }
            };

            // 2. 安装 TrustManager
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new SecureRandom());

            // 3. 设置为默认
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

            // 4. 设置主机名验证器
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);

            // 5. 对于 WebView，还需要设置
            WebView.setWebContentsDebuggingEnabled(true);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
