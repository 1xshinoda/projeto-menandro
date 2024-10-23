package com.example.projetomenandro.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projetomenandro.R;

public class AulasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aulas);

        // Comentando a inicialização dos vídeos para rodar sem arquivos
        /*
        VideoView videoView1 = findViewById(R.id.videoView1);
        VideoView videoView2 = findViewById(R.id.videoView2);

        // Evitar erros ao tentar carregar vídeos
        try {
            Uri videoUri1 = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video1);
            videoView1.setVideoURI(videoUri1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Uri videoUri2 = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video2);
            videoView2.setVideoURI(videoUri2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        */

        // Inicializar botões de áudio, mas sem arquivos de áudio
        /*
        MediaPlayer mediaPlayer1 = MediaPlayer.create(this, R.raw.audio1);
        Button playAudio1 = findViewById(R.id.btnPlayAudio1);
        Button pauseAudio1 = findViewById(R.id.btnPauseAudio1);

        playAudio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mediaPlayer1.isPlaying()) {
                    mediaPlayer1.start();
                }
            }
        });

        pauseAudio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer1.isPlaying()) {
                    mediaPlayer1.pause();
                }
            }
        });

        MediaPlayer mediaPlayer2 = MediaPlayer.create(this, R.raw.audio2);
        Button playAudio2 = findViewById(R.id.btnPlayAudio2);
        Button pauseAudio2 = findViewById(R.id.btnPauseAudio2);

        playAudio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mediaPlayer2.isPlaying()) {
                    mediaPlayer2.start();
                }
            }
        });

        pauseAudio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer2.isPlaying()) {
                    mediaPlayer2.pause();
                }
            }
        });
        */
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*
        if (mediaPlayer1 != null) {
            mediaPlayer1.release();
        }
        if (mediaPlayer2 != null) {
            mediaPlayer2.release();
        }
        */
    }
}