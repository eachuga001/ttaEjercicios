package eus.ehu.tta.practica1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

import eus.ehu.tta.practica1.model.Business;
import eus.ehu.tta.practica1.model.Test;
import eus.ehu.tta.practica1.presentation.Data;

public class NuevoTestActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioGroup rgTestOptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_test);
        Data data = new Data();
        Test test = data.getTest();
        rgTestOptions = (RadioGroup)findViewById(R.id.rgOpcionesTest);
        TextView tvPreguntaTest = (TextView)findViewById(R.id.tvPreguntaTest);
        tvPreguntaTest.setText(test.getTextQuestion());

        int i = 0;
        for(Test.Choice choice : test.getChoices()){
            RadioButton radio =  new RadioButton(this);
            radio.setText(choice.getTextChoice());
            radio.setOnClickListener(this);
            rgTestOptions.addView(radio);
        }

    }


    @Override
    public void onClick(View v) {
        findViewById(R.id.btnEnviar).setVisibility(View.VISIBLE);
    }
}
