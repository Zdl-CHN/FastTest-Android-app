package com.example.myapplication;

import android.util.Log;

public class NativeTest {
    public NativeTest(){
        System.loadLibrary("tst");
        Log.d("TAG", "NativeTest: " + jni_tst());

    }
    public void invokeNativeTest(){
        Log.d("TAG", "NativeTestinvoke: " + jni_tst());
    }
    public native long jni_tst();

}

