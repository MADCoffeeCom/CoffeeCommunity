package com.example.coffeecom.model;

public class BankCardModel {
    private int bankCardNo;
    private String bankHolderName;
    private int cardCvv;
    private int cardExpiryDate;

    public BankCardModel(int bankCardNo, String bankHolderName, int cardCvv, int cardExpiryDate) {
        this.bankCardNo = bankCardNo;
        this.bankHolderName = bankHolderName;
        this.cardCvv = cardCvv;
        this.cardExpiryDate = cardExpiryDate;
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
}
