package com.boris.learn.concurrent.part1.demo10.account;

import java.math.BigDecimal;

public class Account {
    //账户号
    private String accNum;
    //余额
    private BigDecimal balance;

    public void debit(BigDecimal amount) {
        System.out.println("账户" + accNum + "扣款:" + String.valueOf(amount));
    }

    public void credit(BigDecimal amount) {
        System.out.println("账户" + accNum + "收款:" + String.valueOf(amount));
    }

    public String getAccNum() {
        return accNum;
    }

    public void setAccNum(String accNum) {
        this.accNum = accNum;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
