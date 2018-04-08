package com.taxation.constants;

/**
 * 证件类型枚举类
 * @author zhangjiwei
 */
public enum PayWayEnum {
    Alipay("0","支付宝"),
    WeChatPay("1","微信");

    private String code;
    private String value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    PayWayEnum(String code, String value){
        this.code = code;
        this.value = value;
    }
    public static PayWayEnum[] getAllCondition() {
        return PayWayEnum.values();
    }
    // 普通方法
    public static String getName(String code) {
        for (PayWayEnum c : PayWayEnum.values()) {
            if (c.getCode().equals(code)) {
                return c.getValue();
            }
        }
        return "";
    }
}
