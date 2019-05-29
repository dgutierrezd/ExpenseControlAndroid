package com.example.expensecontrol;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Se piden los archivos y se muestran sus datos
 *
 */
public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        showData();
        conocerCantidad();
    }

    public void showData() {
        TextView data = (TextView) findViewById(R.id.lDatos);
        Lector lector = new LectorArchivoTextoPlano();
        data.setText(lector.readFileString(ResultsActivity.this, "datos.txt"));
    }

    public void conocerCantidad() {

        TextView cantidadDinero = (TextView) findViewById(R.id.lDinero);
        Lector lector = new LectorArchivoTextoPlano();
        String money = lector.readFileString(this, "money.txt");
        double dMoney = Double.parseDouble(money);

        if(dMoney < 0) {
            cantidadDinero.setTextColor(Color.RED);
        }

        cantidadDinero.setText("$ " + dMoney);
    }

    public void onLimpiar(View view) {
        Escritor escritor = new EscritorArchivoTextoPlano();
        escritor.writeFile("", this, "datos.txt");
        escritor.writeFile("0.0", this, "money.txt");
        finish();
        startActivity(getIntent());
        Toast.makeText(this, "Se ha limpiado satisfactoriamente.", Toast.LENGTH_SHORT).show();
    }

    public void onFinish(View view) {
        finish();
    }

}
