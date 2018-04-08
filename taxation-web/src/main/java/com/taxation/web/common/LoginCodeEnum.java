package com.taxation.web.common;

/**
 * 登录状态枚举类
 */
public enum LoginCodeEnum {
    LoginVerifyCode("0"),
    LoginSuccess("1"),
    LoginFailure("2"),
    LoginError("3");

    /**
     * 返回码
     */
    private String retCode;

    LoginCodeEnum(String retCode) {
        this.retCode = retCode;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public static LoginCodeEnum[] getAllStatus() {
        return LoginCodeEnum.values();
    }
}
