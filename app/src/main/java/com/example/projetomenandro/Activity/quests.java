package com.example.projetomenandro.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetomenandro.R;

import java.util.ArrayList;
import java.util.List;

public class quests extends AppCompatActivity {

    private ImageView questionImage;
    private TextView questionText;
    private LinearLayout answersLayout;
    private List<Question> questions;
    private int currentQuestionIndex;
    private int score;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionario);

        questionImage = findViewById(R.id.question_image);
        questionText = findViewById(R.id.question_text);
        answersLayout = findViewById(R.id.answers_layout);
        imageButton = findViewById(R.id.voltarmenu1);

        questions = getQuestions();
        currentQuestionIndex = 0;
        score = 0;

        showQuestion(currentQuestionIndex);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(quests.this, MainActivityMenu.class);
                startActivity(intent);
            }
        });
    }



    private List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();
        // Adicione as perguntas e respostas aqui
        questions.add(new Question(R.drawable.quadrado, "Quantos lados tem essa forma geometrica?", new String[]{"3", "4", "5"}, 1));
        questions.add(new Question(R.drawable.circulo, "Qual o nome da forma geometrica acima?", new String[]{"Quadrado", "Círculo", "Triangulo", "Hexágono"}, 1));
        questions.add(new Question(R.drawable.hexagono, "Quantos lados tem essa forma geometrica?", new String[]{"2", "4", "6"}, 2));
        questions.add(new Question(R.drawable.triangulo, "Qual o nome da forma geometrica acima?", new String[]{"Hexágono", "Triangulo", "Círculo", "Quadrado"}, 1));
        questions.add(new Question(R.drawable.trianguloazul, "Qual a cor desse objeto?", new String[]{"Azul", "Branco", "Cinza", "Amarelo"}, 0));


        return questions;
    }



    private void showQuestion(int questionIndex) {
        Question question = questions.get(questionIndex);
        questionImage.setImageResource(question.getImageResId());
        questionText.setText(question.getText());

        answersLayout.removeAllViews();

        for (int i = 0; i < question.getAnswers().length; i++) {
            Button answerButton = new Button(this);
            answerButton.setText(question.getAnswers()[i]);
            answerButton.setBackground(getResources().getDrawable(R.drawable.rounded_button));
            answerButton.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            int finalI = i;
            answerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(finalI);
                }
            });
            answersLayout.addView(answerButton);
        }
    }

    private void checkAnswer(int selectedAnswerIndex) {
        Question question = questions.get(currentQuestionIndex);
        if (selectedAnswerIndex == question.getCorrectAnswerIndex()) {
            score++;
        }

        if (currentQuestionIndex < questions.size() - 1) {
            currentQuestionIndex++;
            showQuestion(currentQuestionIndex);
        } else {
            showResult();
        }
    }

    private void showResult() {
        // Exiba o resultado aqui
        questionText.setText("Você acertou " + score + " de " + questions.size() + " perguntas!");
        questionImage.setVisibility(View.GONE);
        answersLayout.removeAllViews();
    }


}
