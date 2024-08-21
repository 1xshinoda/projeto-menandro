package com.example.projetomenandro.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetomenandro.R;
import com.example.projetomenandro.Util.configuraBD;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
public class MainActivityMenu extends AppCompatActivity {
    private FirebaseAuth auth;
    DrawerLayout drawerLayout;
    ImageButton buttonDrawerToggle;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = configuraBD.fireauth();

        drawerLayout = findViewById(R.id.drawerLayout);
        buttonDrawerToggle = findViewById(R.id.buttonDrawerToggle);
        navigationView = findViewById(R.id.navigationView);

        buttonDrawerToggle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }

        });
        Button buttonJoguinho = findViewById(R.id.joguinho);
        buttonJoguinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityMenu.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button buttonJoguinho2 = findViewById(R.id.joguinho2);
        buttonJoguinho2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent para a nova atividade desejada
                Intent intent = new Intent(MainActivityMenu.this, jogo.class);
                startActivity(intent);
            }
        });

        Button buttonJoguinho3 = findViewById(R.id.joguinho3);
        buttonJoguinho3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent para a nova atividade desejada
                Intent intent = new Intent(MainActivityMenu.this, quests.class);
                startActivity(intent);
            }
        });


        View headerView = navigationView.getHeaderView(0);
        ImageView userImage = headerView.findViewById(R.id.userImage);
        TextView textUsername = headerView.findViewById(R.id.textUsername);

        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivityMenu.this, textUsername.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.navMenu) {
                    Toast.makeText(MainActivityMenu.this, "Perfil Clicked", Toast.LENGTH_SHORT).show();
                } else if (itemId == R.id.navCart) {
                    Toast.makeText(MainActivityMenu.this, "Sobre Clicked", Toast.LENGTH_SHORT).show();
                } else if (itemId == R.id.navFavourite) {
                    Toast.makeText(MainActivityMenu.this, "Favourite Clicked", Toast.LENGTH_SHORT).show();
                } else if (itemId == R.id.navOrders) {
                    Toast.makeText(MainActivityMenu.this, "Orders Clicked", Toast.LENGTH_SHORT).show();

                } else if (itemId == R.id.navFeedBack) {
                    Toast.makeText(MainActivityMenu.this, "FeedBack Clicked", Toast.LENGTH_SHORT).show();
                } else if (itemId == R.id.navTerms) {
                    Toast.makeText(MainActivityMenu.this, "Terms Clicked", Toast.LENGTH_SHORT).show();
                } else if (itemId == R.id.navContact) {
                    Toast.makeText(MainActivityMenu.this, "Contact Clicked", Toast.LENGTH_SHORT).show();
                } else if (itemId == R.id.navShare) {
                    deslogar();
                }
                else if (itemId == R.id.joguinho){
                    Intent intent = new Intent(MainActivityMenu.this, MainActivity.class);
                    startActivity(intent);
                }

                drawerLayout.close();
                return true;
            }
        });
    }

    // Método para deslogar
    public void deslogar() {
        Toast.makeText(this, "Deslogar", Toast.LENGTH_SHORT).show();
        try {
            auth.signOut();
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}