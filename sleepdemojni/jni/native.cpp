#define LOG_TAG "sleepdemojni"

#include <utils/Log.h>
#include <stdio.h>
#include <JNIHelp.h>
#include <utils/Trace.h>
#define ATRACE_TAG 2

namespace android {
static void _usleep(JNIEnv*, jobject clazz, jint us) {
    ATRACE_CALL();
    ALOGI("--------->begin usleep():   %d", us);
	usleep(us);
	ALOGI("--------->end usleep()");
}

static const JNINativeMethod g_methods[] = {
    { "uSleep", "(I)V", (void*)_usleep },
};

int register_com_gionee_sleepdemo_Native(JNIEnv *env) {
    if (jniRegisterNativeMethods(
            env, "com/gionee/sleepdemo/Native", g_methods, NELEM(g_methods)) < 0) {
        return JNI_ERR;
    }
    return JNI_VERSION_1_6;
}

} // namespace android

int JNI_OnLoad(JavaVM *jvm, void* reserved) {
    JNIEnv *env;

    if (jvm->GetEnv((void**)&env, JNI_VERSION_1_6)) {
        return JNI_ERR;
    }

    return android::register_com_gionee_sleepdemo_Native(env);
}
