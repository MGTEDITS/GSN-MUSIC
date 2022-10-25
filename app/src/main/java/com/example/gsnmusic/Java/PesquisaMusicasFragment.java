package com.example.gsnmusic.Java;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.gsnmusic.MusicasListAdapter;
import com.example.gsnmusic.R;
import com.example.gsnmusic.db.AppDatabase;
import com.example.gsnmusic.db.Musicas;

import java.util.List;

public class PesquisaMusicasFragment extends Fragment {

    View view;
    EditText searchMusic;
    private MusicasListAdapter musicasListAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pesquisa_musicas, container, false);
        loaduserlist(view);
        initRecyclerView(view);

        searchMusic = view.findViewById(R.id.txtsearchAllMusic);

        searchMusic.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() == 0){
                    loaduserlist(view);
                    initRecyclerView(view);
                }
                musicasListAdapter.getMusicFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

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
        RecyclerView recyclerView = view.findViewById(R.id.rcallMusic);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(musicasListAdapter);
    }
}