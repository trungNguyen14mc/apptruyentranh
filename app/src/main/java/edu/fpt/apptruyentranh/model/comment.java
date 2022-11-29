package edu.fpt.apptruyentranh.model;

public class comment {
    int idComment;
    String noidung;
    int idtruyen;
    String gio_ngay;
    int idUser;

    public comment(int idComment, String noidung, int idtruyen, String gio_ngay, int idUser) {
        this.idComment = idComment;
        this.noidung = noidung;
        this.idtruyen = idtruyen;
        this.gio_ngay = gio_ngay;
        this.idUser = idUser;
    }
    public String getGio_ngay() {
        return gio_ngay;
    }

    public void setGio_ngay(String gio_ngay) {
        this.gio_ngay = gio_ngay;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public int getIdtruyen() {
        return idtruyen;
    }

    public void setIdtruyen(int idtruyen) {
        this.idtruyen = idtruyen;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
