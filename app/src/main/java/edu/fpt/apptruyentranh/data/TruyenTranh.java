package edu.fpt.apptruyentranh.data;

import org.json.JSONException;
import org.json.JSONObject;

public class TruyenTranh {
    private String tenTruyen,tenChap,linkAnh,tenTacGia,namXuatBan;

    public TruyenTranh(){

    }

    public TruyenTranh(JSONObject object) throws JSONException {
        tenTruyen = object.getString("tenTruyen");
        tenChap = object.getString("tenChap");
        linkAnh = object.getString("linkAnh");
        tenTacGia = object.getString("tenTacGia");
        namXuatBan = object.getString("namXuatBan");
    }


    public TruyenTranh(String tenTruyen, String tenChap, String linkAnh,String tenTacGia, String namSanXuat) {
        this.tenTruyen = tenTruyen;
        this.tenChap = tenChap;
        this.linkAnh = linkAnh;
        this.tenTacGia = tenTacGia;
        this.namXuatBan = namSanXuat;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public String getTenChap() {
        return tenChap;
    }

    public void setTenChap(String tenChap) {
        this.tenChap = tenChap;
    }

    public String getLinkAnh() {
        return linkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        this.linkAnh = linkAnh;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public String getNamSanXuat() {
        return namXuatBan;
    }

    public void setNamSanXuat(String namSanXuat) {
        this.namXuatBan = namXuatBan;
    }
}
