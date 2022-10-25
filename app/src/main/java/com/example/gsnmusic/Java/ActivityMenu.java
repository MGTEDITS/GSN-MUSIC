package com.example.gsnmusic.Java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.example.gsnmusic.R;
import com.example.gsnmusic.databinding.ActivityMenuBinding;

public class ActivityMenu extends AppCompatActivity {

    ActivityMenuBinding binding;
    public String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        binding=ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new MenuFragment());

        username = getIntent().getStringExtra("username");

        boolean test = getIntent().getBooleanExtra("top", false);

        if (test){
            Bundle bundle = new Bundle();
            bundle.putString("username", username);
            ProfileFragment profileFragment = new ProfileFragment();
            profileFragment.setArguments(bundle);
            replaceFragment(profileFragment);
        }

        Log.i("TAG", "onCreate: " + username);

        binding.bottomNavigationView.setOnItemSelectedListener(item ->{

            switch (item.getItemId()){
                case R.id.home:
                    replaceFragment(new MenuFragment());
                    break;
                case R.id.pesquisa:
                    replaceFragment(new PesquisaMusicasFragment());
                    break;
                case R.id.library:
                    break;
                case R.id.perfil:
                    Bundle bundle = new Bundle();
                    bundle.putString("username", username);
                    ProfileFragment profileFragment = new ProfileFragment();
                    profileFragment.setArguments(bundle);
                    replaceFragment(profileFragment);
                    break;
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment);
        fragmentTransaction.commit();
    }
}