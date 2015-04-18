LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional
#LOCAL_JNI_SHARED_LIBRARIES_ABI := armeabi
LOCAL_JNI_SHARED_LIBRARIES := libsleepdemojni

LOCAL_SRC_FILES := $(call all-java-files-under, src)

LOCAL_PROGUARD_ENABLED := disabled

LOCAL_PACKAGE_NAME := sleepdemo

#LOCAL_CERTIFICATE := platform

include $(BUILD_PACKAGE)

include $(CLEAR_VARS)
LOCAL_PREBUILT_LIBS :=libsleepdemojni:libs/armeabi/libsleepdemojni.so

include $(BUILD_MULTI_PREBUILT)
include $(call all-makefiles-under,$(LOCAL_PATH))
