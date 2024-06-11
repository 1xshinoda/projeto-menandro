package com.example.projetomenandro.Activity;

public class Question {
    private int imageResId;
    private String text;
    private String[] answers;
    private int correctAnswerIndex;

    public Question(int imageResId, String text, String[] answers, int correctAnswerIndex) {
        this.imageResId = imageResId;
        this.text = text;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getText() {
        return text;
    }

    public String[] getAnswers() {
        return answers;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}

