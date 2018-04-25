package com.skripsi.mrizk.findingdosen.main.mainMahasiswa;

import android.Manifest;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;
import com.skripsi.mrizk.findingdosen.FindingDosenApplication;
import com.skripsi.mrizk.findingdosen.R;
import com.skripsi.mrizk.findingdosen.main.login.LoginViewModel;

import java.util.ArrayList;
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

    private MainViewModel mainViewModel;

    private List<DosenAdapter> dosenList;
    private FastItemAdapter<DosenAdapter> fastDosenAdapter;

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

        mainViewModel = ViewModelProviders.of(this, FindingDosenApplication.getDataComponent()
                .getMainViewModelFactory()).get(MainViewModel.class);

        dosenList = new ArrayList<>();
        fastDosenAdapter = new FastItemAdapter<>();

        // get Dosen List from View Model
        mainViewModel.getDosenList().observe(this, listDosen -> {
            dosenList = listDosen;

            // set to adapter
            fastDosenAdapter.set(dosenList);

            // set adapter to recycler view
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(fastDosenAdapter);

            // on click listener of item
            fastDosenAdapter.withSelectable(true);
            fastDosenAdapter.withOnClickListener((v, adapter, item, position) -> {
                Toast.makeText(this, String.valueOf(item.getUserID()), Toast.LENGTH_SHORT).show();
                return true;
            });
        });

    }

}

