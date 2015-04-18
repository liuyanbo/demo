package com.gionee.sleepdemo;

public class Native {
    static {
        System.loadLibrary("sleepdemojni");
    }

    static void sleep(int ms) {
        uSleep(1000 * ms);
    }

    static native void uSleep(int us);
}
