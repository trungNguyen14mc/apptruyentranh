package edu.fpt.apptruyentranh.model;

public class comment {
    int idComment;
    String noidung;
    int idtruyen;
    String ngay_gio;
    int idUser;

    public comment(int idComment, String noidung, int idtruyen, String ngay_gio, int idUser) {
        this.idComment = idComment;
        this.noidung = noidung;
        this.idtruyen = idtruyen;
        this.ngay_gio = ngay_gio;
        this.idUser = idUser;
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public int getIdtruyen() {
        return idtruyen;
    }

    public void setIdtruyen(int idtruyen) {
        this.idtruyen = idtruyen;
    }

    public String getNgay_gio() {
        return ngay_gio;
    }

    public void setNgay_gio(String ngay_gio) {
        this.ngay_gio = ngay_gio;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
