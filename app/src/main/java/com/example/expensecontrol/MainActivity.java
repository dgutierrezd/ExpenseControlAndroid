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

    String dato;

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

        if(!egreso.isChecked() && !ingreso.isChecked() || monto.isEmpty()) {
            crearDialogoSimple("Debes llenar todos los campos requeridos.");
        } else {
            // int monto = Integer.parseInt(value);
            String categoria = String.valueOf(sCategorias.getSelectedItem());

            ReadWrite.writeFile(guardarDatos(monto, categoria, verificarCheckBox()), this);
            Toast.makeText(this, "Se ha guardado satisfactoriamente.", Toast.LENGTH_SHORT).show();
            limpiarEspacios();
        }


    }

    public String guardarDatos(String monto, String categoria, String estado) {
        String datos = null;
        switch(estado) {
            case "Egreso":
                datos = monto + " " + categoria + " " + estado;
            break;
            case "Ingreso":
                datos = monto + " " + estado;
            break;
        }
        return datos;
    }

    public void habilitarCategorias(Boolean opcion) {
        Spinner sCategorias = (Spinner) findViewById(R.id.sCategorias);
        sCategorias.setEnabled(opcion);
        sCategorias.setClickable(opcion);
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
        ingreso.setChecked(true);
        sCategorias.setSelection(0);
    }

}
