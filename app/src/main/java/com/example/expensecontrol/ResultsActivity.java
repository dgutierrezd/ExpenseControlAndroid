package com.example.expensecontrol;

import android.content.res.ColorStateList;
import android.graphics.Color;
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
        conocerCantidad();
    }

    public void showData() {
        TextView data = (TextView) findViewById(R.id.lDatos);
        data.setText(readWrite.readFileString(ResultsActivity.this, "datos.txt"));
    }

    public void conocerCantidad() {

        TextView cantidadDinero = (TextView) findViewById(R.id.lDinero);

        String money = readWrite.readFileString(this, "money.txt");
        double dMoney = Double.parseDouble(money);

        if(dMoney < 0) {
            cantidadDinero.setTextColor(Color.RED);
        }

        cantidadDinero.setText("$ " + dMoney);
    }

    public void onLimpiar(View view) {
        readWrite.writeFile("", this, "datos.txt");
        readWrite.writeFile("0.0", this, "money.txt");
        finish();
        startActivity(getIntent());
        Toast.makeText(this, "Se ha limpiado satisfactoriamente.", Toast.LENGTH_SHORT).show();
    }

    public void onFinish(View view) {
        finish();
    }

}
