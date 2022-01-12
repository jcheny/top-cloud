package com.ihave.async.executor;

public abstract class Processer<T, V> extends Thread {

    public Processer(Runnable runnable) {
        super(runnable);
    }

    public Processer(Runnable runnable, String threadName) {
        super(runnable);
        setName(threadName);
    }

    public abstract V process(T param);
}
