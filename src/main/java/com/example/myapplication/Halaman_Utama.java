package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class Halaman_Utama extends AppCompatActivity {

    private int points1 = 0;
    private TextView textViewtotal1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_utama);

        ImageView imageView35 = findViewById(R.id.imageView35);
        textViewtotal1 = findViewById(R.id.textViewTotal1);

        updatePointsDisplay();

        imageView35.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer();
            }
        });

        TextView textViewutama = findViewById(R.id.textViewutama);
        int userPoints = Halaman_manage_poin_pengguna.getUserPoints(this);
        textViewutama.setText(String.valueOf(userPoints));

    }

    public void increasetotal1(View view) {
        points1++;
        updatePointsDisplay();
    }

    public void decreasetotal1(View view) {
        points1--;
        updatePointsDisplay();
    }

    private void updatePointsDisplay() {
        textViewtotal1.setText(String.valueOf(points1));
    }

    private void openDrawer() {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.hal_view);
        drawerLayout.openDrawer(GravityCompat.END);
    }

    public void barcode(View view) {
        Intent barcode = new Intent(Halaman_Utama.this, Halaman_Foto.class);
        startActivity(barcode);
    }

    public void goreedem(View view) {
        Intent goreedem = new Intent(Halaman_Utama.this, Halaman_Katalog.class);
        startActivity(goreedem);
    }

    public void goextra(View view) {
        Intent goextra = new Intent(Halaman_Utama.this, Halaman_Undian.class);
        startActivity(goextra);
    }

    public void goroyal(View view) {
        Intent goroyal = new Intent(Halaman_Utama.this, Halaman_Penukaran_Periode.class);
        startActivity(goroyal);
    }
}