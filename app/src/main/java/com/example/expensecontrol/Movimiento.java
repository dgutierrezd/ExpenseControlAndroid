package com.example.expensecontrol;

public abstract class Movimiento {

    private double monto;
    private String descripion;
    private Categoria categoria;

    public Movimiento(double monto, String descripion, Categoria categoria) {
        this.monto = monto;
        this.descripion = descripion;
        this.categoria = categoria;
    }
}
