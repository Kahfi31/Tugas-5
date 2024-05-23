package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Halaman_diskon_traveloka extends AppCompatActivity {
    private int points1 = 0; // Start from 0 and increment by 200
    private int points2 = 0; // Start from 0 and increment by 1
    private TextView textView43;
    private TextView textViewtukar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_halaman_diskon_traveloka);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize textView43 and textViewtukar
        textView43 = findViewById(R.id.textView43);
        textViewtukar = findViewById(R.id.textViewtukar);

        // Set textViewtukar and textView43 to start from 0
        textViewtukar.setText(String.valueOf(points2));
        textView43.setText(String.valueOf(points1));
    }

    public void increasetotal1(View view) {
        points1 += 200;
        points2 += 1;
        updatePointsDisplay();
    }

    public void decreasetotal1(View view) {
        if (points1 > 0) {
            points1 -= 200;
            if (points1 < 0) {
                points1 = 0;
            }
            points2 -= 1;
            if (points2 < 0) {
                points2 = 0;
            }
            updatePointsDisplay();
        }
    }

    private void updatePointsDisplay() {
        textView43.setText(String.valueOf(points1));
        textViewtukar.setText(String.valueOf(points2));
    }

    public void kembali(View view) {
        Intent kembali = new Intent(Halaman_diskon_traveloka.this, Halaman_Katalog.class);
        startActivity(kembali);
    }

    public void reedem(View view) {
        Intent reedem = new Intent(Halaman_diskon_traveloka.this, Halaman_Utama.class);
        int poinYangDikurangi = Integer.parseInt(textView43.getText().toString());
        int userPoints = Halaman_manage_poin_pengguna.getUserPoints(this);

        // Ensure that we don't subtract more points than the user has
        if (poinYangDikurangi > userPoints) {
            poinYangDikurangi = userPoints;
        }

        Halaman_manage_poin_pengguna.subtractPoints(this, poinYangDikurangi);
        startActivity(reedem);
    }
}

