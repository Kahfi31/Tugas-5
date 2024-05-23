package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class Halaman_Foto extends AppCompatActivity implements Halaman_manage_poin_pengguna.PointsUpdateListener {

    private static final String COUNTER_KEY = "counter";
    private boolean isBarcodeRegistered = false;
    private boolean isScanSuccessful = false;
    private ImageView imageViewScanned;
    private ImageButton buttonScan;
    public TextView textView16;
    private ImageView imageView62;
    private ImageView imageView51;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_foto);

        imageViewScanned = findViewById(R.id.imageViewScanned);
        buttonScan = findViewById(R.id.buttonScan);
        textView16 = findViewById(R.id.textView16);
        imageView62 = findViewById(R.id.imageView62);
        imageView51 = findViewById(R.id.imageView51);

        imageView62.setVisibility(View.GONE);
        imageView51.setVisibility(View.GONE);

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setOrientationLocked(true);

        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mulai pemindaian
                integrator.initiateScan();
            }
        });

        Halaman_manage_poin_pengguna.registerPointsUpdateListener(this);
        textView16.setText(String.valueOf(Halaman_manage_poin_pengguna.getUserPoints(this)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Halaman_manage_poin_pengguna.unregisterPointsUpdateListener(this);
    }

    @Override
    public void onPointsUpdated(int newPoints) {
        // Perbarui TextView dengan jumlah poin yang baru
        textView16.setText(String.valueOf(newPoints));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                // Pemindaian dibatalkan
                Toast.makeText(this, "Pemindaian dibatalkan", Toast.LENGTH_SHORT).show();
                isScanSuccessful = false;
                imageView62.setVisibility(View.GONE);
                imageView51.setVisibility(View.GONE);
            } else {
                // Hasil pemindaian
                String scannedData = result.getContents();
                Toast.makeText(this, "Hasil Pemindaian: " + scannedData, Toast.LENGTH_SHORT).show();

                // Anggap pengecekan registrasi dengan data yang dipindai
                isBarcodeRegistered = checkIfBarcodeRegistered(scannedData);

                // Tampilkan data barcode sebagai Bitmap pada ImageButton
                Bitmap barcodeBitmap = generateBarcodeBitmap(scannedData);
                updateButtonScanWithScannedData(barcodeBitmap);

                // Update visibility of imageView62 and imageView51 based on scan success
                isScanSuccessful = isBarcodeRegistered;
                imageView62.setVisibility(isScanSuccessful ? View.VISIBLE : View.GONE);
                imageView51.setVisibility(isScanSuccessful ? View.VISIBLE : View.GONE);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private boolean checkIfBarcodeRegistered(String scannedData) {
        // Contoh logika untuk memeriksa registrasi barcode
        return scannedData != null && !scannedData.isEmpty();
    }

    private void updateButtonScanWithScannedData(Bitmap barcodeBitmap) {
        if (barcodeBitmap != null) {
            buttonScan.setImageBitmap(barcodeBitmap);
        } else {
            // Jika barcode tidak terdaftar atau tidak ada bitmap, gunakan gambar default
            buttonScan.setImageResource(R.drawable.ph_camera);
        }
    }

    private Bitmap generateBarcodeBitmap(String data) {
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, 500, 500);
            BarcodeEncoder encoder = new BarcodeEncoder();
            return encoder.createBitmap(bitMatrix);
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void onSubmitButtonClick(View view) {
        ImageView imageViewTrue = findViewById(R.id.imageView62);
        ImageView imageViewFalse = findViewById(R.id.imageViewPlus1);
        if (isBarcodeRegistered) {
            imageViewTrue.setVisibility(View.VISIBLE);
            imageViewFalse.setVisibility(View.GONE);
        } else {
            imageViewTrue.setVisibility(View.GONE);
            imageViewFalse.setVisibility(View.VISIBLE);
        }
    }

    private void incrementCounter(SharedPreferences sharedPreferences) {
        String currentText = textView16.getText().toString();
        int currentValue = Integer.parseInt(currentText);
        int newValue = currentValue + 1;
        textView16.setText(String.valueOf(newValue));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(COUNTER_KEY, newValue);
        editor.apply();

        imageView62.setVisibility(View.VISIBLE);
        imageView51.setVisibility(View.VISIBLE);

        isScanSuccessful = false;
    }

    public void submit(View view) {
        Intent submit = new Intent(Halaman_Foto.this, Halaman_Klaim.class);
        startActivity(submit);
        Halaman_manage_poin_pengguna.addPoints(this, 100);
    }

    public void cancel(View view) {
        Intent cancel = new Intent(Halaman_Foto.this, Halaman_Utama.class);
        startActivity(cancel);
    }
}





