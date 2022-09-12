package com.rq.chapter9;

public class MediaRecoder {

    private static native final void native_init();

    private native void start() throws IllegalStateException;
}
