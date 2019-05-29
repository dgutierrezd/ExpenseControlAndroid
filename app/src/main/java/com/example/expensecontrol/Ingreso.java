package com.example.expensecontrol;

public class Ingreso extends Acciones {


    @Override
    public double ejecutar(double dinero, double monto) {
        return dinero + monto;
    }
}
