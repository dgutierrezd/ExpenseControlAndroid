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

        String value = eMonto.getText().toString();

        if(!egreso.isChecked() && !ingreso.isChecked() || value.isEmpty()) {
            crearDialogoSimple("Debes llenar todos los campos requeridos.");
        } else {
            // int monto = Integer.parseInt(value);
            String categoria = String.valueOf(sCategorias.getSelectedItem());

            ReadWrite.writeFile(value, this);
            Toast.makeText(this, "Se ha guardado satisfactoriamente.", Toast.LENGTH_SHORT).show();
            limpiarEspacios();
        }


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

    public void verificarCheckBox() {
        final CheckBox egreso = (CheckBox) findViewById(R.id.cEgreso);
        final CheckBox ingreso = (CheckBox) findViewById(R.id.cIngreso);

        egreso.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(egreso.isChecked()) {
                    Toast.makeText(MainActivity.this, "Egreso Checked", Toast.LENGTH_SHORT).show();
                    ingreso.setChecked(false);
                    habilitarCategorias(true);

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
                } else {
                    Toast.makeText(MainActivity.this, "Ingreso Un-Checked", Toast.LENGTH_LONG).show();
                }
            }
        });
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
