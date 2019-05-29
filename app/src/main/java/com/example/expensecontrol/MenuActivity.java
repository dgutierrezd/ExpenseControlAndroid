package com.example.expensecontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void agregarRegistros(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void verRegistros(View view) {
        Intent intent = new Intent(this, ResultsActivity.class);
        startActivity(intent);
        //Toast.makeText(this, Read.readFile(MenuActivity.this), Toast.LENGTH_LONG).show();
    }

    public void onAcercaDe(View view) {
        Intent intent = new Intent(this, AcercaDeActivity.class);
        startActivity(intent);
    }
}
