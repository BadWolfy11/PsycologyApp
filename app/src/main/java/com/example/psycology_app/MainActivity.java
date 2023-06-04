package com.example.psycology_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.psycology_app.Activities.SetsActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //android.App.Application
    //AppCompatActivity
//    private static MainActivity instance;
//
//
//    private List<String> totalCorrectAnswers = new ArrayList<>();
//    private int totalScore = 0;
//
//    public static MainActivity getInstance() {
//        return instance;
//    }
//
//    public  List<String> getTotalCorrectAnswers() {
//        return this.totalCorrectAnswers;
//    }
//
//    public void setTotalCorrectAnswers(List<String> totalCorrectAnswers) {
//        this.totalCorrectAnswers = totalCorrectAnswers;
//    }
//
//    public int getTotalScore() {
//        return this.totalScore;
//    }
//
//    public void setTotalScore(int totalScore) {
//        this.totalScore = totalScore;
//    }

//    @Override
//    public static Context getApplicationContext() {
//        return super.getApplicationContext()
//    }

    Button btnd;
    CardView first, second, third, fourth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instance = this;

        first = findViewById(R.id.first);
        second = findViewById(R.id.second);
        third = findViewById(R.id.third);
        fourth = findViewById(R.id.fourth);

        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SetsActivity.class);
                startActivity(intent);
            }
        });
    }
}