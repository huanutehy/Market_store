package com.example.market_store.Object;

public class Comment {
    private int idProduct, rating;
    private String account, comment, time;

    public Comment() {
    }

    public Comment(int idProduct, String account, String comment, int rating, String time) {
        this.idProduct = idProduct;
        this.account = account;
        this.comment = comment;
        this.rating = rating;
        this.time = time;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return idProduct +
                ",'" + account +
                "',N'" + comment +
                "'," + rating +
                ", '" + time + "'"
                ;
    }
}
