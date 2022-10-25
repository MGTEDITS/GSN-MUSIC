package com.example.gsnmusic.db;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "Username")
    public String username;

    @ColumnInfo(name = "Email")
    public String email;

    @ColumnInfo(name = "Password")
    public String password;

}
