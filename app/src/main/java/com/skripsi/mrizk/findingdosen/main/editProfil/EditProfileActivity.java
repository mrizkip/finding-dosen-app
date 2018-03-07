package com.skripsi.mrizk.findingdosen.main.editProfil;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import com.skripsi.mrizk.findingdosen.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditProfileActivity extends AppCompatActivity {

    @BindView(R.id.editProfile_nama)
    EditText nama;
    @BindView(R.id.editProfile_email)
    EditText email;
    @BindView(R.id.editProfile_password)
    EditText password;
    @BindView(R.id.editProfile_passwordBaru)
    EditText passwordBaru;
    @BindView(R.id.editProfile_buttoneditProfile)
    Button simpanButton;
    @BindView(R.id.editProfile_toolbar)
    Toolbar toolbar;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        ButterKnife.bind(this);

        if (toolbar != null) setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            actionBar = getSupportActionBar();
            actionBar.setTitle("Edit Profil");
        }

    }
}
