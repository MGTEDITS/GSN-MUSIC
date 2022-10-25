package com.example.gsnmusic.Java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gsnmusic.R;

public class LogActionActivity extends AppCompatActivity {

    Button btncriar, btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_action);

        btncriar = findViewById(R.id.btncriar);
        btnlogin = findViewById(R.id.btnsignin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogActionActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        btncriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogActionActivity.this,CriarContaActivity.class);
                startActivity(intent);
            }
        });
    }
}