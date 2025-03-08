package com.procedimientos.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Getter
@Setter
public class CortoCircuitoResultado {
    private double LL;
    private double LN;
    private int FC;
    private int NC;

    // Getters y setters

    public double getLL() {
        return LL;
    }

    public void setLL(double LL) {
        this.LL = LL;
    }

    public double getLN() {
        return LN;
    }

    public void setLN(double LN) {
        this.LN = LN;
    }

    public int getFC() {
        return FC;
    }

    public void setFC(int FC) {
        this.FC = FC;
    }

    public int getNC() {
        return NC;
    }

    public void setNC(int NC) {
        this.NC = NC;
    }

}