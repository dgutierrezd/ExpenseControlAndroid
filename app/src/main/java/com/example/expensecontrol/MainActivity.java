package com.example.expensecontrol;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String dato;
    private static final String EGRESO = "Egreso: ";
    private static final String INGRESO = "Ingreso: ";
    private double dinero = 0;
    protected String filePath = "datos.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        habilitarCategorias(false);
        verificarCheckBox();
    }

    public void onAgregar(View view) {
        EditText eMonto = (EditText) findViewById(R.id.tMonto);
        Spinner sCategorias = (Spinner) findViewById(R.id.sCategorias);

        CheckBox egreso = (CheckBox) findViewById(R.id.cEgreso);
        CheckBox ingreso = (CheckBox) findViewById(R.id.cIngreso);

        String monto = eMonto.getText().toString();

        if((!egreso.isChecked() && !ingreso.isChecked() || monto.isEmpty()) || egreso.isChecked() && sCategorias.getSelectedItemPosition() == 0) {
            crearDialogoSimple("Debes llenar todos los campos requeridos.");

        } else {
            String categoria = String.valueOf(sCategorias.getSelectedItem());
            String datos = Read.readFileString(MainActivity.this, filePath) + guardarDatos(monto, categoria, verificarCheckBox());

            Write.writeFile(datos, this, filePath);
            Toast.makeText(this, "Se ha guardado satisfactoriamente.", Toast.LENGTH_SHORT).show();
            limpiarEspacios();
        }


    }

    public String guardarDatos(String monto, String categoria, String estado) {
        String datos = null;
        double valorMonto = Double.parseDouble(monto);
        switch(estado) {
            case "Egreso":
                datos = EGRESO + " " + monto + " " + categoria;
                conocerDinero(valorMonto, estado);
            break;
            case "Ingreso":
                datos = INGRESO + " " + monto ;
                conocerDinero(valorMonto, estado);
            break;
        }
        return datos;
    }

    public void habilitarCategorias(Boolean opcion) {
        Spinner sCategorias = (Spinner) findViewById(R.id.sCategorias);
        sCategorias.setEnabled(opcion);
        sCategorias.setClickable(opcion);
    }

    public void conocerDinero(double monto, String estado) {
        String money = Read.readFileString(this, "money.txt");
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
        Write.writeFile(dato, this, "money.txt");
    }

    public void crearDialogoSimple(String mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(mensaje);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public String verificarCheckBox() {
        final CheckBox egreso = (CheckBox) findViewById(R.id.cEgreso);
        final CheckBox ingreso = (CheckBox) findViewById(R.id.cIngreso);


        egreso.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(egreso.isChecked()) {
                    Toast.makeText(MainActivity.this, "Egreso Checked", Toast.LENGTH_SHORT).show();
                    ingreso.setChecked(false);
                    habilitarCategorias(true);
                    dato = "Egreso";
                } else {
                    Toast.makeText(MainActivity.this, "Egreso Un-Checked", Toast.LENGTH_SHORT).show();
                    habilitarCategorias(false);
                }
            }
        });

        ingreso.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(ingreso.isChecked()) {
                    Toast.makeText(MainActivity.this, "Ingreso Checked", Toast.LENGTH_LONG).show();
                    egreso.setChecked(false);
                    habilitarCategorias(false);
                    dato = "Ingreso";
                } else {
                    Toast.makeText(MainActivity.this, "Ingreso Un-Checked", Toast.LENGTH_LONG).show();
                }
            }
        });
        return dato;
    }

    public void limpiarEspacios() {
        EditText eMonto = (EditText) findViewById(R.id.tMonto);
        Spinner sCategorias = (Spinner) findViewById(R.id.sCategorias);

        CheckBox egreso = (CheckBox) findViewById(R.id.cEgreso);
        CheckBox ingreso = (CheckBox) findViewById(R.id.cIngreso);

        eMonto.setText("");
        egreso.setChecked(false);
        ingreso.setChecked(false);
        sCategorias.setSelection(0);
    }

    public void onFinish(View view) {
        finish();
    }

    public void setDinero(double dinero) {
        this.dinero = dinero;
    }

    public double getDinero() {
        return dinero;
    }

}
