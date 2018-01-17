package eus.ehu.tta.practica1;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.widget.MediaController;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;
import java.util.List;

import eus.ehu.tta.practica1.comms.RestClient;
import eus.ehu.tta.practica1.model.Business;
import eus.ehu.tta.practica1.model.JSONTools;
import eus.ehu.tta.practica1.model.Test;
import eus.ehu.tta.practica1.presentation.Data;
import eus.ehu.tta.practica1.view.AudioPlayer;

public class NuevoTestActivity extends AppCompatActivity implements View.OnClickListener, Runnable {

    private RadioGroup rgTestOptions;
    private LinearLayout layout;
    private String advice = null;
    private Test test;
    private String mimeType,testString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_test);
        layout = (LinearLayout)findViewById(R.id.layoutNuevoTestActivity);

        Data data = new Data(new JSONTools());
        Intent intent = getIntent();
        testString = intent.getStringExtra(HomeActivity.EXTRA_TEST);
        System.out.println(testString);
        test = data.getTest(testString);
        rgTestOptions = (RadioGroup)findViewById(R.id.rgOpcionesTest);
        TextView tvPreguntaTest = (TextView)findViewById(R.id.tvPreguntaTest);
        tvPreguntaTest.setText(test.getWording());


        int i = 0;
        for(Test.Choice choice : test.getChoices()){
            RadioButton radio =  new RadioButton(this);
            radio.setText(choice.getAnswer());
            radio.setOnClickListener(this);
            rgTestOptions.addView(radio);
            i++;
        }

    }

    public void clickEnviar(View view){

        int selected = rgTestOptions.getCheckedRadioButtonId()-1;
        int choices =  rgTestOptions.getChildCount();
        for(int i=0; i<choices;i++)
            rgTestOptions.getChildAt(i).setEnabled(false);
        layout.removeView(findViewById(R.id.btnEnviar));
        advice = test.getChoices().get(selected).getAdvise();
        mimeType = test.getChoices().get(selected).getResourceType().getMime();

        if(test.getChoices().get(selected).isCorrect())
            rgTestOptions.getChildAt(selected).setBackgroundColor(Color.GREEN);
        if(test.getChoices().get(selected).isCorrect()==false){
            rgTestOptions.getChildAt(selected).setBackgroundColor(Color.RED);
            Toast.makeText(this,"Has fallado !!", Toast.LENGTH_SHORT).show();
            if(advice != null)
                findViewById(R.id.btnVerAyuda).setVisibility(View.VISIBLE);
        }else
            Toast.makeText(getApplicationContext(),"Correcto",Toast.LENGTH_SHORT).show();
    }

    public void clickVerAyuda(View view){
        //findViewById(R.id.btnEnviar).setVisibility(View.INVISIBLE);
        if(advice != null){
            switch (mimeType){
                case "text/html":
                    showHtml(advice);
                    break;
                case "video":
                    showVideo();
                    break;
                case "audio":
                    playAudio(view);
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        findViewById(R.id.btnEnviar).setVisibility(View.VISIBLE);
    }

    private void showHtml(String advice){
        if(advice.substring(0,5).contains("://")){
            Uri uri = Uri.parse(advice);
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);
        }else{
            WebView web = new WebView(this);
            web.loadData(advice,"text/html",null);
            web.setBackgroundColor(Color.TRANSPARENT);
            web.setLayerType(WebView.LAYER_TYPE_SOFTWARE,null);
            layout.addView(web);
        }
    }

    private void showVideo(){

        VideoView videoView = new VideoView(this);
        videoView.setVideoURI(Uri.parse(advice));

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        videoView.setLayoutParams(params);
        MediaController controller = new MediaController(this){
            @Override
            public void hide(){

            }
            @Override
            public boolean dispatchKeyEvent(KeyEvent event){
                if(event.getKeyCode() == KeyEvent.KEYCODE_BACK)
                    finish();
                return NuevoTestActivity.super.dispatchKeyEvent(event);
            }
        };

        controller.setAnchorView(videoView);
        videoView.setMediaController(controller);

        layout.addView(videoView);
        videoView.start();
    }

    private void playAudio(View view){
        //ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
          //      ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);

        AudioPlayer player = new AudioPlayer(layout,this);
        try {

            player.setAudioUri(Uri.parse(advice));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        finish();
    }
}
