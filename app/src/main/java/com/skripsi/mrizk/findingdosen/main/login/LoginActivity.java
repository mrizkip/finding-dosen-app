package com.skripsi.mrizk.findingdosen.main.login;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.Iconics;
import com.mikepenz.iconics.context.IconicsContextWrapper;
import com.skripsi.mrizk.findingdosen.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

    }

}
