package com.example.expensecontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

public class AcercaDeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);
        iniciar();
    }

    public void iniciar() {
        DisplayMetrics medidasVentana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentana);

        int ancho = medidasVentana.widthPixels;
        int alto= medidasVentana.heightPixels;

        getWindow().setLayout((int)(ancho * 0.8), (int)(alto *0.5));
    }

    public void onFinish(View view) {
        finish();
    }


}
