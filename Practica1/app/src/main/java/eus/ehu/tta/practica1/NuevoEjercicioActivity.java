package eus.ehu.tta.practica1;

import android.app.Activity;
import android.content.Entity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import eus.ehu.tta.practica1.model.FileOperations;

public class NuevoEjercicioActivity extends AppCompatActivity {

    private static final int READ_REQUEST_CODE = 0;
    private static final int VIDEO_REQUEST_CODE = 1;
    private static final int AUDIO_REQUEST_CODE = 2;
    private static final int PICTURE_REQUEST_CODE = 3;
    private static final int SAVE_REQUEST_CODE = 4;
    private static final String TAG = "TAG";

    private LinearLayout layout;
    private Uri pictureUri;
    private String size,displayName;

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
                dumpImageMetaData(data.getData());
                Toast.makeText(this,"El fichero "+displayName+" tiene "+size+" bytes",Toast.LENGTH_SHORT).show();
                break;
            case VIDEO_REQUEST_CODE:
                Toast.makeText(this,"Video "+R.string.successSaving,Toast.LENGTH_SHORT).show();
            case AUDIO_REQUEST_CODE:
                Toast.makeText(this,"Audio "+R.string.successSaving,Toast.LENGTH_SHORT).show();
                break;
            case PICTURE_REQUEST_CODE:
                Toast.makeText(this,"Foto "+R.string.successSaving,Toast.LENGTH_SHORT).show();
                break;
            /*case SAVE_REQUEST_CODE:
                //FileOperations f = new FileOperations();
                if(f.isExternalStorageWritable()) {
                    f.getAlbumStorageDir("AudioPractica1");
                    Toast.makeText(this,R.string.successSaving,Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(this,R.string.errorSave,Toast.LENGTH_SHORT).show();


                break;  */
        }
    }

    public void clickSendFile(View view){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        startActivityForResult(intent,READ_REQUEST_CODE);
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

    public void clickTakePhoto(View view){
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA))
            Toast.makeText(this,R.string.noCamera,Toast.LENGTH_SHORT).show();
        else{
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            if(intent.resolveActivity(getPackageManager())!=null){
                File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                try{
                    File file = File.createTempFile("tta",".jpg",dir);
                    pictureUri = Uri.fromFile(file);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,pictureUri);
                    startActivityForResult(intent,PICTURE_REQUEST_CODE);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else
                Toast.makeText(this,R.string.noApp,Toast.LENGTH_SHORT).show();
        }
    }

    public void dumpImageMetaData(Uri uri){
        Cursor cursor = this.getContentResolver().query(uri,null,null,null,null,null);
        try{
            if(cursor != null && cursor.moveToFirst()){
                displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                Log.i(TAG,"Display Name: "+displayName);

                int sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE);

                if(!cursor.isNull(sizeIndex))
                    size = cursor.getString(sizeIndex);
                else
                    size = "Unknown";
                Log.i(TAG,"Size: "+size);
            }
        }finally {
            cursor.close();
        }
    }
}
