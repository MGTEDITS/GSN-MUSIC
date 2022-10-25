package com.example.gsnmusic.Java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.gsnmusic.R;
import com.example.gsnmusic.classes.JavaMailAPI;
import com.example.gsnmusic.classes.Users;
import com.example.gsnmusic.db.AppDatabase;
import com.example.gsnmusic.db.User;

import java.util.Random;

public class CriarContaActivity extends AppCompatActivity {

    Button btncriarconta;
    EditText txtusername,txtemail, txtpassword;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);

        db = AppDatabase.getDbInstance(this.getApplicationContext());
        txtusername = findViewById(R.id.txtusernamecriar);
        txtemail = findViewById(R.id.txtemailcriar);
        txtpassword = findViewById(R.id.txtpasswordcriar);
        btncriarconta = findViewById(R.id.btncriaraccount);

        String email = txtemail.getText().toString().trim();

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";



        btncriarconta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtusername.getText().toString().equals("") || txtemail.getText().toString().equals("") ||txtpassword.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "PorFavor preencha todos os espaços", Toast.LENGTH_LONG).show();
                }else {

                    if (!txtemail.getText().toString().matches(emailPattern))
                    {
                        txtemail.setError("Email incorreto");
                        txtemail.requestFocus();
                    }
                    else
                    {
                        Users users = new Users(txtusername.getText().toString(),txtemail.getText().toString(),txtpassword.getText().toString());
                        inserirUser(users);
                        Intent intent = new Intent(CriarContaActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }

                }
            }
        });



    }

    private void enviaremail(Integer i){
        String mail = txtemail.getText().toString().trim();
        Toast.makeText(getApplicationContext(), ""+txtemail.getText().toString(), Toast.LENGTH_SHORT).show();
        String Subject = "Verificar Conta";
        String Mensagem = i.toString();
        JavaMailAPI javaMailAPI = new JavaMailAPI(this, mail, Subject ,Mensagem);
        javaMailAPI.execute();
    }



    private void inserirUser(Users userClass){
        User user = new User();
        user.username = userClass.getUsername();
        user.email = userClass.getEmail();
        user.password = userClass.getPassword();
        db.userDao().insertUser(user);
        finish();
    }
}