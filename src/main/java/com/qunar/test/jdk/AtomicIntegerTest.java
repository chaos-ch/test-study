package com.qunar.test.jdk;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by he.chen on 14-11-17.
 */
public class AtomicIntegerTest {
    private final static AtomicInteger ThreadCount = new AtomicInteger();
    private String channel;

    public AtomicIntegerTest(String channel)
    {
        ThreadCount.incrementAndGet();
//        super("travel-file-thread-" + ThreadCount.incrementAndGet());
        this.channel = channel;
    }

}
