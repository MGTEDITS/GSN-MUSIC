package com.example.gsnmusic.Java;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.gsnmusic.R;
import com.example.gsnmusic.db.AppDatabase;


public class ProfileFragment extends Fragment {


    View view;
    ImageButton btneditcount, btndeleteAccount;
    TextView lbusernameProfile, lbaddmusic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        assert getArguments() != null;
        String username = getArguments().getString("username");

        Log.i("TAG", "onCreateView: username" + username);
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        btneditcount = view.findViewById(R.id.btnperfiledit);

        lbusernameProfile = view.findViewById(R.id.lbusernameProfile);

        if (username != null){
            lbusernameProfile.setText(username);
        }else {
            lbusernameProfile.setText("MGTEDITS");
        }

        btndeleteAccount = view.findViewById(R.id.btneliminarconta);

        lbaddmusic = view.findViewById(R.id.lbaddmusic);

        if (username != null) {
            if (username.equals("MGTEDITS")) {
                lbaddmusic.setVisibility(View.VISIBLE);
                lbaddmusic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(view.getContext(), InserirMusicaActivity.class);
                        startActivity(intent);
                    }
                });
            } else {
                lbaddmusic.setVisibility(View.INVISIBLE);
            }
        }

        btndeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase db = AppDatabase.getDbInstance(view.getContext());
                db.userDao().deleteuser(username);
                Intent intent = new Intent(view.getContext(), LogActionActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                view.getContext().startActivity(intent);
            }
        });

        return view;
    }
}