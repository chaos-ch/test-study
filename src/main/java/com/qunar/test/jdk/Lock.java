package com.qunar.test.jdk;

import com.google.common.collect.Lists;
import com.qunar.test.ServiceFactory;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by he.chen on 14-11-12.
 */
public class Lock {
    public static void main(String[] args) {
        ReadWriteLock rwl = new ReentrantReadWriteLock();
        rwl.writeLock().lock();
        rwl.readLock().unlock();
    }
    public void test(){
        synchronized (this){}

    }
}
