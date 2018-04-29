package com.skripsi.mrizk.findingdosen.main.mainMahasiswa;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.materialize.holder.StringHolder;
import com.skripsi.mrizk.findingdosen.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mrizk on 17/03/2018.
 */

public class DosenAdapter extends AbstractItem<DosenAdapter, DosenAdapter.ViewHolder> {

    private int userID;
    private String namaDosen;
    private String kehadiranDosen;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getNamaDosen() {
        return namaDosen;
    }

    public void setNamaDosen(String namaDosen) {
        this.namaDosen = namaDosen;
    }

    public String getKehadiranDosen() {
        return kehadiranDosen;
    }

    public void setKehadiranDosen(String kehadiranDosen) {
        this.kehadiranDosen = kehadiranDosen;
    }

    @NonNull
    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    public int getType() {
        return R.id.listDosen_adapter;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.list_dosen_adapter;
    }

    protected static class ViewHolder extends FastAdapter.ViewHolder<DosenAdapter> {

        @BindView(R.id.listDosen_profileImage)
        ImageView profileImage;
        @BindView(R.id.listDosen_nama)
        TextView nama;
        @BindView(R.id.listDosen_hadir)
        TextView hadir;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindView(DosenAdapter item, List<Object> payloads) {
            Picasso.get().load("file:///android_asset/profile-placeholder.png").resize(72,72).centerCrop().into(profileImage);
            nama.setText(item.getNamaDosen());
            hadir.setText(item.getKehadiranDosen());
            if (item.getKehadiranDosen().equalsIgnoreCase("Aktif")) {
                hadir.setBackgroundResource(R.drawable.label_hadir);
            } else {
                hadir.setBackgroundResource(R.drawable.label_tidak_hadir);
            }
        }

        @Override
        public void unbindView(DosenAdapter item) {
            profileImage.setImageDrawable(null);
            nama.setText(null);
            hadir.setText(null);
        }
    }

}
