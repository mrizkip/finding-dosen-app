package com.skripsi.mrizk.findingdosen.main.profilDosen;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.skripsi.mrizk.findingdosen.FindingDosenApplication;
import com.skripsi.mrizk.findingdosen.R;
import com.skripsi.mrizk.findingdosen.main.posisiDosen.PosisiDosenActivity;
import com.skripsi.mrizk.findingdosen.repository.entity.local.ProfilDosen;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfilDosenActivity extends AppCompatActivity {

    @BindView(R.id.profilDosen_imageViewParent)
    ImageView imageViewCover;
    @BindView(R.id.profilDosen_imageViewProfile)
    ImageView imageViewProfil;
    @BindView(R.id.profilDosen_textNamaDosen)
    TextView namaDosen;
    @BindView(R.id.profilDosen_textNIP)
    TextView noIdentitas;
    @BindView(R.id.profilDosen_textViewNIP)
    TextView jenisIdentitas;
    @BindView(R.id.profilDosen_textNoTelp)
    TextView noTelponDosen;
    @BindView(R.id.profilDosen_buttonLihatPeta)
    Button lihatPetaButton;
    @BindView(R.id.profilDosen_textEmail)
    TextView email;
    @BindView(R.id.profilDosen_textStatus)
    TextView status;
    @BindView(R.id.profilDosen_textKeterangan)
    TextView keterangan;
    @BindView(R.id.profilDosen_toolbar)
    Toolbar toolbar;
    ActionBar actionBar;

    private ProfilDosenViewModel profilDosenViewModel;
    private ProfilDosen profilDosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_dosen);

        ButterKnife.bind(this);

        if (toolbar != null) setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            actionBar = getSupportActionBar();
            actionBar.setTitle("Profil Dosen");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initHeader();

        Intent intent = getIntent();
        int userId = intent.getIntExtra("UserID", 0);

        profilDosenViewModel = ViewModelProviders.of(this, FindingDosenApplication.getDataComponent()
                .getProfilDosenViewModelFactory()).get(ProfilDosenViewModel.class);

        profilDosen = new ProfilDosen();
        profilDosenViewModel.getProfilDosen(userId).observe(this, dosen -> {
            profilDosen = dosen;
            setProfile();
        });

        lihatPetaButton.setOnClickListener(v -> viewLocation());

    }

    private void initHeader() {
        int maxWidth = 720;
        int height = 200;
        Picasso.get().load("file:///android_asset/background_drawer1.png").resize(maxWidth, height).into(imageViewCover);
        Picasso.get().load("file:///android_asset/profile-placeholder.png").resize(72,72).centerCrop().into(imageViewProfil);
    }

    private void viewLocation() {
        Intent intent = new Intent(ProfilDosenActivity.this, PosisiDosenActivity.class);
        intent.putExtra("userId", profilDosen.getUserId());
        startActivity(intent);
    }

    private void setProfile() {
        namaDosen.setText(profilDosen.getNama());
        noIdentitas.setText(profilDosen.getNoIdentitas());
        jenisIdentitas.setText(profilDosen.getJenisIdentitas());
        email.setText(profilDosen.getEmail());
        noTelponDosen.setText(profilDosen.getNoTelpon());
        status.setText(profilDosen.getStatus());
        keterangan.setText(profilDosen.getDescStatus());

        if (profilDosen.getStatus().equalsIgnoreCase("Aktif")) {
            lihatPetaButton.setVisibility(View.VISIBLE);
        } else {
            lihatPetaButton.setVisibility(View.GONE);
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
