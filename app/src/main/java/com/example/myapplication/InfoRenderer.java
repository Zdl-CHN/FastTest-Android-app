package com.example.myapplication;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class InfoRenderer implements GLSurfaceView.Renderer {
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        String vendor = GLES20.glGetString(GLES20.GL_VENDOR);
        String renderer = GLES20.glGetString(GLES20.GL_RENDERER);
        String version = GLES20.glGetString(GLES20.GL_VERSION);

        Log.d("GLInfo", "厂商: " + vendor);
        Log.d("GLInfo", "型号: " + renderer);
        Log.d("GLInfo", "版本: " + version);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // 表面尺寸变化
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        // 绘制帧（可空实现）
    }
}