LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

# This is the target being built.
LOCAL_MODULE:= libsleepdemojni
LOCAL_MULTILIB := 32
 
LOCAL_SRC_FILES:= \
  native.cpp

# All of the shared libraries we link against.
LOCAL_SHARED_LIBRARIES := \
	libutils liblog libnativehelper libcutils

# Also need the JNI headers.
LOCAL_C_INCLUDES += \
	$(JNI_H_INCLUDE)

include $(BUILD_SHARED_LIBRARY)
