package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Halaman_Katalog extends AppCompatActivity {

    private TextView textViewTotal1;
    private TextView textViewTotal2;
    private TextView textViewTotal3;
    private TextView textViewTotal4;
    private int points1 = 1;
    private int points2 = 1;
    private int points3 = 1;
    private int points4 = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_katalog);

        textViewTotal1 = findViewById(R.id.textViewTotal1);
        textViewTotal2 = findViewById(R.id.textViewTotal2);
        textViewTotal3 = findViewById(R.id.textViewTotal3);
        textViewTotal4 = findViewById(R.id.textViewTotal4);

        updatePointsDisplay();
        TextView textView46 = findViewById(R.id.textView46);
        int userPoints = Halaman_manage_poin_pengguna.getUserPoints(this);
        textView46.setText(String.valueOf(userPoints));

    }

    public void increasetotal1(View view) {
        points1++;
        updatePointsDisplay();
    }

    public void decreasetotal1(View view) {
        points1--;
        updatePointsDisplay();
    }
    public void increasetotal2(View view) {
        points2++;
        updatePointsDisplay();
    }

    public void decreasetotal2(View view) {
        points2--;
        updatePointsDisplay();
    }
    public void increasetotal3(View view) {
        points3++;
        updatePointsDisplay();
    }

    public void decreasetotal3(View view) {
        points3--;
        updatePointsDisplay();
    }
    public void increasetotal4(View view) {
        points4++;
        updatePointsDisplay();
    }

    public void decreasetotal4(View view) {
        points4--;
        updatePointsDisplay();
    }



    private void updatePointsDisplay() {
        textViewTotal1.setText(String.valueOf(points1));
        textViewTotal2.setText(String.valueOf(points2));
        textViewTotal3.setText(String.valueOf(points3));
        textViewTotal4.setText(String.valueOf(points4));
    }


// Method for intent when the click button is clicked
    public void klik(View view) {
        Intent login = new Intent(Halaman_Katalog.this, Halaman_diskon_traveloka.class);
        startActivity(login);
    }

    // Method for intent when the back button is clicked
    public void kembali(View view) {
        Intent login = new Intent(Halaman_Katalog.this, Halaman_Utama.class);
        startActivity(login);
    }
}



