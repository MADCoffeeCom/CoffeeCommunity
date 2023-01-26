package com.example.coffeecom.model;

import java.util.ArrayList;

public class HelpdeskModel {

    String helpId;
    String question;
    String answer;

    public HelpdeskModel(String helpId, String question, String answer) {
        this.helpId = helpId;
        this.question = question;
        this.answer = answer;
    }

    public String getHelpId() {
        return helpId;
    }

    public void setHelpId(String helpId) {
        this.helpId = helpId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
