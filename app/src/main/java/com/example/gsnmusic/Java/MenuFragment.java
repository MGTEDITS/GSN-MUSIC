package com.example.gsnmusic.Java;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.gsnmusic.R;
import com.example.gsnmusic.MusicasListAdapter;
import com.example.gsnmusic.db.AppDatabase;
import com.example.gsnmusic.db.Musicas;
import com.example.gsnmusic.kotlin.LeitorActivity;

import java.util.List;

public class MenuFragment extends Fragment {

    View view;
    private MusicasListAdapter musicasListAdapter;
    ImageButton btnplaymusicLife;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_menu, container, false);

        loaduserlist(view);
        initRecyclerView(view);

        btnplaymusicLife = view.findViewById(R.id.btnplaymusiclife);

        btnplaymusicLife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), LeitorActivity.class);
                startActivity(intent);
            }
        });
        return view;

    }


    private void loaduserlist(View view){
        AppDatabase db = AppDatabase.getDbInstance(view.getContext());
        List<Musicas> musicasList = db.musicasDAO().getmusica();


        if (musicasList != null){
            musicasListAdapter = new MusicasListAdapter(musicasList);
        }

    }

    private void initRecyclerView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.rcmusicas);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(musicasListAdapter);
    }

}