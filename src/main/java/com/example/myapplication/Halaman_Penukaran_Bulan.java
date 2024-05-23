package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Halaman_Penukaran_Bulan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_halaman_penukaran_bulan);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Mendapatkan referensi ImageView
        ImageView imageView56 = findViewById(R.id.imageView56);
        ImageView imageView59 = findViewById(R.id.imageView59);

        // Menambahkan onClickListener untuk imageView56
        imageView56.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ImageView56", "ImageView56 diklik");

                // Memuat animasi rotasi 360 derajat
                Animation rotateAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_animation);

                // Set custom interpolator
                rotateAnimation.setInterpolator(new DecelerateAccelerateInterpolator());

                // Set animation listener
                rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        // Tidak melakukan apa-apa
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // Menampilkan kembali ImageView56 setelah animasi selesai
                        imageView56.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // Tidak melakukan apa-apa
                    }
                });

                // Memulai animasi pada imageView59
                imageView59.setVisibility(View.VISIBLE);
                imageView56.startAnimation(rotateAnimation);
                Log.d("ImageView56", "Rotasi dimulai");

                // Sembunyikan imageView56 saat imageView56 diklik
                imageView56.setVisibility(View.GONE);
            }
        });
    }

    public void kembali(View view) {
        Intent kembali = new Intent(Halaman_Penukaran_Bulan.this, Halaman_Undian.class);
        startActivity(kembali);
    }

    public void reedem(View view) {
        Intent reedem = new Intent(Halaman_Penukaran_Bulan.this, Halaman_KTP.class);
        startActivity(reedem);
    }
}

