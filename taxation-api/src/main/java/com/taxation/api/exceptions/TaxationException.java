package com.taxation.api.exceptions;

/**
 * 对于对外部抛出的异常进行再封装。方便异常发生时的日志记录及客户端对异常的统一处理
 *
 * @author zjw
 */
public class TaxationException extends RuntimeException {

    public TaxationException(String message) {
        super(message);
    }

    public TaxationException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaxationException(Throwable cause) {
        super(cause);
    }
}
