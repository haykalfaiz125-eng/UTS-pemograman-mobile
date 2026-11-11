package com.example.utsaplikasipenghitunggajikaryawan;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class GajiActivityHaykalFaiz extends AppCompatActivity {

    private EditText etNik, etNama, etGajiPokok, etTunjanganJabatan, etJumlahHariKerja;
    private EditText etTunjanganMakan, etTunjanganTransport, etGajiKotor, etGajiBersih;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gaji_haykalfaiz);

        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        etNik = findViewById(R.id.etNik);
        etNama = findViewById(R.id.etNama);
        etGajiPokok = findViewById(R.id.etGajiPokok);
        etTunjanganJabatan = findViewById(R.id.etTunjanganJabatan);
        etJumlahHariKerja = findViewById(R.id.etJumlahHariKerja);

        etTunjanganMakan = findViewById(R.id.etTunjanganMakan);
        etTunjanganTransport = findViewById(R.id.etTunjanganTransport);
        etGajiKotor = findViewById(R.id.etGajiKotor);
        etGajiBersih = findViewById(R.id.etGajiBersih);

        Button btnHitung = findViewById(R.id.btnHitung);
        Button btnKembaliGaji = findViewById(R.id.btnKembaliGaji);

        btnHitung.setOnClickListener(v -> hitungGaji());
        btnKembaliGaji.setOnClickListener(v -> finish());
    }

    private void hitungGaji() {
        String strNik = etNik.getText().toString().trim();
        String strNama = etNama.getText().toString().trim();
        String strGajiPokok = etGajiPokok.getText().toString().trim();
        String strTunjanganJabatan = etTunjanganJabatan.getText().toString().trim();
        String strJumlahHariKerja = etJumlahHariKerja.getText().toString().trim();

        if (TextUtils.isEmpty(strNik) || TextUtils.isEmpty(strNama) ||
                TextUtils.isEmpty(strGajiPokok) || TextUtils.isEmpty(strTunjanganJabatan) ||
                TextUtils.isEmpty(strJumlahHariKerja)) {
            Toast.makeText(this, "Harap isi semua data input terlebih dahulu!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double gajiPokok = Double.parseDouble(strGajiPokok);
            double tunjanganJabatan = Double.parseDouble(strTunjanganJabatan);
            double jumlahHariKerja = Double.parseDouble(strJumlahHariKerja);

            // Perhitungan sesuai petunjuk soal:
            // a. Tunjangan makan = jumlah hari kerja * Rp. 10.000
            double tunjanganMakan = jumlahHariKerja * 10000;

            // b. Tunjangan transportasi = jumlah hari kerja * Rp. 5.000
            double tunjanganTransport = jumlahHariKerja * 5000;

            // c. Gaji Kotor = gaji pokok + tunjangan jabatan + tunjangan makan + tunjangan transportasi
            double gajiKotor = gajiPokok + tunjanganJabatan + tunjanganMakan + tunjanganTransport;

            // d. Pajak = 10% dari gaji kotor
            double pajak = 0.10 * gajiKotor;

            // Gaji Bersih = gaji kotor - pajak
            double gajiBersih = gajiKotor - pajak;

            etTunjanganMakan.setText(formatRupiah(tunjanganMakan));
            etTunjanganTransport.setText(formatRupiah(tunjanganTransport));
            etGajiKotor.setText(formatRupiah(gajiKotor));
            etGajiBersih.setText(formatRupiah(gajiBersih));

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Format angka tidak valid!", Toast.LENGTH_SHORT).show();
        }
    }

    private String formatRupiah(double nominal) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        DecimalFormat decimalFormat = new DecimalFormat("Rp #,###", symbols);
        return decimalFormat.format(nominal);
    }
}