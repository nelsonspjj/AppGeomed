package com.example.desenvolvimento.teste;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button botao;
    private Button btnFiqueInformado;

    @Override
    protected void onResume() {
        if (getIntent().getBooleanExtra("SAIR", false)) {
            finish();
        }
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botao = (Button) findViewById(R.id.btniniciarid);
        btnFiqueInformado = (Button) findViewById(R.id.btnInformadoid);


        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AtendimentoActivity.class));

            }
        });

        btnFiqueInformado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FiqueInformadoActivity.class));

            }
        });


    }
}
