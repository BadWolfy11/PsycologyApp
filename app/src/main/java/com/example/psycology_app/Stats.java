package com.example.psycology_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.psycology_app.databinding.ActivityStatsBinding;

import java.util.ArrayList;
import java.util.List;

public class Stats extends AppCompatActivity {

    List<String> myList = new ArrayList<>();

    private String savedValue;

    Button calculateButton;
    float count1;
    String grade;
    TextView gradt;
    ActivityStatsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStatsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        calculateButton = findViewById(R.id.calculateButton);
        gradt = findViewById(R.id.grad);

        int count = getIntent().getIntExtra("total", 0);
        int correctAns = getIntent().getIntExtra("score", 0);

        binding.grad.setText(String.valueOf(correctAns));

        if (correctAns >= 7) {
            grade = "5";
        } else if (correctAns >= 5) {
            grade = "4";
        } else if (correctAns >= 3) {
            grade = "3";
        }

        String countStr = Integer.toString(count);
        myList.add(countStr);

        count1 = 0;
        for (int i = 0; i < myList.size(); i++) {
            count1 += Integer.parseInt(myList.get(i));
        }

        count1 = count1 / myList.size();
        String countAvgStr = Float.toString(count1);
        gradt.setText("");
        gradt.append("Average Count: " + countAvgStr + "\n");
        gradt.append("Grade: " + grade);

        MyViewModel viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        LiveData<String> valueLiveData = viewModel.getValue();

        valueLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String value) {
                myList.clear();


                if (value != null) {
                    myList.add(value);
                }
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String countAvgStr = Float.toString(count1);
                gradt.setText("");
                gradt.append("Average Count: " + countAvgStr + "\n");
                gradt.append("Grade: " + grade);
            }
        });
    }
}

