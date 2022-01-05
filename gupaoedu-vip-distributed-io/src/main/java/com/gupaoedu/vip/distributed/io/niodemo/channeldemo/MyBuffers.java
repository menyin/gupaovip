package com.gupaoedu.vip.distributed.io.niodemo.channeldemo;

import java.nio.ByteBuffer;

public class MyBuffers {
    private ByteBuffer readBuffer;
    private ByteBuffer writeBuffer;
    public MyBuffers(int readBufferCapacity,int writeBufferCapacity) {
        this.readBuffer = ByteBuffer.allocate(readBufferCapacity);
        this.writeBuffer= ByteBuffer.allocate(writeBufferCapacity);
    }

    public ByteBuffer getReadBuffer() {
        return readBuffer;
    }

    public ByteBuffer getWriteBuffer() {
        return writeBuffer;
    }
}
