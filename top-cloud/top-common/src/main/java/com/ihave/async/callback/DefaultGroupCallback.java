package com.ihave.async.callback;

import com.ihave.async.wrapper.WorkerWrapper;

import java.util.List;

/**
 * @version 1.0
 */
public class DefaultGroupCallback implements IGroupCallback {
    @Override
    public void success(List<WorkerWrapper> workerWrappers) {

    }

    @Override
    public void failure(List<WorkerWrapper> workerWrappers, Exception e) {

    }
}
