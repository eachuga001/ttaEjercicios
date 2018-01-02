package eus.ehu.tta.practica1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import eus.ehu.tta.practica1.model.Business;
import eus.ehu.tta.practica1.model.Test;
import eus.ehu.tta.practica1.presentation.Data;

public class NuevoTestActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioGroup rgTestOptions;
    private LinearLayout layout;
    private int correct;
    private String advice = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_test);
        layout = (LinearLayout)findViewById(R.id.layoutNuevoTestActivity);

        Data data = new Data();
        Test test = data.getTest();
        rgTestOptions = (RadioGroup)findViewById(R.id.rgOpcionesTest);
        TextView tvPreguntaTest = (TextView)findViewById(R.id.tvPreguntaTest);
        tvPreguntaTest.setText(test.getTextQuestion());
        advice = test.getAdvise();

        int i = 0;
        for(Test.Choice choice : test.getChoices()){
            RadioButton radio =  new RadioButton(this);
            radio.setText(choice.getTextChoice());
            radio.setOnClickListener(this);
            rgTestOptions.addView(radio);
            if(choice.isCorrect())
                correct = i;
            i++;
        }

    }

    public void clickEnviar(View view){

        int selected = rgTestOptions.getCheckedRadioButtonId()-1;
        int choices =  rgTestOptions.getChildCount();
        for(int i=0; i<choices;i++)
            rgTestOptions.getChildAt(i).setEnabled(false);
        layout.removeView(findViewById(R.id.btnEnviar));

        rgTestOptions.getChildAt(correct).setBackgroundColor(Color.GREEN);
        if(selected != correct){
            rgTestOptions.getChildAt(selected).setBackgroundColor(Color.RED);
            Toast.makeText(getApplicationContext(),"Has fallado !!", Toast.LENGTH_SHORT);
            if(advice != null)
                findViewById(R.id.btnVerAyuda).setVisibility(View.VISIBLE);
        }else
            Toast.makeText(getApplicationContext(),"Correcto",Toast.LENGTH_SHORT);
    }

    public void clickVerAyuda(View view){
        WebView web = new WebView(this);
        web.loadData(advice,"text/html",null);
        web.setBackgroundColor(Color.TRANSPARENT);
        web.setLayerType(WebView.LAYER_TYPE_SOFTWARE,null);
        layout.addView(web);
    }

    @Override
    public void onClick(View v) {
        findViewById(R.id.btnEnviar).setVisibility(View.VISIBLE);
    }
}
