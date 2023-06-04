package com.example.psycology_app.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import com.example.psycology_app.MainActivity;
import com.example.psycology_app.R;
import com.example.psycology_app.Stats;
import com.example.psycology_app.databinding.ActivityScoreBinding;

public class ScoreActivity extends AppCompatActivity {

    ActivityScoreBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String setName = getIntent().getStringExtra("set");
        int totalScore = getIntent().getIntExtra("total", 0);
        int correctAns = getIntent().getIntExtra("score", 0);
        int values = totalScore - correctAns;
        binding.total.setText(String.valueOf(totalScore));
        binding.yes.setText(String.valueOf(correctAns));


        if (setName != null && setName.equals("Ваше мировозрение")) {

            if(values <= 4)
            {
                binding.Answ.setText("Вы себя чувствуете счастливым/счастливой и находитесь в позитивном настроении. Это замечательно, так как позитивное состояние помогает улучшить качество жизни и достичь личных целей.");

            } else if (values < 7 || values >= 5) {

                binding.Answ.setText("Вы относитесь к своей работе или учебе с положительным настроем и наслаждаетесь процессом. Это важно, так как позитивное отношение к работе или учебе способствует повышению мотивации, продуктивности и достижению успеха.");

            } else if (values >= 7) {
                binding.Answ.setText("Вы имеете хорошие отношения с близкими людьми и общаетесь с ними открыто и искренне. Это является ключевым аспектом здоровых и поддерживающих отношений, которые способствуют счастью и благополучию.");
            }
            // Остальной код здесь
            // ...
        }

       

        binding.quitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.btnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoreActivity.this, Stats.class);
                intent.putExtra("score", correctAns);
                intent.putExtra("total", correctAns);
                startActivity(intent);
            }
        });
    }

}