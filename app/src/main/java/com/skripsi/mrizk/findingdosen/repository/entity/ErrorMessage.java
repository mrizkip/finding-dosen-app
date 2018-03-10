package com.skripsi.mrizk.findingdosen.repository.entity;

/**
 * Created by mrizk on 08/03/2018.
 */

public class ErrorMessage {

    private String errorMessage;
    private Integer errorCode;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}
