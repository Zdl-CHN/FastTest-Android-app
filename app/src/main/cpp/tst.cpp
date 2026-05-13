#include <jni.h>
#include <string>
//核心：JNI_OnLoad
// 当 System.loadLibrary 被调用时，系统会自动寻找并执行这个函数
//extern "C" JNIEXPORT jstring JNICALL
//Java_com_example_myapplication_MainActivity_stringFromJNI(
//        JNIEnv* env,
//        jobject /* this */) {
//    std::string hello = "Hello from C++";
//
//    return env->NewStringUTF(hello.c_str());
//}
uint64_t  d1 = 0x1226455385;
uint64_t  d4 = 0x5324126558;

jlong dyn_load_tst(JNIEnv *env, jobject thiz) {
//    return env->NewStringUTF("Hello from JNI Dynamic Registration!");
    d1 = 0x1226455385;
    uint64_t  d2 = 0x2645538512;
    uint64_t  d3 = 0x6451225385;
    uint64_t  d5 = 0x2265541358;
    uint64_t dd =  (d1*d3 -d5&d2^d4)*0x252-0x14145243432;
    dd^=d2%d1*d3/d4/d2/d5;

    jlong j_value = static_cast<jlong>(dd);
    return j_value;
}
static const JNINativeMethod gMethods[] = {
        {"jni_tst", "()J", (void *)dyn_load_tst}
};
JNIEXPORT jint JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv *env = nullptr;
    if (vm->GetEnv((void **)&env, JNI_VERSION_1_6) != JNI_OK) {
        return JNI_ERR;
    }

    // 找到对应的 Java 类
    jclass clazz = env->FindClass("com/example/myapplication/NativeTest");
    if (clazz == nullptr) {
        return JNI_ERR;
    }

    // 动态注册：把映射表注册到类中
    // 参数：类对象, 映射表数组, 映射条数
    if (env->RegisterNatives(clazz, gMethods, sizeof(gMethods) / sizeof(gMethods[0])) < 0) {
        return JNI_ERR;
    }

    return JNI_VERSION_1_6;
}