package com.kylechen.mybatis.model;

public class Test {
    private String id;

    private String tb01;

    private String tb02;

    private String tb03;

    private String tb04;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTb01() {
        return tb01;
    }

    public void setTb01(String tb01) {
        this.tb01 = tb01 == null ? null : tb01.trim();
    }

    public String getTb02() {
        return tb02;
    }

    public void setTb02(String tb02) {
        this.tb02 = tb02 == null ? null : tb02.trim();
    }

    public String getTb03() {
        return tb03;
    }

    public void setTb03(String tb03) {
        this.tb03 = tb03 == null ? null : tb03.trim();
    }

    public String getTb04() {
        return tb04;
    }

    public void setTb04(String tb04) {
        this.tb04 = tb04 == null ? null : tb04.trim();
    }
}