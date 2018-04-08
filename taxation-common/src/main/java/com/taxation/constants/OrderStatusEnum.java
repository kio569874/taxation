package com.taxation.constants;


public enum OrderStatusEnum {
   // 订单状态:0-初始状态、1-订单取消、2-订单超时、3-已完成、4-申请退款、5-退款中、6-已退款
    INIT("0","初始状态"),
	CANNEL("1","订单取消"),
	TIMEOUT("2","订单超时"),
	FINISH("3","已完成"),
	APPLY_REFUND("4","申请退款"),
	REFUNDING("5","退款中"),
	REFUNDED("6","已退款"),
	SECTION_REFUND("7","部分退款");

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

    OrderStatusEnum(String code, String value){
        this.code = code;
        this.value = value;
    }
    public static OrderStatusEnum[] getAllCondition() {
        return OrderStatusEnum.values();
    }
    // 普通方法
    public static String getName(String code) {
        for (OrderStatusEnum c : OrderStatusEnum.values()) {
            if (c.getCode().equals(code)) {
                return c.getValue();
            }
        }
        return "";
    }
}
