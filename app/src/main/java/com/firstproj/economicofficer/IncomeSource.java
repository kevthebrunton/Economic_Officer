package com.firstproj.economicofficer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class IncomeSource extends AppCompatActivity {
    Button btnBack, btnAdd;

    EditText incomeType, amount;
    DatabaseHelper MyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_source);
        MyDB = new DatabaseHelper(this);

        incomeType = findViewById(R.id.income_id);
        amount = findViewById(R.id.editTextNumberAmount);

        btnBack = findViewById(R.id.btnBackToIncome1);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IncomeSource.this,Income.class);
                startActivity(intent);
            }
        });


        btnAdd = findViewById(R.id.btnDeleteIncome2);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = incomeType.getText().toString();
                String IcAmount = amount.getText().toString();

                boolean checkInput = (type.equals("") || IcAmount.equals(""));
                if (checkInput == true)
                    Toast.makeText(IncomeSource.this, "Enter the income type and amount", Toast.LENGTH_LONG).show();
                else {
                    Boolean isInserted = MyDB.insertIncomeData(incomeType.getText().toString(), amount.getText().toString());
                    if (isInserted == true) {
                        Toast.makeText(IncomeSource.this, "Data is inserted successfully", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(IncomeSource.this, "Data is NOT inserted successfully", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}