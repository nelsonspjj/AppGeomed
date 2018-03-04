package com.example.desenvolvimento.teste;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class FiqueInformadoActivity extends AppCompatActivity {


    private Button btvoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fique_informado);

        btvoltar = (Button) findViewById(R.id.btvoltar);

        btvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(FiqueInformadoActivity.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });


    }
}
