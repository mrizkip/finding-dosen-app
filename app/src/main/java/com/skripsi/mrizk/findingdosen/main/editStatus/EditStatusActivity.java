package com.skripsi.mrizk.findingdosen.main.editStatus;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.skripsi.mrizk.findingdosen.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditStatusActivity extends AppCompatActivity {

    @BindView(R.id.editStatus_toolbar)
    Toolbar toolbar;
    @BindView(R.id.editStatus_radioGroupStatus)
    RadioGroup radioGroupStatus;
    @BindView(R.id.editStatusDetail_textDetail)
    EditText detailStatus;
    @BindView(R.id.editStatus_btnSimpan)
    Button btnSimpan;

    ActionBar actionBar;

    private String status;
    private String keterangan;

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

        btnSimpan.setOnClickListener(view -> {
            if (detailStatus.getText().toString().isEmpty()) {
                keterangan = "";
            } else {
                keterangan = detailStatus.getText().toString();
            }
            switch (radioGroupStatus.getCheckedRadioButtonId()) {
                case R.id.editStatus_aktif:
                    status = "Aktif";
                    simpanStatus();
                    break;
                case R.id.editStatus_tidakAktif:
                    status = "Tidak Aktif";
                    simpanStatus();
                    break;
                case -1:
                    Toast.makeText(this, "Pilih Status!", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }

    private void simpanStatus() {

    }
}
