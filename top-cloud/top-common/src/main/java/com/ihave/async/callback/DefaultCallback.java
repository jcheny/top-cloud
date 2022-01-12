package com.ihave.async.callback;


import com.ihave.async.worker.WorkResult;

/**
 * 默认回调类，如果不设置的话，会默认给这个回调
 */
public class DefaultCallback<T, V> implements ICallback<T, V> {
    @Override
    public void begin() {
        
    }

    @Override
    public void result(boolean success, T param, WorkResult<V> workResult) {

    }

}
