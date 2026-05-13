package com.example.myapplication;

import android.content.Context;
import android.opengl.GLSurfaceView;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.IntBuffer;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.cert.Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import org.json.JSONObject;
import android.content.Context;
import android.view.Display;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import android.os.ParcelFileDescriptor;
import android.os.PowerManager;
import android.os.StatFs;
import android.os.UserManager;
import android.os.storage.StorageManager;
import android.provider.Settings;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.service.persistentdata.PersistentDataBlockManager;
import android.system.Os;
import android.system.StructUtsname;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
//import android.view.DisplayInfo;
import static android.database.sqlite.SQLiteDatabase.openDatabase;
import static android.os.Process.myUserHandle;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.security.Security.getProviders;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;


import android.app.ActivityManager;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Application;
import android.app.KeyguardManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.display.DisplayManager;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.BatteryManager;
import android.os.Build;
public class FastTest {
    private String TAG = "FastTest";
    private GLSurfaceView glSurfaceView;
    public FastTest(Context ctx){
//        opengl(ctx);



//        gtFeatureInfo();
//        go3();
//        diagnoseKeyGeneration();
//        System.out.println();
//        System.out.println();
//        umTest();
//        db_r();

//        t_TT();

//        intt();
//        gtFeatureInfo();
        fastTest();
    }
    private void  fastTest(){

        String s = i("irHXj8ycqqqQoMitjnyM2LCgjY27sA==");
        Log.d(TAG,"111" +" "+ s);
    }
    public static String i(String str) {
        byte[] bytes = str.getBytes();
        byte[] bytes2 = "GioCgfUXDhftZ6Ime8U".getBytes();
        byte[] bArrDecode = Base64.decode(bytes, 2);
        int length = bytes2.length;
        int i7 = 0;
        for (int i8 = 0; i8 < bArrDecode.length; i8++) {
            if (i7 >= length) {
                i7 = 0;
            }
            bArrDecode[i8] = (byte) (bArrDecode[i8] - bytes2[i7]);
            i7++;
        }
        return new String(bArrDecode);
    }
    public  String decrypt(String str, String str2) {
        return decrypt(Base64.decode(str, 0), Base64.decode(str2, 0));
    }
    public String decrypt(byte[] bArr, byte[] bArr2) {
        return new String(xor(bArr, bArr2), StandardCharsets.UTF_8);
    }

    private static byte[] xor(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        int length2 = bArr2.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            if (i2 >= length2) {
                i2 = 0;
            }
            bArr[i] = (byte) (bArr[i] ^ bArr2[i2]);
            i++;
            i2++;
        }
        return bArr;
    }
    private void  opengl(Context ctx){
        glSurfaceView = new GLSurfaceView(ctx);
        glSurfaceView.setEGLContextClientVersion(2); // OpenGL ES 2.0
        glSurfaceView.setRenderer(new InfoRenderer());
//        setContentView(glSurfaceView);

    }
}
