package com.example.gsnmusic.Java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gsnmusic.R;
import com.example.gsnmusic.classes.Users;
import com.example.gsnmusic.db.AppDatabase;

public class MudarPassActivity extends AppCompatActivity {

    Button btnmudarPassword;
    EditText txtemail, txtpassword, txtconfpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mudar_pass);

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        txtemail = findViewById(R.id.txtemailMudarPass);
        txtpassword = findViewById(R.id.txtnovapassword);
        txtconfpassword = findViewById(R.id.txtconfpassword);

        btnmudarPassword = findViewById(R.id.btnmudarpass);

        btnmudarPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtemail.getText().toString().equals("") || !txtpassword.getText().toString().equals("") || txtconfpassword.getText().toString().equals("")){
                    if (txtpassword.getText().toString().equals(txtconfpassword.getText().toString())){
                        if (!txtemail.getText().toString().matches(emailPattern)){
                            Toast.makeText(getApplicationContext(), "Insira Email Válido", Toast.LENGTH_LONG).show();
                        }else{
                            Users users = new Users();
                            users.setPasswordEncrypt(txtpassword.getText().toString());
                            AppDatabase db = AppDatabase.getDbInstance(getApplicationContext());
                            db.userDao().updateUser(txtemail.getText().toString(), users.getPassword());
                            Intent intent = new Intent(MudarPassActivity.this, LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }

                    }else{
                        Toast.makeText(getApplicationContext(), "Password e confirmação não coicidem", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Porfavor preencha todos os espaços", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}