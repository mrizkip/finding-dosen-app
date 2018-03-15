package com.skripsi.mrizk.findingdosen.main.register;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.skripsi.mrizk.findingdosen.FindingDosenApplication;
import com.skripsi.mrizk.findingdosen.R;
import com.skripsi.mrizk.findingdosen.main.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.register_layout)
    RelativeLayout register_layout;
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
    @BindView(R.id.register_login)
    TextView login;
    @BindView(R.id.register_toolbar)
    Toolbar toolbar;
    ActionBar actionBar;

    private RegisterViewModel registerViewModel;

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

        registerViewModel = ViewModelProviders.of(this, FindingDosenApplication.getDataComponent().
                getRegisterViewModelFactory()).get(RegisterViewModel.class);

        registerButton.setOnClickListener(v -> register());

        login.setOnClickListener(v -> finish());

    }

    private void register() {
        String emailUser = email.getText().toString().trim();
        String passwordUser = password.getText().toString().trim();
        String namaUser = nama.getText().toString().trim();
        String jenisIdentitasUser = jenisIdentitas.getSelectedItem().toString().trim();
        String noIdentitasUser = noIdentitas.getText().toString().trim();
        String noTelponUser = noTelpon.getText().toString().trim();

        if (emailUser.isEmpty() || passwordUser.isEmpty() || namaUser.isEmpty() || jenisIdentitasUser.isEmpty()
                || noIdentitasUser.isEmpty() || noTelponUser.isEmpty()) {
            Toast.makeText(this, "Seluruh kolom harus diisi!", Toast.LENGTH_SHORT).show();
        } else {
            // Create proggress bar
            ProgressBar progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            register_layout.addView(progressBar, params);
            progressBar.setVisibility(View.VISIBLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

            // call registerUser from ViewModel
            registerViewModel.registerUser(emailUser, passwordUser, namaUser, jenisIdentitasUser, noIdentitasUser, noTelponUser)
                    .observe(this, registerStatus -> {
                        progressBar.setVisibility(View.GONE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        if (registerStatus) {
                            Toast.makeText(this, "Pendaftaran Berhasil!", Toast.LENGTH_SHORT).show();
                            finish(); // Finish activity and navigate to login activity
                        } else {
                            Toast.makeText(this, "Pendaftaran Gagal!", Toast.LENGTH_SHORT).show();
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
