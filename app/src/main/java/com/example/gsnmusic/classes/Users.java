package com.example.gsnmusic.classes;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Users {
    String username;
    String email;
    String password;

    public Users() {
    }

    public Users(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = hashPassword(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String hashPassword(String password){ try
    {
        // Create MessageDigest instance for MD5
        MessageDigest md = MessageDigest.getInstance("MD5");

        // Add password bytes to digest
        md.update(password.getBytes());

        // Get the hash's bytes
        byte[] bytes = md.digest();

        // This bytes[] has bytes in decimal format. Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        // Get complete hashed password in hex format
        password = sb.toString();
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
    }

        return password;
    }


    public boolean verifyPassword (Object users, String pass){

        Users users1 = (Users) users;
        Log.i("TAG", "verifyPassword: "+ pass);
        System.out.println("--------------->"+hashPassword(pass));
        System.out.println("--------------->"+users1.getPassword());

        return hashPassword(pass).equals(users1.getPassword());
    }

    @Override
    public String toString() {
        return "Users{" +
                "utilizador='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }



    public void setPasswordEncrypt(String password) {
        this.password = hashPassword(password);
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
