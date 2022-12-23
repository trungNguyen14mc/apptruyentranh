package edu.fpt.apptruyentranh.model;

import java.util.List;

public class imgModel {
    boolean success;
    String message;
    List<img_truyen> result;
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

    public List<img_truyen> getResult() {
        return result;
    }

    public void setResult(List<img_truyen> result) {
        this.result = result;
    }
}
