package eus.ehu.tta.practica1;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    public static String EXTRA_USER,EXTRA_LOGIN,EXTRA_TEST;
    private TextView tvLogin;
    private String testString;
    private Button btnNuevoTest;
    private Button btnNuevoEjercicio;
    private Button btnSeguimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        tvLogin = (TextView)findViewById(R.id.tvLogin);
        tvLogin.setText(intent.getStringExtra(EXTRA_LOGIN));
        testString = intent.getStringExtra(HomeActivity.EXTRA_TEST);
        btnNuevoEjercicio = (Button)findViewById(R.id.btnNuevoEjercicio);
        btnNuevoTest = (Button)findViewById(R.id.btnNuevoTest);
        btnSeguimiento = (Button)findViewById(R.id.btnSeguimiento);

    }

    public void nuevoTest(View view){
        Intent intent = new Intent(this,NuevoTestActivity.class);
        System.out.println(getIntent().getStringExtra(HomeActivity.EXTRA_TEST));
        intent.putExtra(HomeActivity.EXTRA_TEST,testString);
        startActivity(intent);

    }

    public void nuevoEjercicio(View view){
        Intent intent = new Intent(this,NuevoEjercicioActivity.class);
        startActivity(intent);
    }

}
