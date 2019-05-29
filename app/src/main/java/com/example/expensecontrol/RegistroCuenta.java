package com.example.expensecontrol;

import android.content.Context;
import android.support.v7.app.AlertDialog;

/**
 * Controla el ingreso y egreso de datos a los archivos de texto.
 * @author Daniel Gutierrez
 * @author Sebastian Cordero
 * @since 20190524
 * @version 1.0
 */
public class RegistroCuenta {
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
    public String guardarDatos(String monto, String categoria, String estado, Context context) {
        String datos = null;
        double valorMonto = Double.parseDouble(monto);
        switch(estado) {
            case "Egreso":
                datos = EGRESO + " " + monto + " " + categoria;
                conocerDinero(valorMonto, estado, context);
                break;
            case "Ingreso":
                datos = INGRESO + " " + monto ;
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
        String money = Reader.readFileString(context, "money.txt");
        double dMoney = Double.parseDouble(money);
        switch(estado) {
            case "Ingreso":
                setDinero( dMoney + monto);
                break;
            case "Egreso":
                setDinero(dMoney - monto);
                break;
        }
        String dato = Double.toString(getDinero());
        Writer.writeFile(dato, context, "money.txt");
    }

    /**
     * Crear dialogo alerta.
     * @param mensaje String que se va a mostra en el diálogo.
     */
    public void crearDialogoSimple(String mensaje, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(mensaje);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void setDinero(double dinero) {
        this.dinero = dinero;
    }

    public double getDinero() {
        return dinero;
    }
}
