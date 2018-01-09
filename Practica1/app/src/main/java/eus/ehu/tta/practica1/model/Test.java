package eus.ehu.tta.practica1.model;

import java.util.List;

/**
 * Created by edwin on 28/12/17.
 */

public class Test {

    private String textQuestion;
    private String advice;
    private int correctChoice;
    private List<Choice> choices;
    private String typeMIME;


    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getAdvice() {
        return advice;
    }

    public String getTypeMIME() {
        return typeMIME;
    }

    public void setTypeMIME(String typeMIME) {
        this.typeMIME = typeMIME;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public String getTextQuestion() {
        return textQuestion;
    }

    public void setTextQuestion(String textQuestion) {
        this.textQuestion = textQuestion;
    }

    public int getCorrectChoice() {
        return correctChoice;
    }

    public void setCorrectChoice(int correctChoice) {
        this.correctChoice = correctChoice;
    }


    public static class Choice {

        int numChoice;
        boolean isCorrect = false;
        String textChoice;

        public boolean isCorrect() {
            return isCorrect;
        }

        public void setCorrect(boolean correct) {
            isCorrect = correct;
        }

        public String getTextChoice() {
            return textChoice;
        }

        public void setTextChoice(String textChoice) {
            this.textChoice = textChoice;
        }

        public int getNumChoice() {
            return numChoice;
        }

        public void setNumChoice(int numChoice) {
            this.numChoice = numChoice;
        }


    }
}
