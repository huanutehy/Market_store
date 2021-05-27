package com.example.market_store.Object;

public class CTDH {
    private int idDH, idProduct, soluong, gia;

    public CTDH() {
    }

    public CTDH(int idDH, int idProduct, int soluong, int gia) {
        this.idDH = idDH;
        this.idProduct = idProduct;
        this.soluong = soluong;
        this.gia = gia;
    }

    public int getIdDH() {
        return idDH;
    }

    public void setIdDH(int idDH) {
        this.idDH = idDH;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int tinhtien(){
        return soluong*gia;
    }
    @Override
    public String toString() {
        return  idDH +
                "," + idProduct +
                "," + soluong +
                "," + gia ;
    }
}
