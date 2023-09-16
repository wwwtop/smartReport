package com.proj.core.utils.exception;

/**
 * 项目内部自定义异常类
 *
 * @author dong.ning
 */
public class ProjException extends Exception {

    public ProjException() {

    }

    public ProjException(String text) {
        super(text);
    }

    public ProjException(Throwable throwable) {
        super(throwable);
    }

}
