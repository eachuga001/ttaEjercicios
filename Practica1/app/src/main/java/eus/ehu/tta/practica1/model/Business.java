package eus.ehu.tta.practica1.model;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.List;

/**
 * Created by edwin on 28/12/17.
 */

public interface Business {
    String[] TEXT_QUESTION = {"Test 1","Test 2","Test 3"};
    String[] TEXT_OPTIONS = {"Option 1", "Option 2", "Option 3", "Option 4","Option 5"};
    String[] MIME_TYPE = {"text/html","video","audio"};
    int[] CORRECT_OPTION = {1,2,3};
    String LOGIN = "1234";
    String PASS = "1234";

    String[] ADVICES = {"http://www.google.es","Advice 2","Advice 3"};

}
