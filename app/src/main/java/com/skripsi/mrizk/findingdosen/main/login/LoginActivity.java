package com.skripsi.mrizk.findingdosen.main.login;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.skripsi.mrizk.findingdosen.FindingDosenApplication;
import com.skripsi.mrizk.findingdosen.R;
import com.skripsi.mrizk.findingdosen.main.mainMahasiswa.MainActivity;
import com.skripsi.mrizk.findingdosen.main.register.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    @BindView(R.id.login_layout)
    RelativeLayout login_layout;
    @BindView(R.id.login_judul)
    TextView judul;
    @BindView(R.id.login_logo)
    ImageView logo;
    @BindView(R.id.login_email)
    EditText email;
    @BindView(R.id.login_password)
    EditText password;
    @BindView(R.id.login_buttonLogin)
    Button loginButton;
    @BindView(R.id.login_register)
    TextView register;

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        loginViewModel = ViewModelProviders.of(this, FindingDosenApplication.getDataComponent().
                getLoginViewModelFactory()).get(LoginViewModel.class);

        loginButton.setOnClickListener(v -> login());

        register.setOnClickListener(v -> navigateToRegister());

    }

    private void login() {
        if (email.getText().toString().trim().isEmpty() || password.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Email atau Password tidak boleh kosong!", Toast.LENGTH_SHORT).show();
        } else {
            String emailUser = email.getText().toString().trim();
            String passwordUser = password.getText().toString().trim();

            // Create proggress bar
            ProgressBar progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            login_layout.addView(progressBar, params);
            progressBar.setVisibility(View.VISIBLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

            // loginUser
            loginViewModel.loginUser(emailUser, passwordUser).observe(this, loginStatus -> {
                progressBar.setVisibility(View.GONE);
                if (loginStatus) {
                    Toast.makeText(this, "Login Berhasil! ", Toast.LENGTH_SHORT).show();
                    navigateToMainActivity();
                } else {
                    Toast.makeText(this, "Login Gagal! " , Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        Log.d(TAG, "navigateToMainActivity: wtf");
        startActivity(intent);
        finish();
    }

    private void navigateToRegister() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

}
