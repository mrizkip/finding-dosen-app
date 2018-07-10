package com.skripsi.mrizk.findingdosen.main.myProfile;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.skripsi.mrizk.findingdosen.FindingDosenApplication;
import com.skripsi.mrizk.findingdosen.R;
import com.skripsi.mrizk.findingdosen.repository.entity.local.User;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyProfileActivity extends AppCompatActivity {

    @BindView(R.id.myProfile_imageViewParent)
    ImageView imageViewCover;
    @BindView(R.id.myProfile_imageViewProfile)
    ImageView imageViewProfile;
    @BindView(R.id.myProfile_textNama)
    TextView nama;
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

    private MyProfileViewModel myProfileViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        ButterKnife.bind(this);

        if (toolbar != null) setSupportActionBar(toolbar);
        if (getSupportActionBar() != actionBar) {
            actionBar = getSupportActionBar();
            actionBar.setTitle("Profil Saya");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initHeader();

        myProfileViewModel = ViewModelProviders.of(this, FindingDosenApplication.getDataComponent()
                                .getMyProfileViewModelFactory()).get(MyProfileViewModel.class);

        myProfileViewModel.getMyProfile().observe(this, this::setProfile);
    }

    private void setProfile(User user) {
        nama.setText(user.getNama());
        jenisIdentitas.setText(user.getJenisIdentitas());
        noIdentitas.setText(user.getNoIdentitas());
        noTelpon.setText(user.getNoTelpon());
        email.setText(user.getEmail());
    }

    private void initHeader() {
        Picasso.get().load("file:///android_asset/background_header.png").into(imageViewCover);
        Picasso.get().load("file:///android_asset/profile-placeholder.png").resize(72,72).centerCrop().into(imageViewProfile);
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
