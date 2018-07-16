package com.skripsi.mrizk.findingdosen.main.posisiDosen;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
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
    TextView tvNamaDosen;
    @BindView(R.id.posisiDosen_lokasiDosen)
    TextView tvLokasiDosen;
    @BindView(R.id.posisiDosen_lastUpdate)
    TextView tvTerkahirDiperbarui;
    @BindView(R.id.posisiDosen_imageViewPosisi)
    ImageView imageViewPosisi;
    @BindView(R.id.posisiDosen_toolbar)
    Toolbar toolbar;
    ActionBar actionBar;

    private PosisiDosenViewModel viewModel;

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

        viewModel.getPosisiDosen(userId).observe(this, this::lihatPosisiDosen);
    }

    private void lihatPosisiDosen(PosisiDosen posisiDosen) {
        tvNamaDosen.setText(posisiDosen.getNama());
        tvLokasiDosen.setText(posisiDosen.getPosisi());
        tvTerkahirDiperbarui.setText(posisiDosen.getLastUpdate());
        if (posisiDosen.getPosisi().startsWith("A1") || posisiDosen.getPosisi().startsWith("LA1")) {
            Picasso.get().load("https://s3-ap-southeast-1.amazonaws.com/findingdosen/COLOR+RADIOMAP+GEDUNG+A+LANTAI+1.jpg").into(imageViewPosisi);
        } else if (posisiDosen.getPosisi().startsWith("A2") || posisiDosen.getPosisi().startsWith("LA2")) {
            Picasso.get().load("https://s3-ap-southeast-1.amazonaws.com/findingdosen/COLOR+RADIOMAP+GEDUNG+A+LANTAI+2.jpg").into(imageViewPosisi);
        } else {
            imageViewPosisi.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_close_grey_24dp));
        }
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
