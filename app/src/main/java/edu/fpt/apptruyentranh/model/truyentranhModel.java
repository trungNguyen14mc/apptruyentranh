package edu.fpt.apptruyentranh.model;

import java.util.List;

public class truyentranhModel {
    boolean success;
    String message;
    List<truyentranh> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<truyentranh> getResult() {
        return result;
    }

    public void setResult(List<truyentranh> result) {
        this.result = result;
    }
}
