package com.example.astro;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import com.example.astro.ui.login.LoginActivity;
import com.example.astro.ui.login.RegisterActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        FirebaseAuth appAuth = FirebaseAuth.getInstance();
        UserInfo user = appAuth.getCurrentUser();

        if (user == null) {
            Intent registerIntent = new Intent(MainActivity.this, WelcomeActivity.class);
            registerIntent.putExtra("hello", 1);
            MainActivity.this.startActivity(registerIntent);
            finish();
        } else {
            Intent appIntent = new Intent(MainActivity.this, AppActivity.class);
            appIntent.putExtra("hello", 1);
            MainActivity.this.startActivity(appIntent);
            finish();
        }

        /*FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef;
        myRef = database.getReference("users");
        myRef.setValue("Hello");

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };
        Handler handler = new Handler();
        handler.postDelayed(runnable, 2000);*/
    }
}
