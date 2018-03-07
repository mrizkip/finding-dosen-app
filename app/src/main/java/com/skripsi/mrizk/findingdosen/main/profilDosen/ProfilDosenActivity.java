package com.skripsi.mrizk.findingdosen.main.profilDosen;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.skripsi.mrizk.findingdosen.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfilDosenActivity extends AppCompatActivity {

    @BindView(R.id.profilDosen_imageViewParent)
    ImageView imageViewCover;
    @BindView(R.id.profilDosen_imageViewProfile)
    ImageView imageViewProfil;
    @BindView(R.id.profilDosen_textNamaDosen)
    TextView namaDosen;
    @BindView(R.id.profilDosen_textJabatan)
    TextView jabatanDosen;
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
    @BindView(R.id.profilDosen_toolbar)
    Toolbar toolbar;
    ActionBar actionBar;

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


    }
}
