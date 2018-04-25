package com.skripsi.mrizk.findingdosen.repository.transformer;

import com.skripsi.mrizk.findingdosen.main.mainMahasiswa.DosenAdapter;
import com.skripsi.mrizk.findingdosen.repository.entity.api.DosenListResponse;
import com.skripsi.mrizk.findingdosen.repository.utils.BaseLayerDataTransformer;

import javax.inject.Inject;

public class DosenListResponseToDosenAdapter extends BaseLayerDataTransformer<DosenListResponse, DosenAdapter> {

    @Inject
    public DosenListResponseToDosenAdapter() {
    }

    @Override
    public DosenAdapter transform(DosenListResponse from) {
        DosenAdapter dosen = new DosenAdapter();
        dosen.setUserID(from.getUserId());
        dosen.setNamaDosen(from.getNama());
        dosen.setKehadiranDosen(from.getStatus());
        return dosen;
    }
}
