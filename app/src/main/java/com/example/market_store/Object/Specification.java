package com.example.market_store.Object;

public class Specification {
    private int idProduct, RAM, ROM;
    private String manhinh, hdh, camera, CPU, sim, thenho, pin;

    public Specification() {
    }

    public Specification(int idProduct, String manhinh, String hdh, String camera, String CPU, int RAM, int ROM, String sim, String thenho, String pin) {
        this.idProduct = idProduct;
        this.RAM = RAM;
        this.ROM = ROM;
        this.manhinh = manhinh;
        this.hdh = hdh;
        this.camera = camera;
        this.CPU = CPU;
        this.sim = sim;
        this.thenho = thenho;
        this.pin = pin;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getRAM() {
        return RAM;
    }

    public void setRAM(int RAM) {
        this.RAM = RAM;
    }

    public int getROM() {
        return ROM;
    }

    public void setROM(int ROM) {
        this.ROM = ROM;
    }

    public String getManhinh() {
        return manhinh;
    }

    public void setManhinh(String manhinh) {
        this.manhinh = manhinh;
    }

    public String getHdh() {
        return hdh;
    }

    public void setHdh(String hdh) {
        this.hdh = hdh;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public String getSim() {
        return sim;
    }

    public void setSim(String sim) {
        this.sim = sim;
    }

    public String getThenho() {
        return thenho;
    }

    public void setThenho(String thenho) {
        this.thenho = thenho;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return  "idProduct=" + idProduct +
                ", RAM=" + RAM +
                ", ROM=" + ROM +
                ", manhinh=" + manhinh +
                ", hdh=" + hdh +
                ", camera=" + camera +
                ", CPU=" + CPU +
                ", sim=" + sim +
                ", thenho=" + thenho +
                ", pin=" + pin;
    }
}
