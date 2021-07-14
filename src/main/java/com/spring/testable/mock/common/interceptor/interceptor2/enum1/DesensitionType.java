package com.spring.testable.mock.common.interceptor.interceptor2.enum1;

/**
 * @author leo
 */

public enum DesensitionType {
    PHONE("phone", "11位手机号", "^(\\d{3})\\d{4}(\\d{4})$", "$1****$2"),
    // ID_CARD("idCard", "16或者18身份证号", "^(\\d{4})\\d{8,10}(\\w{4})$",
    // "$1****$2"),
    ID_CARD("idCard", "16或者18身份证号", "^(\\d{4})\\d{11,13}(\\w{1})$", "$1****$2"), BANK_CARD("bankCardNo", "银行卡号",
            "^(\\d{4})\\d*(\\d{4})$", "$1****$2"), ADDRESS("addrss", "地址", "(?<=.{3}).*(?=.{3})", "*"), REAL_NAME(
            "realName", "真实姓名", "(?<=.{1}).*(?=.{1})", "*"), EMAIL("email", "电子邮箱", "(\\w+)\\w{5}@(\\w+)",
            "$1***@$2"), CUSTOM("custom", "自定义正则处理", ""), TRUNCATE("truncate", "字符串截取处理", "");

    private String type;

    private String describe;

    private String[] regular;

    DesensitionType(String type, String describe, String... regular) {
        this.type = type;
        this.describe = describe;
        this.regular = regular;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String[] getRegular() {
        return regular;
    }

    public void setRegular(String[] regular) {
        this.regular = regular;
    }

}