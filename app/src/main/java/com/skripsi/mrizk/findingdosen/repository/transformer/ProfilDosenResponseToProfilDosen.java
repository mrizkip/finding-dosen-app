package com.skripsi.mrizk.findingdosen.repository.transformer;

import com.skripsi.mrizk.findingdosen.repository.entity.api.ProfilDosenResponse;
import com.skripsi.mrizk.findingdosen.repository.entity.local.ProfilDosen;
import com.skripsi.mrizk.findingdosen.repository.utils.BaseLayerDataTransformer;

import javax.inject.Inject;

public class ProfilDosenResponseToProfilDosen extends BaseLayerDataTransformer<ProfilDosenResponse, ProfilDosen> {

    @Inject
    public ProfilDosenResponseToProfilDosen() {
    }

    @Override
    public ProfilDosen transform(ProfilDosenResponse from) {
        ProfilDosen dosen = new ProfilDosen();
        dosen.setUserId(from.getUserId());
        dosen.setNama(from.getNama());
        dosen.setEmail(from.getEmail());
        dosen.setJenisIdentitas(from.getJenisIdentitas());
        dosen.setNoIdentitas(from.getNoIdentitas());
        dosen.setNoTelpon(from.getNoTelpon());
        dosen.setStatus(from.getStatus());
        dosen.setDescStatus(from.getDescStatus());
        dosen.setKetStatus(from.getKetStatus());
        return dosen;
    }
}