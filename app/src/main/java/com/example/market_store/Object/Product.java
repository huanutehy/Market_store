package com.example.market_store.Object;

import java.util.Arrays;

public class Product{
    private int idProduct, baohanh, gia;
    private String tensp, nsx, chitiet, khuyenmai, review, ngaynhap;
    private String hinhanh;

    public Product() {
    }

    public Product(int idProduct, String tensp, String hinhanh, String nsx, String chitiet, String khuyenmai, int baohanh, int gia, String review, String ngaynhap) {
        this.idProduct = idProduct;
        this.baohanh = baohanh;
        this.gia = gia;
        this.tensp = tensp;
        this.nsx = nsx;
        this.chitiet = chitiet;
        this.khuyenmai = khuyenmai;
        this.review = review;
        this.hinhanh = hinhanh;
        this.ngaynhap = ngaynhap;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getBaohanh() {
        return baohanh;
    }

    public void setBaohanh(int baohanh) {
        this.baohanh = baohanh;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getNsx() {
        return nsx;
    }

    public void setNsx(String nsx) {
        this.nsx = nsx;
    }

    public String getChitiet() {
        return chitiet;
    }

    public void setChitiet(String chitiet) {
        this.chitiet = chitiet;
    }

    public String getKhuyenmai() {
        return khuyenmai;
    }

    public void setKhuyenmai(String khuyenmai) {
        this.khuyenmai = khuyenmai;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getNgaynhap() {
        return ngaynhap;
    }

    public void setNgaynhap(String ngaynhap) {
        this.ngaynhap = ngaynhap;
    }

    @Override
    public String toString() {
        return  "idProduct=" + idProduct +
                ", baohanh=" + baohanh +
                ", gia=" + gia +
                ", tensp='" + tensp +
                ", nsx='" + nsx +
                ", chitiet='" + chitiet +
                ", khuyenmai='" + khuyenmai +
                ", review='" + review +
                ", ngaynhap='" + ngaynhap ;
    }

}
