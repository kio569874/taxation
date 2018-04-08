package com.taxation.constants;


public enum PayStateEnum {
    WatingPayment("0","待支付"),
    Paid("1","已支付");

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

    PayStateEnum(String code, String value){
        this.code = code;
        this.value = value;
    }
    public static PayStateEnum[] getAllCondition() {
        return PayStateEnum.values();
    }
    // 普通方法
    public static String getName(String code) {
        for (PayStateEnum c : PayStateEnum.values()) {
            if (c.getCode().equals(code)) {
                return c.getValue();
            }
        }
        return "";
    }
}
