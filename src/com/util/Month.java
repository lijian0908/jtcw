package com.util;

public enum Month {
    January("01","һ��"),
    February("02","����"),
    March("03","����"),
    April("04","����"),
    May("05","����"),
    June("06","����"),
    July("07","����"),
    August("08","����"),
    September("09","����"),
    October("10","ʮ��"),
    November("11","ʮһ��"),
    December("12","ʮ����");

    private String code;
    private String value;

    Month(String code,String value){
        this.code = code;
        this.value = value;
    }

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
}
