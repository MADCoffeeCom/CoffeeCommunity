package com.example.coffeecom.model;

import java.util.ArrayList;

public class HelpdeskModel {
    ArrayList<String> question = new ArrayList<>();
    ArrayList<String> answer = new ArrayList<>();

    public HelpdeskModel() {
        question.add("How to refund your bought coffee?");
        question.add("How do I check the status of my offer?");
        question.add("Why can’t I make payment using E-Wallet");
        question.add("How to top up my coffee wallet?");
        question.add("How to report a seller for selling fake coffee.");
        question.add("Why I can’t cancel my order?");

        answer.add("There is no refund available from the platform. Please liaise with the barista.");
        answer.add("Check the coffee page.");
        answer.add("I don't know");
        answer.add("First, you need to activate your wallet. Then, you can top up using ur bank card.");
        answer.add("Click the 3 dot up at the barista and write a report to the platform!");
        answer.add("Tou thought this is public transport?");
    }

    public ArrayList<String> getQuestion() {
        return question;
    }

    public void setQuestion(ArrayList<String> question) {
        this.question = question;
    }

    public ArrayList<String> getAnswer() {
        return answer;
    }

    public void setAnswer(ArrayList<String> answer) {
        this.answer = answer;
    }
}
