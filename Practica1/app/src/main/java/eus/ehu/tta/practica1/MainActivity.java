package eus.ehu.tta.practica1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText etLogin;
    private EditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etLogin = (EditText) findViewById(R.id.etLogin);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin =  (Button) findViewById(R.id.btnLogin);
    }

    public void login(View view){
        Intent intent = new Intent(this,HomeActivity.class);
        if(authenticate(etLogin.toString(),etPassword.toString())){
            intent.putExtra(HomeActivity.EXTRA_LOGIN,etLogin.toString());
            startActivity(intent);
        }
    }

    public boolean authenticate (String login, String password){
        boolean isOk = true;

        return isOk;
    }
}
