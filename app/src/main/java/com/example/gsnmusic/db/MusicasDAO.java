package com.example.gsnmusic.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MusicasDAO {

    @Query("SELECT * FROM Musicas")
    public List<Musicas> getmusica();

    @Insert
    void insertUser(Musicas... music);

    @Delete
    void delete(Musicas music);

}
