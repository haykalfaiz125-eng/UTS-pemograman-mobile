package com.example.utsaplikasipenghitunggajikaryawan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivityHaykalFaiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_haykalfaiz);

        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        LinearLayout btnGajiPegawai = findViewById(R.id.btnGajiPegawai);
        LinearLayout btnPetunjuk = findViewById(R.id.btnPetunjuk);
        LinearLayout btnTentang = findViewById(R.id.btnTentang);
        LinearLayout btnExit = findViewById(R.id.btnExit);
        TextView tvFooterWebsite = findViewById(R.id.tvFooterWebsite);

        btnGajiPegawai.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivityHaykalFaiz.this, GajiActivityHaykalFaiz.class);
            startActivity(intent);
        });

        btnPetunjuk.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivityHaykalFaiz.this, PetunjukActivityHaykalFaiz.class);
            startActivity(intent);
        });

        btnTentang.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivityHaykalFaiz.this, TentangActivityHaykalFaiz.class);
            startActivity(intent);
        });

        btnExit.setOnClickListener(v -> finishAffinity());

        tvFooterWebsite.setOnClickListener(v -> {
            try {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sandrio.com"));
                startActivity(browserIntent);
            } catch (Exception e) {
                Toast.makeText(MainActivityHaykalFaiz.this, "Membuka www.sandrio.com", Toast.LENGTH_SHORT).show();
            }
        });
    }
}