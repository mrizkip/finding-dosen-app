package com.skripsi.mrizk.findingdosen.main.mainDosen;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.skripsi.mrizk.findingdosen.FindingDosenApplication;
import com.skripsi.mrizk.findingdosen.R;
import com.skripsi.mrizk.findingdosen.main.editProfil.EditProfileActivity;
import com.skripsi.mrizk.findingdosen.main.editStatus.EditStatusActivity;
import com.skripsi.mrizk.findingdosen.main.login.LoginActivity;
import com.skripsi.mrizk.findingdosen.main.mainMahasiswa.MainActivity;
import com.skripsi.mrizk.findingdosen.repository.datasource.local.SharedPrefsUserRepository;
import com.skripsi.mrizk.findingdosen.repository.entity.local.ProfilDosen;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivityDosen extends AppCompatActivity {

    private static final String TAG = "MainActivityDosen";

    @BindView(R.id.mainDosen_toolbar)
    Toolbar toolbar;
    @BindView(R.id.mainDosen_imageViewParent)
    ImageView imageViewCover;
    @BindView(R.id.mainDosen_imageViewProfile)
    CircleImageView imageViewProfile;
    @BindView(R.id.mainDosen_textNamaDosen)
    TextView namaDosen;
    @BindView(R.id.mainDosen_textStatus)
    TextView statusDosen;
    @BindView(R.id.mainDosen_textKeterangan)
    TextView statusKeterangan;
    @BindView(R.id.mainDosen_editStatus)
    ImageButton editStatus;
    @BindView(R.id.mainDosen_textNIP)
    TextView nipDosen;
    @BindView(R.id.mainDosen_textNoTelp)
    TextView noTelpDosen;
    @BindView(R.id.mainDosen_textEmail)
    TextView emailDosen;
    @BindView(R.id.mainDosen_textViewNIP)
    TextView jenisIdentitasDosen;
    @BindView(R.id.mainDosen_btnLogout)
    Button btnLogout;

    ActionBar actionBar;

    private MainDosenViewModel dosenViewModel;
    private SharedPrefsUserRepository sharedPrefsUserRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dosen);
        ButterKnife.bind(this);

        if (toolbar != null) setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            actionBar = getSupportActionBar();
            actionBar.setTitle("FINDING DOSEN");
        }

        initHeader();

        dosenViewModel = ViewModelProviders.of(this, FindingDosenApplication.getDataComponent().getMainDosenViewModelFactory())
                .get(MainDosenViewModel.class);
        sharedPrefsUserRepository = FindingDosenApplication.getDataComponent().getSharedPrefsUserRepository();

        dosenViewModel.getProfilDosen().observe(this, this::setProfile);

        editStatus.setOnClickListener(view -> editStatus());

        btnLogout.setOnClickListener(view -> logout());

    }

    private void setProfile(ProfilDosen profilDosen) {
        namaDosen.setText(profilDosen.getNama());
        nipDosen.setText(profilDosen.getNoIdentitas());
        jenisIdentitasDosen.setText(profilDosen.getJenisIdentitas());
        emailDosen.setText(profilDosen.getEmail());
        noTelpDosen.setText(profilDosen.getNoTelpon());
        statusDosen.setText(profilDosen.getStatus());
        statusKeterangan.setText(profilDosen.getKetStatus());
    }

    private void logout() {
        new AlertDialog.Builder(MainActivityDosen.this)
                .setIcon(R.drawable.ic_warning_black_24dp)
                .setTitle("Keluar")
                .setMessage("Apakah anda ingin keluar dari sesi ini?")
                .setPositiveButton("YA", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    sharedPrefsUserRepository.unsetUserFromPrefs();
                    Intent logoutIntent = new Intent(MainActivityDosen.this, LoginActivity.class);
                    logoutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(logoutIntent);
                })
                .setNegativeButton("TIDAK", (null))
                .show();
    }

    private void editStatus() {
        Intent intent = new Intent(MainActivityDosen.this, EditStatusActivity.class);
        startActivity(intent);
    }

    private void initHeader() {
        int maxWidth = 720;
        int height = 200;
        Picasso.get().load("file:///android_asset/background_drawer1.png").resize(maxWidth, height).into(imageViewCover);
        Picasso.get().load("file:///android_asset/profile-placeholder.png").resize(72,72).centerCrop().into(imageViewProfile);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_profil, menu);

        MenuItem itemEdit = menu.findItem(R.id.menu_profil_edit);
        Drawable editIcon = itemEdit.getIcon();
        editIcon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_profil_edit:
                Intent intent = new Intent(MainActivityDosen.this, EditProfileActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
