package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Halaman_depan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_depan);
    }

    public void Masuk(View view) {
        Intent Masuk = new
                Intent(Halaman_depan.this, Halaman_pilihan.class);
        startActivity(Masuk);
    }
}
