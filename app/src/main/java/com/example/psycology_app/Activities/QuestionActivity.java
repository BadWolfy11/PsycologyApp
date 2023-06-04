package com.example.psycology_app.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.example.psycology_app.Models.QuestionModel;
import com.example.psycology_app.R;
import com.example.psycology_app.databinding.ActivityQuestionBinding;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {

    ArrayList<QuestionModel> list = new ArrayList<>();
    private int count = 0;
    private int position = 0;
    private int score = 0;
    ActivityQuestionBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String setName = getIntent().getStringExtra("set");

        if(setName.equals("Ваше мировозрение"))
        {
            setOne();
        } else if (setName.equals("Какой вы человек?")) {
            setTwo();
        }

        for(int i = 0; i<4; i++)
        {

            playAnimation(binding.question, 0, list.get(position).getQuestion());
            binding.container.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkBtn((Button) view);
                }
            });
        }

        binding.btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.btnnext.setEnabled(false);
                binding.btnnext.setAlpha((float) 0.3);
                enableOption(true);
                position ++;
                if(position == list.size())
                {
                    Intent intent = new Intent(QuestionActivity.this, ScoreActivity.class);
                    intent.putExtra("score", score);
                    intent.putExtra("total", list.size());
                    startActivity(intent);
                    //finish();

                }
                count = 0;
                    playAnimation(binding.question, 0, list.get(position).getQuestion());

            }
        });
    }


    private void playAnimation(View view, int value, String data) {
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(@NonNull Animator animator) {
                        if (value == 0 && count < 4)
                        {
                            String option = "";
                            if ( count == 0)
                            {
                                option = list.get(position).getOptionA();
                            } else if ( count == 1)
                            {
                                option = list.get(position).getOptionB();
                            }
                            else if ( count == 2)
                            {
                                option = list.get(position).getOptionC();
                            } else if (count == 3) {

                                option = list.get(position).getOptionD();
                            }
                            playAnimation(binding.container.getChildAt(count), 0, option);
                            count ++;
                        }
                    }

                    @Override
                    public void onAnimationEnd(@NonNull Animator animator) {
                        if (value == 0) {
                            try {
                                ((TextView) view).setText(data);
                                binding.totaltest.setText(position + 1 + "/" + list.size());
                            } catch (Exception e) {
                                ((Button) view).setText(data);
                            }
                            view.setTag(data);
                            playAnimation(view, 1, data); // Передайте значение 1 в качестве аргумента value
                        }
                    }


                    @Override
                    public void onAnimationCancel(@NonNull Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(@NonNull Animator animator) {

                    }
                });
    }

    private void enableOption(boolean enable) {
        for(int i = 0; i<4; i++)
        {
            binding.container.getChildAt(i).setEnabled(enable);

            if(enable)
            {

                //selectedOption.setBackgroundResource(R.drawable.rightanswer);
                binding.container.getChildAt(i).setBackgroundResource(R.drawable.btn_op);

            }
        }

    }
    private void checkBtn(Button selectedOption) {
        binding.btnnext.setEnabled(false);
        binding.btnnext.setAlpha(1);


        for(int i = 0; i<4; i++) {
            final int index = i;
            binding.container.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (view.isEnabled()) {
                        for(int index = 0; index<4; index++) {
                            binding.btnnext.setEnabled(true);
                            binding.container.getChildAt(index).setBackgroundResource(R.drawable.btn_op);
                        }

                        binding.container.getChildAt(index).setBackgroundResource(R.drawable.btn_disable);
                        checkAnswer((Button) view);
                    }
                }
            });
        }
    }

    private void checkAnswer(Button selectedOption) {
        binding.btnnext.setEnabled(true);
        binding.btnnext.setAlpha(1);

        if (selectedOption.getText().toString().equals(list.get(position).getCorrectAnswer())){
        score ++;
        }
    }


    private void setTwo() {
        list.add(new QuestionModel("Как вы чувствовали себя на этой неделе?","Я не чувствую себя расстроенной, печальной. ", "Я счастлива", "У меня много забот, но я не переживаю", "У меня такая же жизнь как и у других", "Я не чувствую себя расстроенной, печальной. "));
        list.add(new QuestionModel("Как вы чувствовали себя на этой неделе?", "Я не чувствую себя расстроенной, печальной.", "Я счастлива.", "У меня много забот, но я не переживаю.", "У меня такая же жизнь как и у других.", "Я не чувствую себя расстроенной, печальной."));
        list.add(new QuestionModel("Какие цели вы хотите достичь в ближайшем будущем?", "Я хочу достичь успеха в карьере.", "Я хочу улучшить свои отношения с семьей и друзьями.", "Я хочу улучшить свое физическое здоровье и фитнес.", "Я хочу развивать свои творческие навыки.", "Я хочу достичь успеха в карьере."));
        list.add(new QuestionModel("Как вы проводите свободное время?", "Я предпочитаю заниматься спортом или физическими упражнениями.", "Я люблю читать или изучать что-то новое.", "Я провожу время с семьей и друзьями.", "Я занимаюсь творческими или хобби проектами.", "Я провожу время с семьей и друзьями."));
        list.add(new QuestionModel("Как вы обычно реагируете на стрессовые ситуации?", "Я стараюсь сохранять спокойствие и искать позитивные решения.", "Я чувствую себя подавленной и неуверенной.", "Я ищу поддержку у близких людей.", "Я пытаюсь справиться с проблемой самостоятельно.", "Я стараюсь сохранять спокойствие и искать позитивные решения."));
        list.add(new QuestionModel("Как вы относитесь к изменениям в своей жизни?", "Я открыта для изменений и готова адаптироваться.", "Я предпочитаю стабильность и предсказуемость.", "Я нервничаю и беспокоюсь о неизвестности.", "Я считаю, что изменения могут быть хорошими возможностями.", "Я открыта для изменений и готова адаптироваться."));
        list.add(new QuestionModel("Как вы обычно реагируете на критику?", "Я принимаю критику как возможность для роста и улучшений.", "Я чувствую себя обиженной и оскорбленной.", "Я стараюсь защищаться и оправдываться.", "Я обычно принимаю критику лично.", "Я принимаю критику как возможность для роста и улучшений."));
        list.add(new QuestionModel("Какую роль играет сон в вашей жизни?", "Я стараюсь обеспечивать себе достаточное количество сна.", "У меня проблемы со сном и я часто не высыпаюсь.", "Я не обращаю особого внимания на свой сон.", "Сон важен для моего физического и эмоционального благополучия.", "Я стараюсь обеспечивать себе достаточное количество сна."));


    }

    private void setOne() {

        list.add(new QuestionModel("Как вы относитесь к своей работе или учебе?", "Я люблю свою работу или учебу и наслаждаюсь процессом.", "Я чувствую себя удовлетворенным и достигаю успехов.", "Я чувствую себя подавленным или недовольным.", "Я стараюсь делать все, что от меня зависит, чтобы достичь успеха.", "Я люблю свою работу или учебу и наслаждаюсь процессом."));
        list.add(new QuestionModel("Как вы обычно реагируете на неожиданные изменения планов?", "Я гибко адаптируюсь к новым обстоятельствам и ищу решения.", "Я чувствую себя раздраженным и смущенным.", "Я стараюсь найти альтернативные варианты или планы.", "Я испытываю страх или тревогу из-за неожиданных изменений.", "Я гибко адаптируюсь к новым обстоятельствам и ищу решения."));
        list.add(new QuestionModel("Как вы относитесь к своему внешнему виду?", "Я заботлюсь о своем внешнем виде и стараюсь выглядеть хорошо.", "Я чувствую себя комфортно и уверенно в своем внешнем виде.", "Я иногда беспокоюсь о своем внешнем виде и сравниваю себя с другими.", "Я считаю, что внешность не имеет большого значения.", "Я заботлюсь о своем внешнем виде и стараюсь выглядеть хорошо."));
        list.add(new QuestionModel("Как вы обычно проводите выходные или отпуск?", "Я предпочитаю активный отдых и новые приключения.", "Я люблю расслабляться и отдыхать в тишине и спокойствии.", "Я провожу время с друзьями или семьей.", "Я занимаюсь хобби или увлечениями.", "Я провожу время с друзьями или семьей."));
        list.add(new QuestionModel("Как вы относитесь к своим эмоциям?", "Я умею распознавать и выражать свои эмоции.", "Я часто чувствую себя эмоционально неустойчивым.", "Я стараюсь справляться с негативными эмоциями и находить способы их управления.", "Я редко показываю свои эмоции и склонен скрывать их.", "Я умею распознавать и выражать свои эмоции."));

        list.add(new QuestionModel("Как вы относитесь к социальным ситуациям и общению с людьми?", "Я наслаждаюсь общением с другими и легко нахожу общий язык.", "Я чувствую себя комфортно в социальных ситуациях и умею поддерживать беседу.", "Я бываю неуверенным и неудобно в общении с незнакомыми людьми.", "Я предпочитаю уединение и меньше общаюсь с людьми.", "Я наслаждаюсь общением с другими и легко нахожу общий язык."));
        list.add(new QuestionModel("Как вы справляетесь с стрессом?", "Я использую различные стратегии релаксации и самоуправления для снятия стресса.", "Я обращаюсь к друзьям или близким людям за поддержкой.", "Я обычно стараюсь справиться с проблемами самостоятельно.", "Я чувствую себя парализованным или паникующим в стрессовых ситуациях.", "Я использую различные стратегии релаксации и самоуправления для снятия стресса."));


    }
}