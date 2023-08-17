package com.firstproj.economicofficer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class delete_income extends AppCompatActivity {
    Button btnBack, btnDeleteIncome;
    DatabaseHelper MyDB;
    EditText id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_income);

        btnBack = findViewById(R.id.btnBackToIncome1);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(delete_income.this,Income.class);
                startActivity(intent);
            }
        });

        id = findViewById(R.id.income_id);
        btnDeleteIncome = findViewById(R.id.btnDeleteIncome2);
        btnDeleteIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDB = new DatabaseHelper(delete_income.this);
                MyDB.deleteIncomeRow(id.getText().toString());
                finish();

            }
        });
    }


}