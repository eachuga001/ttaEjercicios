package eus.ehu.tta.practica1;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;

import eus.ehu.tta.practica1.model.FileOperations;

public class NuevoEjercicioActivity extends AppCompatActivity {

    private static final int READ_REQUEST_CODE = 0;
    private static final int VIDEO_REQUEST_CODE = 1;
    private static final int AUDIO_REQUEST_CODE = 2;
    private static final int PICTURE_REQUEST_CODE = 3;
    private static final int SAVE_REQUEST_CODE = 4;

    private LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_ejercicio);
        layout = (LinearLayout)findViewById(R.id.layoutNuevoEjercicioActivity);
    }
    //@Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(resultCode != Activity.RESULT_OK)
            return;
        switch (requestCode){
            case READ_REQUEST_CODE:

                break;
            case VIDEO_REQUEST_CODE:
                Toast.makeText(this,"Video "+R.string.successSaving,Toast.LENGTH_SHORT).show();
            case AUDIO_REQUEST_CODE:
                Toast.makeText(this,"Audio "+R.string.successSaving,Toast.LENGTH_SHORT).show();

                break;
            case PICTURE_REQUEST_CODE:

                break;
            case SAVE_REQUEST_CODE:
                //FileOperations f = new FileOperations();
                /*if(f.isExternalStorageWritable()) {
                    f.getAlbumStorageDir("AudioPractica1");
                    Toast.makeText(this,R.string.successSaving,Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(this,R.string.errorSave,Toast.LENGTH_SHORT).show();
                */

                break;
        }
    }

    public void sendFile(View view, Uri uri){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        startActivityForResult(intent,SAVE_REQUEST_CODE);
    }

    public void clickRecAudio(View view){
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE))
            Toast.makeText(this,R.string.noMicrophone,Toast.LENGTH_SHORT).show();
        else{
            Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
            if(intent.resolveActivity(getPackageManager())!=null)
                startActivityForResult(intent, AUDIO_REQUEST_CODE);
            else
                Toast.makeText(this,R.string.noApp,Toast.LENGTH_SHORT).show();
        }
    }

    public void clickRecVideo(View view){
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA))
            Toast.makeText(this,R.string.noCamera,Toast.LENGTH_SHORT).show();
        else{
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            if(intent.resolveActivity(getPackageManager())!=null)
                startActivityForResult(intent,VIDEO_REQUEST_CODE);
            else
                Toast.makeText(this,R.string.noApp,Toast.LENGTH_SHORT).show();
        }
    }

    public void clickUploadFile(View view){

    }
}
