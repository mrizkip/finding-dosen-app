package com.skripsi.mrizk.findingdosen.main.editStatus;

import android.Manifest;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.skripsi.mrizk.findingdosen.FindingDosenApplication;
import com.skripsi.mrizk.findingdosen.R;
import com.skripsi.mrizk.findingdosen.main.mainDosen.MainActivityDosen;
import com.skripsi.mrizk.findingdosen.main.mainDosen.MainDosenViewModel;
import com.skripsi.mrizk.findingdosen.repository.entity.api.AccessPointRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditStatusActivity extends AppCompatActivity {

    private static final String TAG = "EditStatusActivity";

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

    private EditStatusViewModel editStatusViewModel;

    private int scanCount;

    private List<ScanResult> scanResults = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_status);

        ButterKnife.bind(this);

        if (toolbar != null) setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            actionBar = getSupportActionBar();
            actionBar.setTitle("Edit Status");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        editStatusViewModel = ViewModelProviders.of(this, FindingDosenApplication.getDataComponent().getEditStatusViewModelFactory())
                .get(EditStatusViewModel.class);

        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            for (int i = 0; i < 10; i++) {
                                scanCount = i + 1;
                                accessWifiManager();
                                Log.d(TAG, "accessWifiManager: " + scanCount);
                            }
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                    }
                }).check();


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
        editStatusViewModel.ubahStatus(status, keterangan).observe(this, rubahStatusResponse -> {
            if (rubahStatusResponse != null) {
                Toast.makeText(this, "Perubahan status berhasil", Toast.LENGTH_SHORT).show();
                if (status.equalsIgnoreCase("Aktif")) {
                    // scan wifi and update location
                    ubahStatusDosen();
                } else {
                    Intent intent = new Intent(EditStatusActivity.this, MainActivityDosen.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            } else {
                Toast.makeText(this, "Perubahan status gagal!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ubahStatusDosen() {
        // TODO REMOVE FOR LOOP
        if (!scanResults.isEmpty()) {
            Log.d(TAG, "ubahStatusDosen: " + scanResults.size());
            List<AccessPointRequest> listApRequest = new ArrayList<>();
            for (ScanResult result : scanResults) {
                AccessPointRequest apReqeust = new AccessPointRequest();
                apReqeust.setBSSID(result.BSSID);
                apReqeust.setSSID(result.SSID);
                apReqeust.setLevel(result.level);
                listApRequest.add(apReqeust);
            }
            editStatusViewModel.ubahLokasi(listApRequest).observe(this, rubahLokasiResponse -> {
                if (rubahLokasiResponse != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this)
                            .setTitle("Ubah Status")
                            .setMessage("Lokasi: " + rubahLokasiResponse.getPosisi() + "\nNilai MSE: " + rubahLokasiResponse.getMse())
                            .setPositiveButton("OK", (dialogInterface, i) -> {
                                Intent intent = new Intent(EditStatusActivity.this, MainActivityDosen.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            });
                    builder.show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this)
                            .setTitle("Ubah Status")
                            .setMessage("Lokasi tidak ditemukan!")
                            .setPositiveButton("OK", (dialogInterface, i) -> {
                                Intent intent = new Intent(EditStatusActivity.this, MainActivityDosen.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            });
                    builder.show();
                }
            });
        }
    }


    private void accessWifiManager() {
        final WifiManager mWifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);

        if (mWifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {

            // register WiFi scan results receiver
            IntentFilter filter = new IntentFilter();
            filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);

            registerReceiver(new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {

                    List<ScanResult> resultList = mWifiManager.getScanResults();
                    scanResults.addAll(resultList);
                    int N = scanResults.size();

                    Log.v(TAG, "Wi-Fi Scan Results ... Count:" + N);
                }
            }, filter);

            // start WiFi Scan
            mWifiManager.startScan();
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
