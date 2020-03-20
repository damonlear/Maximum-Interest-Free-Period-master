package com.damonleexh.bean;

import java.io.Serializable;

public class CreditCard extends BankModel implements Serializable {
    //银行卡号
    private String number;
    //图标链接
    private String icon;
    //交易日
    private String transactionDate;
    //账单日
    private String statementDate;
    //还款日
    private String paymentDate;
    //上一账单日
    private String previousStatementDate;
    //账单日
    private String nextStatementDate;
    //上一还款日
    private String previousPaymentDate;
    //还款到期日
    private String nextPaymentDate;
    //免息周期
    private int gracePeriod = -1;

    public CreditCard(){}

    public CreditCard(String bank, String name, String statementDate, String paymentDate) {
        this.bank = bank;
        this.name = name;
        this.statementDate = statementDate;
        this.paymentDate = paymentDate;
    }

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getStatementDate() {
        return statementDate;
    }

    public void setStatementDate(String statementDate) {
        this.statementDate = statementDate;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPreviousStatementDate() {
        return previousStatementDate;
    }

    public void setPreviousStatementDate(String previousStatementDate) {
        this.previousStatementDate = previousStatementDate;
    }

    public String getNextStatementDate() {
        return nextStatementDate;
    }

    public void setNextStatementDate(String nextStatementDate) {
        this.nextStatementDate = nextStatementDate;
    }

    public String getPreviousPaymentDate() {
        return previousPaymentDate;
    }

    public void setPreviousPaymentDate(String previousPaymentDate) {
        this.previousPaymentDate = previousPaymentDate;
    }

    public String getNextPaymentDate() {
        return nextPaymentDate;
    }

    public void setNextPaymentDate(String nextPaymentDate) {
        this.nextPaymentDate = nextPaymentDate;
    }

    public int getGracePeriod() {
        return gracePeriod;
    }

    public void setGracePeriod(int maxGracePeriod) {
        this.gracePeriod = maxGracePeriod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditCard)) return false;
        CreditCard that = (CreditCard) o;
        return getGracePeriod() == that.getGracePeriod() &&
                getBank().equals(that.getBank()) &&
                getName().equals(that.getName()) &&
                getNumber().equals(that.getNumber()) &&
                getIcon().equals(that.getIcon()) &&
                getTransactionDate().equals(that.getTransactionDate()) &&
                getStatementDate().equals(that.getStatementDate()) &&
                getPaymentDate().equals(that.getPaymentDate()) &&
                getPreviousStatementDate().equals(that.getPreviousStatementDate()) &&
                getNextStatementDate().equals(that.getNextStatementDate()) &&
                getPreviousPaymentDate().equals(that.getPreviousPaymentDate()) &&
                getNextPaymentDate().equals(that.getNextPaymentDate());
    }

    @Override
    public int hashCode() {
        return getBank().hashCode() +
                getName().hashCode() +
                getNumber().hashCode() +
                getIcon().hashCode() +
                getTransactionDate().hashCode() +
                getStatementDate().hashCode() +
                getPaymentDate().hashCode() +
                getPreviousStatementDate().hashCode() +
                getNextStatementDate().hashCode() +
                getPreviousPaymentDate().hashCode() +
                getNextPaymentDate().hashCode() +
                getGracePeriod();
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "bank='" + bank + '\'' +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", number='" + number + '\'' +
                ", statementDate='" + statementDate + '\'' +
                ", paymentDate='" + paymentDate + '\'' +
                ", previousStatementDate='" + previousStatementDate + '\'' +
                ", nextStatementDate='" + nextStatementDate + '\'' +
                ", previousPaymentDate='" + previousPaymentDate + '\'' +
                ", nextPaymentDate='" + nextPaymentDate + '\'' +
                ", maxGracePeriod=" + gracePeriod +
                '}';
    }
}
