package com.skripsi.mrizk.findingdosen.main.mainMahasiswa;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.skripsi.mrizk.findingdosen.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.mainActivity_toolbar)
    Toolbar toolbar;
    @BindView(R.id.mainActivity_searchView)
    SearchView searchView;
    @BindView(R.id.mainActivity_recyclerView)
    RecyclerView recyclerView;
    ActionBar actionBar;

    private WifiManager wifiManager;
    private List<ScanResult> scanResults;
    private int scanCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (toolbar != null) setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            actionBar = getSupportActionBar();
            actionBar.setTitle("FINDING DOSEN");
        }

        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            accessWifiManager();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                    }
                }).check();


    }

    private void accessWifiManager() {
        final WifiManager mWifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);

        if(mWifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {

            // register WiFi scan results receiver
            IntentFilter filter = new IntentFilter();
            filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);

            registerReceiver(new BroadcastReceiver(){
                @Override
                public void onReceive(Context context, Intent intent) {

                    List<ScanResult> results = mWifiManager.getScanResults();
                    final int N = results.size();

                    Log.v(TAG, "Wi-Fi Scan Results ... Count:" + N);
                    for(int i=0; i < N; ++i) {
                        Log.v(TAG, "  BSSID       =" + results.get(i).BSSID);
                        Log.v(TAG, "  SSID        =" + results.get(i).SSID);
                        Log.v(TAG, "  Capabilities=" + results.get(i).capabilities);
                        Log.v(TAG, "  Frequency   =" + results.get(i).frequency);
                        Log.v(TAG, "  Level       =" + results.get(i).level);
                        Log.v(TAG, "---------------");
                    }
                }
            }, filter);

            // start WiFi Scan
            mWifiManager.startScan();
        }
    }
}

