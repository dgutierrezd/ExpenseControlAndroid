package com.example.expensecontrol;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * Se ingresa los egresos o egresos.
 * @author Daniel Gutierrez & Sebastian Cordero
 * @since 20190524
 * @version 1.0
 */
public class AddDataActivity extends AppCompatActivity {

    /**
     * Linea que se va a ingresar al archivo de texto.
     */
    private String dato;
    /**
     * Ruta de archivo donde se guardan los datos.
     */
    protected String filePath = "datos.txt";

    private Cartera registroCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        habilitarCategorias(false);
        verificarCheckBox();
        registroCuenta = new Cartera();
    }

    /**
     * Se agregan los valores como String al archivo de texto y se hacen las validaciones neesarias.
     * @param view
     */
    public void onAgregar(View view) {
        EditText eMonto = (EditText) findViewById(R.id.tMonto);
        Spinner sCategorias = (Spinner) findViewById(R.id.sCategorias);

        CheckBox egreso = (CheckBox) findViewById(R.id.cEgreso);
        CheckBox ingreso = (CheckBox) findViewById(R.id.cIngreso);

        String monto = eMonto.getText().toString();

        if((!egreso.isChecked() && !ingreso.isChecked() || monto.isEmpty()) || egreso.isChecked() && sCategorias.getSelectedItemPosition() == 0) {
            crearDialogoSimple("Debes llenar todos los campos requeridos.", this);

        } else {
            String categoria = String.valueOf(sCategorias.getSelectedItem());
            Lector lector = new LectorArchivoTextoPlano();
            String datos = lector.readFileString(AddDataActivity.this, filePath) + registroCuenta.generarDatos(monto, categoria, verificarCheckBox(), this);

            Escritor escritor = new EscritorArchivoTextoPlano();
            escritor.writeFile(datos, this, filePath);
            Toast.makeText(this, "Se ha guardado satisfactoriamente.", Toast.LENGTH_SHORT).show();
            limpiarEspacios();
        }


    }

    /**
     * Dependiendo de la opcion ingresada se habilita el spinner de 'categorias'.
     * @param opcion
     */
    public void habilitarCategorias(Boolean opcion) {
        Spinner sCategorias = (Spinner) findViewById(R.id.sCategorias);
        sCategorias.setEnabled(opcion);
        sCategorias.setClickable(opcion);
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

    /**
     * Se verifica el estado de los checkbox.
     * @return String de checkbox seleccionado.
     */
    public String verificarCheckBox() {
        final CheckBox egreso = (CheckBox) findViewById(R.id.cEgreso);
        final CheckBox ingreso = (CheckBox) findViewById(R.id.cIngreso);


        egreso.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(egreso.isChecked()) {
                    Toast.makeText(AddDataActivity.this, "Egreso Checked", Toast.LENGTH_SHORT).show();
                    ingreso.setChecked(false);
                    habilitarCategorias(true);
                    dato = "Egreso";
                } else {
                    Toast.makeText(AddDataActivity.this, "Egreso Un-Checked", Toast.LENGTH_SHORT).show();
                    habilitarCategorias(false);
                }
            }
        });

        ingreso.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(ingreso.isChecked()) {
                    Toast.makeText(AddDataActivity.this, "Ingreso Checked", Toast.LENGTH_LONG).show();
                    egreso.setChecked(false);
                    habilitarCategorias(false);
                    dato = "Ingreso";
                } else {
                    Toast.makeText(AddDataActivity.this, "Ingreso Un-Checked", Toast.LENGTH_LONG).show();
                }
            }
        });
        return dato;
    }

    /**
     * Se modifican vacios los espacios.
     */
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

    /**
     * Salir del Activity.
     * @param view
     */
    public void onFinish(View view) {
        finish();
    }

}
