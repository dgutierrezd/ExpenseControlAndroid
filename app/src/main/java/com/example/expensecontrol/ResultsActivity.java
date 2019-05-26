package com.example.expensecontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {

    private MainActivity mainActivity;
    private ReadWrite readWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        showData();
        // conocerCantidad();
    }

    public void showData() {
        TextView data = (TextView) findViewById(R.id.lDatos);
        data.setText(readWrite.readFileString(ResultsActivity.this));
    }

    public void conocerCantidad() {

        TextView cantidadDinero = (TextView) findViewById(R.id.lDinero);

        // cantidadDinero.setText("Tienes en tu cuenta: $ " + cantidad);
    }

    public void onLimpiar(View view) {
        readWrite.writeFile("", this);
        finish();
        startActivity(getIntent());
        Toast.makeText(this, "Se ha limpiado satisfactoriamente.", Toast.LENGTH_SHORT).show();
    }

}
