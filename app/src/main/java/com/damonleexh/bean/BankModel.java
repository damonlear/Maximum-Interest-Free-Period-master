package com.damonleexh.bean;

import java.io.Serializable;

public class BankModel implements Serializable {
    //英文缩写
    protected String bank;
    //中文名称
    protected String name;

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
