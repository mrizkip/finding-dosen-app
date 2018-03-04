package com.skripsi.mrizk.findingdosen.main;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.skripsi.mrizk.findingdosen.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivityDosen extends AppCompatActivity {

    private static final String TAG = "MainActivityDosen";

    @BindView(R.id.mainDosen_toolbar) Toolbar toolbar;
    @BindView(R.id.mainDosen_imageViewParent) ImageView imageViewCover;
    @BindView(R.id.mainDosen_imageViewProfile) CircleImageView imageViewProfile;
    @BindView(R.id.mainDosen_textNamaDosen) TextView namaDosen;
    @BindView(R.id.mainDosen_textJabatan) TextView jabatanDosen;
    @BindView(R.id.mainDosen_textStatus) TextView statusDosen;
    @BindView(R.id.mainDosen_textKeterangan) TextView statusKeterangan;
    @BindView(R.id.mainDosen_textEditInfo) TextView textEditInfo;
    @BindView(R.id.mainDosen_editStatus) ImageView editStatus;
    @BindView(R.id.mainDosen_textNIP) TextView nipDosen;
    @BindView(R.id.mainDosen_textNoTelp) TextView noTelpDosen;
    @BindView(R.id.mainDosen_textEmail) TextView emailDosen;

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dosen);
        ButterKnife.bind(this);
    }
}
