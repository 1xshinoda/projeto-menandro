package com.example.projetomenandro.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetomenandro.R;

import java.util.ArrayList;
import java.util.Collections;


public class fase1 extends AppCompatActivity {

    private ArrayList<Integer> images;
    private int firstCard, secondCard;
    private ImageView firstImage, secondImage;

    private GridLayout GrindLayout;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fase1);

        GridLayout gridLayout = findViewById(R.id.grindLayout);
        images = new ArrayList<>();
        images.add(R.drawable.bob);
        images.add(R.drawable.mario);
        images.add(R.drawable.sonic);
        images.add(R.drawable.bob);
        images.add(R.drawable.mario);
        images.add(R.drawable.sonic);

        Collections.shuffle(images);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView imageView = (ImageView) gridLayout.getChildAt(i);
            imageView.setTag(i);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (int) v.getTag();
                    flipCard((ImageView) v, position);
                }
            });
        }
    }

    private void flipCard(ImageView imageView, int position) {
        if (firstImage == null) {
            firstImage = imageView;
            firstImage.setImageResource(images.get(position));
            firstCard = position;
        } else if (secondImage == null && imageView != firstImage) {
            secondImage = imageView;
            secondImage.setImageResource(images.get(position));
            secondCard = position;
            checkMatch();
        }
    }

    private void checkMatch() {
        if (images.get(firstCard).equals(images.get(secondCard))) {
            score++;
            if (score == 3) {
                Toast.makeText(this, "Parabéns! Você ganhou!", Toast.LENGTH_SHORT).show();
                showCongratulations();
            }
            resetCards();
        } else {
            Toast.makeText(this, "Tente novamente!", Toast.LENGTH_SHORT).show();
            imageViewFlipBack();
        }
    }

    private void resetCards() {
        firstImage = null;
        secondImage = null;
    }

    private void imageViewFlipBack() {
        firstImage.postDelayed(new Runnable() {
            @Override
            public void run() {
                firstImage.setImageResource(R.drawable.card_back);
                secondImage.setImageResource(R.drawable.card_back);
                resetCards();
            }
        }, 1000);
    }

    private void showCongratulations() {
        Intent intent = new Intent(this, Congratulations.class);
        startActivity(intent);
        finish();
    }
}