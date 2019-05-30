package com.example.expensecontrol;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Controla el ingreso y egreso de datos a los archivos de texto.
 * @author Daniel Gutierrez
 * @author Sebastian Cordero
 * @since 20190524
 * @version 1.0
 */
public class Cartera {

    private ArrayList<Movimiento> movimientos;

    private Escritor escritor;

    private Lector lector;

    /**
     * Constante para anexar a los valores de dinero egresado.
     */
    private static final String EGRESO = "Egreso: ";
    /**
     * Constante para anexar a los valores de dinero ingresado.
     */
    private static final String INGRESO = "Ingreso: ";
    /**
     * Valor inicial al dinero que se tiene al comenzar la aplicación.
     */
    private double dinero = 0;

    public Cartera() {
        this.movimientos = new ArrayList<>();
        escritor = new EscritorArchivoTextoPlano();
        lector = new LectorArchivoTextoPlano();
    }

    /**
     * Se anexan los datos necesarios para agregar al archivo de texto.
     * @param monto Cantidad de dinero ingresada.
     * @param categoria Categoria ingresada mediante un spinner.
     * @param estado Especifica si se realizo un gasto o ingreso.
     * @return
     */
    public String generarDatos(String monto, Categoria categoria, String estado, Context context, String descripcion) {
        String datos = null;
        double valorMonto = Double.parseDouble(monto);
        switch(estado) {
            case "Egreso":
                datos = EGRESO + " " + monto + " " + categoria.getClass().getSimpleName() + " " + descripcion;
                Movimiento egreso = new Egreso(valorMonto, descripcion, categoria);
                agregarMovimientos(egreso);
                conocerDinero(valorMonto, estado, context);
                break;
            case "Ingreso":
                datos = INGRESO + " " + monto + " " + descripcion;
                Movimiento ingreso = new Ingreso(valorMonto, descripcion, categoria);
                agregarMovimientos(ingreso);
                conocerDinero(valorMonto, estado, context);
                break;
        }
        return datos;
    }

    public void agregarMovimientos(Movimiento movimiento) {
        movimientos.add(movimiento);
    }

    /**
     * Se lee un archivo de texto para conocer la cantidad de dinero que hay, y dependiendo de la
     * acción que se realice se suma o resta a dicho valor.
     * @param monto Cantidad de dinero ingresada
     * @param estado Accion por realizar.
     */
    public void conocerDinero(double monto, String estado, Context context) {
        String money = lector.readFileString(context, "money.txt");
        double dMoney = Double.parseDouble(money);
        switch(estado) {
            case "Ingreso":
                setDinero(calcularDinero(dMoney, monto));
                break;
            case "Egreso":
                setDinero(calcularDinero(dMoney, -monto));
                break;
        }
        String dato = Double.toString(getDinero());
        escritor.writeFile(dato, context, "money.txt");
    }

    public double calcularDinero(double dinero, double monto) {
        return dinero + monto;
    }

    public Categoria determinarCategoria(String categoria){
        Categoria newCategoria = null;
        switch(categoria){
            case "Comida":
                newCategoria = new Comida();
            break;
            case "Fiesta":
                newCategoria = new Fiesta();
            break;
            case "Transporte":
                newCategoria = new Transporte();
            break;
            case "Universidad":
                newCategoria = new Universidad();
            break;
        }
        return newCategoria;
    }

    public void setDinero(double dinero) {
        this.dinero = dinero;
    }

    public double getDinero() {
        return dinero;
    }
}
