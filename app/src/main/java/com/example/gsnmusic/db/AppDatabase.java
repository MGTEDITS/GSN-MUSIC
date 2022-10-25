package com.example.gsnmusic.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Musicas.class}, version = 1)
    public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract MusicasDAO musicasDAO();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context){

        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class, "BD_GSNMUSIC")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
