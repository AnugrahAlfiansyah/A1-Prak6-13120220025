package com.example.a1_prak6_13120220025;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class TampilDataActivity extends AppCompatActivity {

    private TableLayout tbMhs;
    private TableRow tr;
    private TextView col1, col2, col3;
    private  RestHelper restHelper;
    private String stb, nama, angkatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tampil_data);
        restHelper = new RestHelper(this);
        tbMhs = findViewById(R.id.tbl_mhs);
        tampilData();
    }

    private void tampilTblMhs(ArrayList<Mahasiswa> arrayListMhs){
        tbMhs.removeAllViews();

        tr = new TableRow(this);
        col1 = new TextView(this);
        col2 = new TextView(this);
        col3 = new TextView(this);

        col1.setText("Stambuk");
        col2.setText("Nama");
        col3.setText("Angkatan");

        col1.setWidth(200);
        col1.setWidth(300);
        col1.setWidth(150);

        tr.addView(col1);
        tr.addView(col2);
        tr.addView(col3);

        tbMhs.addView(tr);

        for (final Mahasiswa mhs: arrayListMhs){
            tr = new TableRow(this);
            col1 = new TextView(this);
            col2 = new TextView(this);
            col3 = new TextView(this);

            col1.setText(mhs.getStb());
            col2.setText(mhs.getNama());
            col3.setText(String.valueOf(mhs.getAngkatan()));

            col1.setWidth(200);
            col1.setWidth(300);
            col1.setWidth(150);

            tr.addView(col1);
            tr.addView(col2);
            tr.addView(col3);

            tbMhs.addView(tr);

            tr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int i=0; i<tbMhs.getChildCount(); i++){
                        stb = mhs.getStb();
                        nama = mhs.getNama();
                        angkatan = String.valueOf(mhs.getAngkatan());
                        if (tbMhs.getChildAt(i) == view)
                            tbMhs.getChildAt(i).setBackgroundColor(Color.LTGRAY);
                        else
                            tbMhs.getChildAt(i).setBackgroundColor(Color.WHITE);
                    }
                }
            });
        }
    }

    private void tampilData(){
        restHelper.getDataMhs(new RestCallbackMahasiswa() {
            @Override
            public void requestDataMhsSuccess(ArrayList<Mahasiswa> arrayList) {
                tampilTblMhs(arrayList);
            }
        });
    }

    public void btnEditClick(View view){
        Intent intent = new Intent();
        intent.putExtra("stb", stb);
        intent.putExtra("nama", nama);
        intent.putExtra("angkatan", angkatan);
        setResult(1, intent);
        finish();
    }

    public void btnHapusClick(View view){
        if(stb == null) return;
        restHelper.hapusData(stb, new RestCallbackMahasiswa() {
            @Override
            public void requestDataMhsSuccess(ArrayList<Mahasiswa> arrayList) {
                tampilTblMhs(arrayList);
            }
        });
    }
}