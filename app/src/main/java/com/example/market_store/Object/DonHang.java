package com.example.market_store.Object;

public class DonHang {
    private int idDH, status, sdt;
    private String account, hoten, diachi, ngaymua;

    public DonHang() {
    }

    public DonHang(int idDH, String account, String hoten,  int sdt, String diachi, int status, String ngaymua) {
        this.idDH = idDH;
        this.account = account;
        this.status = status;
        this.sdt = sdt;
        this.hoten = hoten;
        this.diachi = diachi;
        this.ngaymua = ngaymua;
    }

    public int getIdDH() {
        return idDH;
    }

    public void setIdDH(int idDH) {
        this.idDH = idDH;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getNgaymua() {
        return ngaymua;
    }

    public void setNgaymua(String ngaymua) {
        this.ngaymua = ngaymua;
    }

    @Override
    public String toString() {
        return  idDH +
                ", '" + account +
                "', N'" + hoten +
                "', " + sdt +
                ", N'" + diachi +
                "', " + status +
                ", '" + ngaymua +"'";
    }
}
