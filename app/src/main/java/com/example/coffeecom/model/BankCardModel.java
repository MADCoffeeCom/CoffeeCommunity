package com.example.coffeecom.model;

public class BankCardModel {
    private String bankCardNo;
    private String bankHolderName;
    private String cardCvv;
    private String cardExpiryDate;
    private String bankName;

    public BankCardModel(String bankCardNo, String bankHolderName, String cardCvv, String cardExpiryDate, String bankName) {
        this.bankCardNo = bankCardNo;
        this.bankHolderName = bankHolderName;
        this.cardCvv = cardCvv;
        this.cardExpiryDate = cardExpiryDate;
        this.bankName = bankName;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getBankHolderName() {
        return bankHolderName;
    }

    public void setBankHolderName(String bankHolderName) {
        this.bankHolderName = bankHolderName;
    }

    public String getCardCvv() {
        return cardCvv;
    }

    public void setCardCvv(String cardCvv) {
        this.cardCvv = cardCvv;
    }

    public String getCardExpiryDate() {
        return cardExpiryDate;
    }

    public void setCardExpiryDate(String cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
