package com.example.expensecontrol;

import android.content.Context;
/**
 * Controla el ingreso y egreso de datos a los archivos de texto.
 * @author Daniel Gutierrez
 * @author Sebastian Cordero
 * @since 20190524
 * @version 1.0
 */
public class Cartera {

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
                conocerDinero(valorMonto, estado, context);
                break;
            case "Ingreso":
                datos = INGRESO + " " + monto + " " + descripcion;
                conocerDinero(valorMonto, estado, context);
                break;
        }
        return datos;
    }

    /**
     * Se lee un archivo de texto para conocer la cantidad de dinero que hay, y dependiendo de la
     * acción que se realice se suma o resta a dicho valor.
     * @param monto Cantidad de dinero ingresada
     * @param estado Accion por realizar.
     */
    public void conocerDinero(double monto, String estado, Context context) {
        Lector lector = new LectorArchivoTextoPlano();
        String money = lector.readFileString(context, "money.txt");
        double dMoney = Double.parseDouble(money);
        switch(estado) {
            case "Ingreso":
                Movimiento ingreso = new Ingreso();

                setDinero(ingreso.ejecutar(dMoney, monto));
                break;
            case "Egreso":
                Movimiento egreso = new Egreso();
                setDinero(egreso.ejecutar(dMoney, monto));
                break;
        }
        String dato = Double.toString(getDinero());
        Escritor escritor = new EscritorArchivoTextoPlano();
        escritor.writeFile(dato, context, "money.txt");
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