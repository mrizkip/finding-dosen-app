package com.skripsi.mrizk.findingdosen.main.posisiDosen;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.skripsi.mrizk.findingdosen.FindingDosenApplication;
import com.skripsi.mrizk.findingdosen.R;
import com.skripsi.mrizk.findingdosen.repository.entity.local.PosisiDosen;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PosisiDosenActivity extends AppCompatActivity {

    @BindView(R.id.posisiDosen_namaDosen)
    TextView namaDosen;
    @BindView(R.id.posisiDosen_lokasiDosen)
    TextView lokasiDosen;
    @BindView(R.id.posisiDosen_lastUpdate)
    TextView terkahirDiperbarui;
    @BindView(R.id.posisiDosen_imageViewPosisi)
    ImageView imageViewPosisi;
    @BindView(R.id.posisiDosen_toolbar)
    Toolbar toolbar;
    ActionBar actionBar;

    private PosisiDosenViewModel viewModel;
    private PosisiDosen posisiDosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posisi_dosen);

        ButterKnife.bind(this);

        if (toolbar != null) setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            actionBar = getSupportActionBar();
            actionBar.setTitle("Posisi Dosen");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        int userId = intent.getIntExtra("userId", 0);

        viewModel = ViewModelProviders.of(this, FindingDosenApplication.getDataComponent().getPosisiDosenViewModelFactory())
                .get(PosisiDosenViewModel.class);
        posisiDosen = new PosisiDosen();

        viewModel.getPosisiDosen(userId).observe(this, posisi -> {
            posisiDosen = posisi;
            setPosisi();
        });
    }

    private void setPosisi() {
        namaDosen.setText(posisiDosen.getNama());
        lokasiDosen.setText(posisiDosen.getPosisi());
        terkahirDiperbarui.setText(posisiDosen.getLastUpdate());
        Picasso.get().load("file:///android_asset/profile-placeholder.png").resize(200, 200).centerCrop().into(imageViewPosisi);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
