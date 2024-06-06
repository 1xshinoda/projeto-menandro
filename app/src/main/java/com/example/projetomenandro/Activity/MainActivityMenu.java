package com.example.projetomenandro.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetomenandro.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivityMenu extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageButton buttonDrawerToggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        buttonDrawerToggle = findViewById(R.id.buttonDrawerToggle);
        navigationView = findViewById(R.id.navigationView);

        buttonDrawerToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });


        View headerView = navigationView.getHeaderView(0);
        ImageView userImage = headerView.findViewById(R.id.userImage);
        TextView textUsername = headerView.findViewById(R.id.textUsername);

        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivityMenu.this,textUsername.getText(), Toast.LENGTH_SHORT).show();
            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();

                if (itemId == R.id.navMenu){
                    Toast.makeText(MainActivityMenu.this, "Menu Clicked", Toast.LENGTH_SHORT).show();
                }

                if (itemId == R.id.navCart){
                    Toast.makeText(MainActivityMenu.this, "Cart Clicked", Toast.LENGTH_SHORT).show();
                }

                if (itemId == R.id.navFavourite){
                    Toast.makeText(MainActivityMenu.this, "Favourite Clicked", Toast.LENGTH_SHORT).show();
                }

                if (itemId == R.id.navOrders){
                    Toast.makeText(MainActivityMenu.this, "Orders Clicked", Toast.LENGTH_SHORT).show();
                }

                if (itemId == R.id.navHistory){
                    Toast.makeText(MainActivityMenu.this, "History Clicked", Toast.LENGTH_SHORT).show();
                }

                if (itemId == R.id.navFeedBack){
                    Toast.makeText(MainActivityMenu.this, "FeedBack Clicked", Toast.LENGTH_SHORT).show();
                }

                if (itemId == R.id.navTerms){
                    Toast.makeText(MainActivityMenu.this, "Terms Clicked", Toast.LENGTH_SHORT).show();
                }

                if (itemId == R.id.navContact){
                    Toast.makeText(MainActivityMenu.this, "Contact Clicked", Toast.LENGTH_SHORT).show();
                }

                if (itemId == R.id.navShare){
                    Toast.makeText(MainActivityMenu.this, "Share Clicked", Toast.LENGTH_SHORT).show();
                }

                drawerLayout.close();

                return false;
            }
        });

    }
}
