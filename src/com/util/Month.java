package com.util;

public enum Month {
    January("01","一月"),
    February("02","二月"),
    March("03","三月"),
    April("04","四月"),
    May("05","五月"),
    June("06","六月"),
    July("07","七月"),
    August("08","八月"),
    September("09","九月"),
    October("10","十月"),
    November("11","十一月"),
    December("12","十二月");

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
