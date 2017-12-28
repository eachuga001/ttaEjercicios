package eus.ehu.tta.practica1.model;

import java.util.List;

/**
 * Created by edwin on 28/12/17.
 */

public class Test {

    public String textQuestion;
    public int correctChoice;
    public List<Choice> choices;

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
