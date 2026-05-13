package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.myapplication.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class MainActivity extends AppCompatActivity {
    NativeTest n = new NativeTest();
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Button myButton = findViewById(R.id.button);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n.invokeNativeTest();
            }
        });

//        binding = ActivityMainBinding.inflate(getLayoutInflater());

//        webView();
//        testDec();
    }


    private void webView(){
        Log.i("TG","mod:webview");
        Ja3WebView j = new Ja3WebView(this);

        WebView webView = j.getWebView();

        setContentView(webView);
        Log.i("TG","666666");
        webView.loadUrl("https://tls.browserscan.net/api/tls");
        onResume();
    }
    private void testDec(){
        Log.i("TG","mod:testDec");
        new FastTest(getApplicationContext());

    }
    @Override
    protected void onResume() {
        super.onResume();

    }


}