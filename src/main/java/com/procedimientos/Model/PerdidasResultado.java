package com.procedimientos.Model;

public class PerdidasResultado {
    private double perdidas;
    private double corriente;
    private double perdidasR;
    private int capacidadCorrientePermisible;

    // Getters y Setters
    public double getPerdidas() {
        return perdidas;
    }

    public void setPerdidas(double perdidas) {
        this.perdidas = perdidas;
    }

    public double getCorriente() {
        return corriente;
    }

    public void setCorriente(double corriente) {
        this.corriente = corriente;
    }

    public double getPerdidasR() {
        return perdidasR;
    }

    public void setPerdidasR(double perdidasR) {
        this.perdidasR = perdidasR;
    }

    public int getCapacidadCorrientePermisible() {
        return capacidadCorrientePermisible;
    }

    public void setCapacidadCorrientePermisible(int capacidadCorrientePermisible) {
        this.capacidadCorrientePermisible = capacidadCorrientePermisible;
    }
}
