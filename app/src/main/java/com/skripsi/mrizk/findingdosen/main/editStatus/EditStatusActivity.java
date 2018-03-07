package com.skripsi.mrizk.findingdosen.main.editStatus;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.skripsi.mrizk.findingdosen.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditStatusActivity extends AppCompatActivity {

    @BindView(R.id.editStatus_toolbar)
    Toolbar toolbar;
    @BindView(R.id.editStatus_radioGroupStatus)
    RadioGroup radioGroupStatus;
    @BindView(R.id.editStatus_aktif)
    RadioButton aktif;
    @BindView(R.id.editStatus_tidakAktif)
    RadioButton tidakAktif;
    @BindView(R.id.editStatus_radioGroupKeterangan)
    RadioGroup radioGroupKeterangan;
    @BindView(R.id.editStatus_keterangan1)
    RadioButton keterangan1;
    @BindView(R.id.editStatus_keterangan2)
    RadioButton keterangan2;
    @BindView(R.id.editStatusDetail_textDetail)
    EditText detailStatus;

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_status);

        ButterKnife.bind(this);

        if (toolbar != null) setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            actionBar = getSupportActionBar();
            actionBar.setTitle("Edit Status");
        }
    }
}
