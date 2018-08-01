package com.skripsi.mrizk.findingdosen.main.mainMahasiswa;

import android.app.SearchManager;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.skripsi.mrizk.findingdosen.FindingDosenApplication;
import com.skripsi.mrizk.findingdosen.R;
import com.skripsi.mrizk.findingdosen.main.login.LoginActivity;
import com.skripsi.mrizk.findingdosen.main.myProfile.MyProfileActivity;
import com.skripsi.mrizk.findingdosen.main.profilDosen.ProfilDosenActivity;
import com.skripsi.mrizk.findingdosen.repository.datasource.local.SharedPrefsUserRepository;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, SearchView.OnCloseListener {

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

    private List<DosenAdapter> dosenFilterList;

    private Drawer drawer;
    private PrimaryDrawerItem itemListDosen;
    private PrimaryDrawerItem itemLogout;

    private SharedPrefsUserRepository sharedPrefsUserRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (toolbar != null) setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            actionBar = getSupportActionBar();
            actionBar.setTitle("Daftar Dosen");
        }

        SearchManager searchManager = (SearchManager) this.getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);

        initRecyclerView();

        mainViewModel = ViewModelProviders.of(this, FindingDosenApplication.getDataComponent()
                .getMainViewModelFactory()).get(MainViewModel.class);
        sharedPrefsUserRepository = FindingDosenApplication.getDataComponent()
                .getSharedPrefsUserRepository();

        setupDrawer();

        // get Dosen List from View Model
        mainViewModel.getDosenList().observe(this, this::lihatDaftarDosen);
    }



    private void setupDrawer() {
        itemListDosen = new PrimaryDrawerItem().withIdentifier(1).withName("Daftar Dosen")
                .withIcon(R.drawable.ic_people_black_24dp).withSelectedIcon(R.drawable.ic_people_blue_24dp);
        itemLogout = new PrimaryDrawerItem().withIdentifier(2).withName("Keluar")
                .withIcon(R.drawable.ic_exit_to_app_black_24dp).withSelectedIcon(R.drawable.ic_exit_to_app_blue_24dp);

        AccountHeader header = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.background_drawer1)
                .addProfiles(new ProfileDrawerItem().withIdentifier(0).withName(sharedPrefsUserRepository.getUserFromPrefs().getNama()))
                .withTextColor(Color.WHITE)
                .withSelectionListEnabledForSingleProfile(false)
                .withProfileImagesClickable(true)
                .withOnAccountHeaderProfileImageListener(new AccountHeader.OnAccountHeaderProfileImageListener() {
                    @Override
                    public boolean onProfileImageClick(View view, IProfile profile, boolean current) {
                        Intent intent = new Intent(MainActivity.this, MyProfileActivity.class);
                        startActivity(intent);
                        return false;
                    }

                    @Override
                    public boolean onProfileImageLongClick(View view, IProfile profile, boolean current) {
                        Toast.makeText(MainActivity.this, "Profil Saya", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .build();

        Drawer.OnDrawerItemClickListener itemClickListener = new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                switch (position) {
                    case 1:
                        // nothing happen
                        break;
                    case 2:
                        // Intent logout and remove user from prefs
                        new AlertDialog.Builder(MainActivity.this)
                                .setIcon(R.drawable.ic_warning_black_24dp)
                                .setTitle("Keluar")
                                .setMessage("Apakah anda ingin keluar dari sesi ini?")
                                .setPositiveButton("YA", (dialogInterface, i) -> {
                                    dialogInterface.dismiss();
                                    sharedPrefsUserRepository.unsetUserFromPrefs();
                                    Intent logoutIntent = new Intent(MainActivity.this, LoginActivity.class);
                                    logoutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(logoutIntent);
                                })
                                .setNegativeButton("TIDAK", (dialogInterface, i)-> drawer.setSelection(1))
                                .show();
                        break;
                    default:
                        break;
                }
                return false;
            }
        };

        drawer = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(header)
                .withToolbar(toolbar)
                .withTranslucentStatusBar(true)
                .withActionBarDrawerToggle(true)
                .addDrawerItems(itemListDosen, itemLogout)
                .withOnDrawerItemClickListener(itemClickListener)
                .build();
    }

    private void initRecyclerView() {
        dosenList = new ArrayList<>();
        fastDosenAdapter = new FastItemAdapter<>();
        dosenFilterList = new ArrayList<>();

        // set to adapter
        fastDosenAdapter.set(dosenList);

        // set adapter to recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(fastDosenAdapter);

        // on click listener of item
        fastDosenAdapter.withSelectable(true);
        fastDosenAdapter.withOnClickListener((v, adapter, item, position) -> {
            int idDosen = item.getUserID();
            Intent intent = new Intent(MainActivity.this, ProfilDosenActivity.class);
            intent.putExtra("UserID", idDosen);
            startActivity(intent);
            return true;
        });
    }

    private void lihatDaftarDosen(List<DosenAdapter> listDosen) {
        dosenList = listDosen;
        fastDosenAdapter.set(dosenList);
        fastDosenAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onClose() {
        filterData("");
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        searchView.clearFocus();
        filterData(s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if (s.trim().isEmpty()) {
            filterData("");
        } else {
            filterData(s);
        }
        return false;
    }

    private void filterData(String query) {
        query = query.trim().toLowerCase();

        dosenFilterList.clear();

        if (query.isEmpty()) {
            dosenFilterList.addAll(dosenList);
            FastItemAdapter<DosenAdapter> newDosenAdapter = new FastItemAdapter<>();
            newDosenAdapter.set(dosenFilterList);
            recyclerView.setAdapter(newDosenAdapter);
        } else {
            dosenFilterList.clear();
            for (DosenAdapter dosen : dosenList) {
                if (dosen.getNamaDosen().toLowerCase().contains(query)) {
                    dosenFilterList.add(dosen);
                }
            }
            FastItemAdapter<DosenAdapter> newDosenAdapter = new FastItemAdapter<>();
            newDosenAdapter.set(dosenFilterList);
            recyclerView.setAdapter(newDosenAdapter);
        }
    }
}

