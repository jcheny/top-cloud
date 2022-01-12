package com.ihave.async.exception;

/**
 * 如果任务在执行之前，自己后面的任务已经执行完或正在被执行，则抛该exception
 * @version 1.0
 */
public class SkippedException extends RuntimeException {
    public SkippedException() {
        super();
    }

    public SkippedException(String message) {
        super(message);
    }
}
