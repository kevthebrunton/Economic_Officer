package com.firstproj.economicofficer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Dashboard extends AppCompatActivity{
    Button btnAdd,btnMaps,btIncome,btExpenses,btSettings;
    private SensorManager sensorManager;
    private Sensor sensor;
    private TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        textView = findViewById(R.id.textView13);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        textView.setText(String.valueOf(sensor.getPower()));

        btnAdd = findViewById(R.id.btnAddExpenses);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,Add.class);
                startActivity(intent);
            }
        });

        btnMaps = findViewById(R.id.btnRegularStores);
        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,Maps.class);
                startActivity(intent);
            }
        });

        btIncome = findViewById(R.id.btnIncome);
        btIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,Income.class);
                startActivity(intent);
            }
        });

        btExpenses = findViewById(R.id.btnExpenses);
        btExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(Dashboard.this,Expenses.class);
                startActivity(intent);
            }
        });

        btSettings = findViewById(R.id.btnSettings);
        btSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(Dashboard.this,Settings.class);
                startActivity(intent);
            }
        });


    }
}