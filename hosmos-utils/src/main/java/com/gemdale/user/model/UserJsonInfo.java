package com.gemdale.user.model;

public class UserJsonInfo {
    private String code;
    private String desc;
    private String rts ;

    private String sign ;

    private UserInfo result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRts() {
        return rts;
    }

    public void setRts(String rts) {
        this.rts = rts;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public UserInfo getResult() {
        return result;
    }

    public void setResult(UserInfo result) {
        this.result = result;
    }
}
