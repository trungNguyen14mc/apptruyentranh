package edu.fpt.apptruyentranh.model;

import java.io.Serializable;

public class truyentranh implements Serializable {
    int idtruyen;
    String tenTruyen;
    String tenTacGia;
    String namXuatBan;
    String anhBia;
    String mota;
    int soluongchap;

    public int getSoluongchap() {
        return soluongchap;
    }

    public void setSoluongchap(int soluongchap) {
        this.soluongchap = soluongchap;
    }

    public int getIdtruyen() {
        return idtruyen;
    }

    public void setIdtruyen(int idtruyen) {
        this.idtruyen = idtruyen;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public String getNamXuatBan() {
        return namXuatBan;
    }

    public void setNamXuatBan(String namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    public String getAnhBia() {
        return anhBia;
    }

    public void setAnhBia(String anhBia) {
        this.anhBia = anhBia;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
