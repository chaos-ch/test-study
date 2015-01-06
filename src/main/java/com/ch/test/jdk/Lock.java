package com.ch.test.jdk;

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
