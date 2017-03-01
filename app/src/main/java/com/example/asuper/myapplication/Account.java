package com.example.asuper.myapplication;

/**
 * Created by Super on 2017/2/22.
 */

public class Account  {
    private int id;
    private int money;
    private String desc;
    private String createDate;

    public Account(int id, int money, String desc, String createDate) {
        this.id = id;
        this.money = money;
        this.desc = desc;
        this.createDate = createDate;
    }

    public Account() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
