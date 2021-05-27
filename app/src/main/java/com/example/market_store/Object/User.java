package com.example.market_store.Object;

public class User {
    private String account, password, hoten, ngaysinh, email, diachi, ngaytao;
    private int SDT, loaitk;
    private long CMT;
    public User() {
    }

    public User(String account, String password, String hoten, String ngaysinh, String email, String diachi, long CMT, int SDT, String ngaytao, int loaitk) {
        this.account = account;
        this.password = password;
        this.hoten = hoten;
        this.ngaysinh = ngaysinh;
        this.email = email;
        this.diachi = diachi;
        this.CMT = CMT;
        this.SDT = SDT;
        this.ngaytao = ngaytao;
        this.loaitk = loaitk;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public long getCMT() {
        return CMT;
    }

    public void setCMT(long CMT) {
        this.CMT = CMT;
    }

    public int getSDT() {
        return SDT;
    }

    public void setSDT(int SDT) {
        this.SDT = SDT;
    }

    public String getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(String ngaytao) {
        this.ngaytao = ngaytao;
    }

    public int getLoaitk() {
        return loaitk;
    }

    public void setLoaitk(int loaitk) {
        this.loaitk = loaitk;
    }

    @Override
    public String toString() {
        return  "'" + account +
                "','" + password +
                "','" + hoten +
                "','" + ngaysinh +
                "','" + email +
                "','" + diachi +
                "'," + CMT +
                "," + SDT +
                ",'" + ngaytao +
                "'," + loaitk;
    }
}
