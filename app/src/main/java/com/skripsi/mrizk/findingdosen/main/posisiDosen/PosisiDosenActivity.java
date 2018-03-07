package com.skripsi.mrizk.findingdosen.main.posisiDosen;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.skripsi.mrizk.findingdosen.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PosisiDosenActivity extends AppCompatActivity {

    @BindView(R.id.posisiDosen_namaDosen)
    TextView namaDosen;
    @BindView(R.id.posisiDosen_textNIP)
    TextView jenisIdentitas;
    @BindView(R.id.posisiDosen_nipDosen)
    TextView noIdentitas;
    @BindView(R.id.posisiDosen_statusDosen)
    TextView statusDosen;
    @BindView(R.id.posisiDosen_imageViewPosisi)
    ImageView imageViewPosisi;
    @BindView(R.id.posisiDosen_toolbar)
    Toolbar toolbar;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posisi_dosen);

        ButterKnife.bind(this);

        if (toolbar != null) setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            actionBar = getSupportActionBar();
            actionBar.setTitle("Posisi Dosen");
        }

    }
}
