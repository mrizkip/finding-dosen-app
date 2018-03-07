package com.skripsi.mrizk.findingdosen.main.myProfile;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.skripsi.mrizk.findingdosen.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyProfileActivity extends AppCompatActivity {

    @BindView(R.id.myProfile_imageViewParent)
    ImageView imageViewCover;
    @BindView(R.id.myProfile_imageViewProfile)
    ImageView imageViewProfile;
    @BindView(R.id.myProfile_textViewJenisIdentitas)
    TextView jenisIdentitas;
    @BindView(R.id.myProfile_textNoIdentitas)
    TextView noIdentitas;
    @BindView(R.id.myProfile_textNoTelp)
    TextView noTelpon;
    @BindView(R.id.myProfile_textEmail)
    TextView email;
    @BindView(R.id.myProfile_toolbar)
    Toolbar toolbar;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        ButterKnife.bind(this);

        if (toolbar != null) setSupportActionBar(toolbar);
        if (getSupportActionBar() != actionBar) {
            actionBar = getSupportActionBar();
            actionBar.setTitle("PROFIL");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
