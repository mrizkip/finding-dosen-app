package com.skripsi.mrizk.findingdosen.repository.entity.api;

public class RubahStatusRequest {
    private int user_id;
    private String status;
    private String desc_status;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc_status() {
        return desc_status;
    }

    public void setDesc_status(String desc_status) {
        this.desc_status = desc_status;
    }
}
