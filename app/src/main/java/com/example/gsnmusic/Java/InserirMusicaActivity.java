package com.example.gsnmusic.Java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gsnmusic.R;
import com.example.gsnmusic.db.AppDatabase;
import com.example.gsnmusic.db.Musicas;

public class InserirMusicaActivity extends AppCompatActivity {

    EditText txtnomeMusica, txtartistaMusica;
    Button btnaddMusica;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_musica);
        txtnomeMusica = findViewById(R.id.txtnomeMusica);
        txtartistaMusica = findViewById(R.id.txtartistaMusica);

        btnaddMusica = findViewById(R.id.btnaddmusic);

        btnaddMusica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtnomeMusica.getText().toString().equals("") || txtnomeMusica.getText().toString().equals("") ){
                    Toast.makeText(getApplicationContext(), "Preencha todos os espaços", Toast.LENGTH_LONG).show();
                }else {
                    insertMusic(txtnomeMusica.getText().toString(), txtartistaMusica.getText().toString());
                    Intent intent = new Intent(InserirMusicaActivity.this, ActivityMenu.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("top", true);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }





    private void insertMusic(String musicName, String artistName){
        AppDatabase appDatabase = AppDatabase.getDbInstance(getApplicationContext());
        Musicas musicas = new Musicas();
        musicas.nomemusica = musicName;
        musicas.artista = artistName;

        appDatabase.musicasDAO().insertUser(musicas);
    }

}

