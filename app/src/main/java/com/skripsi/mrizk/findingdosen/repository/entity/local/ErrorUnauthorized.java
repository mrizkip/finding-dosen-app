package com.skripsi.mrizk.findingdosen.repository.entity.local;

/**
 * Created by mrizk on 08/03/2018.
 */

public class ErrorUnauthorized {

    private String message;
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
