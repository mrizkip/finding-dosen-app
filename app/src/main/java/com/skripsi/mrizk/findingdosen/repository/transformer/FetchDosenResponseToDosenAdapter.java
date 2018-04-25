package com.skripsi.mrizk.findingdosen.repository.transformer;

import com.skripsi.mrizk.findingdosen.main.mainMahasiswa.DosenAdapter;
import com.skripsi.mrizk.findingdosen.repository.entity.api.FetchDosenResponse;
import com.skripsi.mrizk.findingdosen.repository.utils.BaseLayerDataTransformer;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by mrizk on 16/03/2018.
 */

public class FetchDosenResponseToDosenAdapter extends BaseLayerDataTransformer<FetchDosenResponse, List<DosenAdapter>> {

    private final DosenListResponseToDosenAdapter dosenListResponseToDosenAdapter;

    @Inject
    public FetchDosenResponseToDosenAdapter(DosenListResponseToDosenAdapter dosenListResponseToDosenAdapter) {
        this.dosenListResponseToDosenAdapter = dosenListResponseToDosenAdapter;
    }

    @Override
    public List<DosenAdapter> transform(FetchDosenResponse from) {
        List<DosenAdapter> dosenList = dosenListResponseToDosenAdapter.transform(from.getData());
        return dosenList;
    }
}
