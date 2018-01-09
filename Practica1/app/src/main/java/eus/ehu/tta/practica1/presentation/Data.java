package eus.ehu.tta.practica1.presentation;

import java.util.ArrayList;
import java.util.List;

import eus.ehu.tta.practica1.model.Business;
import eus.ehu.tta.practica1.model.Test;

/**
 * Created by edwin on 28/12/17.
 */

public class Data implements Business{

    private Test test;

    public Data (){
        setTest();
    }

    public Test getTest(){
        return this.test;
    }

    public void setTest() {
        test = new Test();

        List<Test.Choice> choices = new ArrayList<Test.Choice>();

        test.setTextQuestion(TEXT_QUESTION[0]);
        test.setCorrectChoice(CORRECT_OPTION[1]);
        test.setAdvice(ADVICES[1]);
        test.setTypeMIME(MIME_TYPE[2]);//0=text/html, 1=video, 2=audio

        for(int i=0;i<TEXT_OPTIONS.length;i++){
            Test.Choice choice = new Test.Choice();
            choice.setTextChoice(TEXT_OPTIONS[i]);
            choice.setNumChoice(i);
            if(choice.getNumChoice() == test.getCorrectChoice())
                choice.setCorrect(true);
            choices.add(choice);
        }
        test.setChoices(choices);

    }

}
