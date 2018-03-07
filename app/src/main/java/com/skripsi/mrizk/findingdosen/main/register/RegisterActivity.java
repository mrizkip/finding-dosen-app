package com.skripsi.mrizk.findingdosen.main.register;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.skripsi.mrizk.findingdosen.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.register_nama)
    EditText nama;
    @BindView(R.id.register_email)
    EditText email;
    @BindView(R.id.register_password)
    EditText password;
    @BindView(R.id.register_noTelpon)
    EditText noTelpon;
    @BindView(R.id.register_spinner_jenisIdentitas)
    Spinner jenisIdentitas;
    @BindView(R.id.register_noIdentitas)
    EditText noIdentitas;
    @BindView(R.id.register_buttonRegister)
    Button registerButton;
    @BindView(R.id.register_toolbar)
    Toolbar toolbar;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);

        if (toolbar != null) setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            actionBar = getSupportActionBar();
            actionBar.setTitle("Daftar");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
