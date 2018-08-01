package com.skripsi.mrizk.findingdosen.main.editProfil;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.skripsi.mrizk.findingdosen.FindingDosenApplication;
import com.skripsi.mrizk.findingdosen.R;
import com.skripsi.mrizk.findingdosen.repository.entity.local.User;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditProfileActivity extends AppCompatActivity {

    @BindView(R.id.editProfile_nama)
    EditText nama;
    @BindView(R.id.editProfile_email)
    EditText email;
    @BindView(R.id.editProfile_noTelpon)
    EditText noTelpon;
    @BindView(R.id.editProfile_buttoneditProfile)
    Button simpanButton;
    @BindView(R.id.editProfile_toolbar)
    Toolbar toolbar;
    ActionBar actionBar;

    private EditProfilViewModel editProfilViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        ButterKnife.bind(this);

        if (toolbar != null) setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            actionBar = getSupportActionBar();
            actionBar.setTitle("Edit Profil");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        editProfilViewModel = ViewModelProviders.of(this, FindingDosenApplication.getDataComponent().getEditProfilViewModelFactory())
                .get(EditProfilViewModel.class);

        editProfilViewModel.getProfil().observe(this, this::setProfile);

        simpanButton.setOnClickListener(view -> editProfil());
    }

    private void setProfile(User user) {
        nama.setText(user.getNama());
        email.setText(user.getEmail());
        noTelpon.setText(user.getNoTelpon());
    }

    private void editProfil() {
        String nomorTelpon = noTelpon.getText().toString().trim();
        if (nomorTelpon.isEmpty()) {
            noTelpon.setError("Nomor Telpon tidak boleh kosong!");
        } else {
            editProfilViewModel.editProfil(nomorTelpon).observe(this, response -> {
                if (response != null) {
                    Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
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
