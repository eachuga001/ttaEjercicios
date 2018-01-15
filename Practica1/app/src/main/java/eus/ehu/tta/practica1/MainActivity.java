package eus.ehu.tta.practica1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.HttpURLConnection;

import eus.ehu.tta.practica1.comms.ProgressTask;
import eus.ehu.tta.practica1.comms.RestClient;
import eus.ehu.tta.practica1.model.JSONTools;
import eus.ehu.tta.practica1.model.User;

public class MainActivity extends AppCompatActivity {

    private EditText etLogin;
    private EditText etPassword;
    private Button btnLogin;
    private RestClient restClient;
    private HttpURLConnection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restClient = new RestClient(RestClient.URL_SERVER);
        etLogin = (EditText) findViewById(R.id.etLogin);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin =  (Button) findViewById(R.id.btnLogin);
    }

    public void login(View view){
        Intent intent = new Intent(this,HomeActivity.class);
        String login = etLogin.getText().toString();
        String pass = etPassword.getText().toString();

        if(authenticate(login,pass)){
            intent.putExtra(HomeActivity.EXTRA_LOGIN,etLogin.getText().toString());
            startActivity(intent);
        }else
            Toast.makeText(this,"Error en login",Toast.LENGTH_SHORT).show();
    }

    public boolean authenticate (final String login, final String password){
        final boolean isOk = true;
        //Aqui va el codigo de validacion de contrasenas
        new ProgressTask<User>(this){

            @Override
            protected User work() throws Exception {
                JSONTools json = new JSONTools();
                restClient.setHttpBasicAuth(login,password);

                User user = json.getUserFromJson(restClient.getStringJson(restClient.REQUEST_INFO_USER+login));
                return user;
            }

            @Override
            protected void onFinish(User user) {
                if(user!=null){
                    Toast.makeText(getApplicationContext(),"HOLA "+user.getUser(),Toast.LENGTH_SHORT).show();
                }

            }
        }.execute();
        //Esto esta puesto para no tener que meter el login correcto.
        //Cuando este terminada la aplicacion controla que devuelva false cuando el login sea fallido
        return isOk;
    }
}
