package com.skripsi.mrizk.findingdosen.repository.transformer;

import android.annotation.SuppressLint;

import com.skripsi.mrizk.findingdosen.repository.entity.api.PosisiDosenResponse;
import com.skripsi.mrizk.findingdosen.repository.entity.local.PosisiDosen;
import com.skripsi.mrizk.findingdosen.repository.utils.BaseLayerDataTransformer;


import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

public class PosisiDosenResponseToPosisiDosen extends BaseLayerDataTransformer<PosisiDosenResponse, PosisiDosen> {

    @Inject
    public PosisiDosenResponseToPosisiDosen() {
    }

    @Override
    public PosisiDosen transform(PosisiDosenResponse from) {
        PosisiDosen posisi = new PosisiDosen();
        posisi.setUserId(from.getUserId());
        posisi.setNama(from.getNama());
        posisi.setPosisi(from.getPosisi());
        String preFormated = from.getLastUpdate();
        DateTimeFormatter parser = ISODateTimeFormat.dateTimeNoMillis();
        DateTime dateTime = new DateTime(parser.parseDateTime(preFormated));
        DateTimeFormatter formatter = DateTimeFormat.forPattern("EEE, dd MMMM yyyy, HH:mm");
        String newDate = formatter.print(dateTime);
        posisi.setLastUpdate(newDate);
        return posisi;
    }
}
