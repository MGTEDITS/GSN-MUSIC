package com.example.gsnmusic.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Musicas {

        @PrimaryKey(autoGenerate = true)
        public int uid;

        @ColumnInfo(name = "musica")
        public String nomemusica;

        @ColumnInfo(name = "artista")
        public String artista;

}
