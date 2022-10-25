package com.example.gsnmusic.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User WHERE Username LIKE:usernames")
    public List<User> getuser(String usernames);

    @Insert
    void insertUser(User... users);

    @Delete
    void delete(User user);

    @Query("DELETE FROM User WHERE Username LIKE:username")
    public void deleteuser(String username);

    @Query("UPDATE User SET Password = :password WHERE Email LIKE:email")
    public void updateUser(String email, String password);

}
