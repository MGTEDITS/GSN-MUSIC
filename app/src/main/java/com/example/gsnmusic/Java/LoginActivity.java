package com.example.gsnmusic.Java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gsnmusic.R;
import com.example.gsnmusic.classes.Users;
import com.example.gsnmusic.db.AppDatabase;
import com.example.gsnmusic.db.User;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText txtusername;
    EditText txtpassword;
    TextView lbmudarPassword;
    Button btninciarSessao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtusername = findViewById(R.id.txtemailcriar);
        txtpassword = findViewById(R.id.txtpasswordcriar);
        btninciarSessao = findViewById(R.id.btninciarsessao);

        lbmudarPassword = findViewById(R.id.lbesquecerpasse);

        lbmudarPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MudarPassActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        btninciarSessao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtusername.getText().toString().equals("") || txtpassword.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Por Favor Prencha todos os campos", Toast.LENGTH_SHORT).show();
                }else{
                    AppDatabase db = AppDatabase.getDbInstance(getApplicationContext());
                    List<User> users = db.userDao().getuser(txtusername.getText().toString());

                    boolean ans = users.size() != 0;
                    if (ans){

                        Users users1 = new Users();

                        users1.setUsername(users.get(0).username);
                        users1.setPassword(users.get(0).password);
                        Log.i("TAG", "onClick: " + users1.getPassword());
                        users1.setEmail(users.get(0).email);


                        if (users1.verifyPassword(users1,txtpassword.getText().toString())){
                            Intent intent = new Intent(LoginActivity.this, ActivityMenu.class);
                            intent.putExtra("username",users.get(0).username);
                            startActivity(intent);
                        }else {
                            txtpassword.setError("Password incorreta");
                            txtpassword.requestFocus();
                            Toast.makeText(getApplicationContext(), "PassWord Incorreta!", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        txtusername.setError("Utilizador incorreto");
                        txtusername.requestFocus();
                        Toast.makeText(getApplicationContext(), "Utilizador Incorreto", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


    }
}