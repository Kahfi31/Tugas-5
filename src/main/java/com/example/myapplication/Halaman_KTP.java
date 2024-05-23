package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Halaman_KTP extends AppCompatActivity implements Halaman_manage_poin_pengguna.PointsUpdateListener {

    private static final int CAMERA_REQUEST_CODE = 100;
    private TextView textView50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_ktp);

        ImageButton imageButton = findViewById(R.id.buttonklik);
        textView50 = findViewById(R.id.textView50);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent untuk membuka kamera
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
                } else {
                    Toast.makeText(Halaman_KTP.this, "Tidak ada aplikasi kamera yang tersedia", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Daftarkan listener untuk pembaruan poin
        Halaman_manage_poin_pengguna.registerPointsUpdateListener(this);
        textView50.setText(String.valueOf(Halaman_manage_poin_pengguna.getUserPoints(this)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Batalkan pendaftaran listener saat aktivitas dihancurkan
        Halaman_manage_poin_pengguna.unregisterPointsUpdateListener(this);
    }

    @Override
    public void onPointsUpdated(int newPoints) {
        // Perbarui TextView dengan jumlah poin yang baru
        textView50.setText(String.valueOf(newPoints));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            // Ambil gambar dari intent kamera
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            // Atur ukuran gambar sesuai keperluan
            Bitmap resizedBitmap = resizeBitmap(imageBitmap, 400, 500);

            // Tampilkan gambar di ImageView
            ImageView imageView = findViewById(R.id.buttonklik);
            imageView.setVisibility(View.VISIBLE); // Tampilkan ImageView yang semula tersembunyi
            imageView.setImageBitmap(resizedBitmap);
        }
    }

    private Bitmap resizeBitmap(Bitmap bitmap, int width, int height) {
        return Bitmap.createScaledBitmap(bitmap, width, height, true);
    }

    public void kembali(View view) {
        Intent kembali = new Intent(Halaman_KTP.this, Halaman_Penukaran_Bulan.class);
        startActivity(kembali);
    }

    public void save(View view) {
        Intent save = new Intent(Halaman_KTP.this, Halaman_Utama.class);
        startActivity(save);
        Halaman_manage_poin_pengguna.subtractPoints(this, 200);
    }
}
