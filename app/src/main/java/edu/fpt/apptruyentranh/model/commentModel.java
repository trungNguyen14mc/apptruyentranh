package edu.fpt.apptruyentranh.model;

import java.util.List;

public class commentModel {
    boolean success;
    String message;
    List<comment> result;

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

    public List<comment> getResult() {
        return result;
    }

    public void setResult(List<comment> result) {
        this.result = result;
    }
}
