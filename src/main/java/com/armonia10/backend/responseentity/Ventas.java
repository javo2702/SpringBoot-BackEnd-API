package com.armonia10.backend.responseentity;

import javax.persistence.Entity;
import java.util.Date;

public class Ventas {
    private double Monto;
    private String fecha;
    public Ventas(){}

    public Ventas(double monto, String fecha) {
        Monto = monto;
        this.fecha = fecha;
    }

    public double getMonto() {
        return Monto;
    }

    public void setMonto(double monto) {
        Monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
