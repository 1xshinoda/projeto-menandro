package com.example.projetomenandro.Activity;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetomenandro.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class jogo extends AppCompatActivity {

    private TextView tvInstruction;
    private TextView tvTitle;
    private Button btnRestart;
    private ImageButton imageButton;
    private String[] animals = {"cachorro", "gato", "peixe", "pássaro"};
    private int currentAnimalIndex = 0;
    private boolean gameActive = true;
    private int wrongAttempts = 0;
    private final int MAX_WRONG_ATTEMPTS = 3; // Limite de tentativas erradas



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encontreoanimal);

        tvTitle = findViewById(R.id.tv_title);
        tvInstruction = findViewById(R.id.tv_instruction);
        ImageView ivDog = findViewById(R.id.iv_dog);
        ImageView ivCat = findViewById(R.id.iv_cat);
        ImageView ivBird = findViewById(R.id.iv_bird);
        ImageView ivFish = findViewById(R.id.iv_fish);
        btnRestart = findViewById(R.id.btn_restart);
        imageButton = findViewById(R.id.voltarmenu1);

        shuffleAnimals(); // Embaralha a lista de animais
        animateTitle();
        updateInstruction();
        chooseRandomAnimal();

        View.OnClickListener animalClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gameActive) {
                    String selectedAnimal = getSelectedAnimal(v.getId());
                    checkAnimal(selectedAnimal, v);
                }
            }
        };

        ivDog.setOnClickListener(animalClickListener);
        ivCat.setOnClickListener(animalClickListener);
        ivBird.setOnClickListener(animalClickListener);
        ivFish.setOnClickListener(animalClickListener);

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(jogo.this, MainActivityMenu.class);
                startActivity(intent);
            }
        });
    }

    private void shuffleAnimals() {
        ArrayList<String> animalList = new ArrayList<>(Arrays.asList(animals));
        Collections.shuffle(animalList);
        animalList.toArray(animals);
    }



    // Método para escolher um animal aleatório
    private void chooseRandomAnimal() {
        Random random = new Random();
        currentAnimalIndex = random.nextInt(animals.length);
    }

    private String getSelectedAnimal(int viewId) {
        if (viewId == R.id.iv_dog) {
            return "cachorro";
        } else if (viewId == R.id.iv_cat) {
            return "gato";
        } else if (viewId == R.id.iv_bird) {
            return "pássaro";
        } else if (viewId == R.id.iv_fish) {
            return "peixe";
        } else {
            return "";
        }
    }

    private void checkAnimal(String selectedAnimal, View view) {
        if (selectedAnimal.equals(animals[currentAnimalIndex])) {
            showToastMessage("Correto! Muito bem!");
            animateCorrectChoice(view);
            if (currentAnimalIndex < animals.length - 1) {
                currentAnimalIndex++;
                updateInstruction();
            } else {
                tvInstruction.setText("Parabéns! Você encontrou todos os animais!");
                btnRestart.setVisibility(View.VISIBLE);
                gameActive = false;
            }
            // Resetar contagem de tentativas erradas
            wrongAttempts = 0;
        } else {
            showToastMessage("Tente novamente.");
            animateIncorrectChoice(view);
            // Incrementar contagem de tentativas erradas
            wrongAttempts++;
            // Verificar se excedeu o limite de tentativas erradas
            if (wrongAttempts >= MAX_WRONG_ATTEMPTS) {
                showToastMessage("Você excedeu o limite de tentativas erradas. O jogo será reiniciado.");
                restartGame();
            }
        }
    }

    private void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void animateCorrectChoice(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f);
        animator.setDuration(1000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }

    private void animateIncorrectChoice(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", 0f, 50f, -50f, 0f);
        animator.setDuration(500);
        animator.start();
    }

    private void restartGame() {
        shuffleAnimals(); // Embaralha a lista de animais novamente
        currentAnimalIndex = 0;
        updateInstruction();
        btnRestart.setVisibility(View.GONE);
        gameActive = true;
    }

    private void updateInstruction() {
        tvInstruction.setText("Toque no " + animals[currentAnimalIndex]);
    }

    private void animateTitle() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(tvTitle, "alpha", 0f, 1f);
        animator.setDuration(2000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }
}
