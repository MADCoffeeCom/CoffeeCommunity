package com.example.coffeecom.model;

public class BankCardModel {
    private int bankCardNo;
    private String bankHolderName;
    private int cardCvv;
    private int cardExpiryDate;
    private String bankName;

    public BankCardModel(int bankCardNo, String bankHolderName, int cardCvv, int cardExpiryDate, String bankName) {
        this.bankCardNo = bankCardNo;
        this.bankHolderName = bankHolderName;
        this.cardCvv = cardCvv;
        this.cardExpiryDate = cardExpiryDate;
        this.bankName = bankName;
    }

    public int getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(int bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getBankHolderName() {
        return bankHolderName;
    }

    public void setBankHolderName(String bankHolderName) {
        this.bankHolderName = bankHolderName;
    }

    public int getCardCvv() {
        return cardCvv;
    }

    public void setCardCvv(int cardCvv) {
        this.cardCvv = cardCvv;
    }

    public int getCardExpiryDate() {
        return cardExpiryDate;
    }

    public void setCardExpiryDate(int cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
