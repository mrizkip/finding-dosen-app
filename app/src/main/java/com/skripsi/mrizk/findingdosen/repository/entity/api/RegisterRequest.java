package com.skripsi.mrizk.findingdosen.repository.entity.api;

/**
 * Created by mrizk on 15/03/2018.
 */

public class RegisterRequest {
    private String email;
    private String password;
    private String nama;
    private String jenis_identitas;
    private String no_identitas;
    private String no_telpon;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenis_identitas() {
        return jenis_identitas;
    }

    public void setJenis_identitas(String jenis_identitas) {
        this.jenis_identitas = jenis_identitas;
    }

    public String getNo_identitas() {
        return no_identitas;
    }

    public void setNo_identitas(String no_identitas) {
        this.no_identitas = no_identitas;
    }

    public String getNo_telpon() {
        return no_telpon;
    }

    public void setNo_telpon(String no_telpon) {
        this.no_telpon = no_telpon;
    }
}
