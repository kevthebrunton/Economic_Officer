package com.firstproj.economicofficer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add extends AppCompatActivity {
    Button btnBack,btnAdd;
    EditText editExpense,editAmount,editDescription,editStoreName,editLocation,editDate,editTime;
    DatabaseHelper MyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        MyDB = new DatabaseHelper(this);
        editExpense = findViewById(R.id.inputExpenseType);
        editAmount = findViewById(R.id.editTextNumber);
        editDescription = findViewById(R.id.editTextTextMultiLine);
        editStoreName = findViewById(R.id.editTextTextPersonName3);
        editLocation = findViewById(R.id.editTextTextPersonName4);
        editDate = findViewById(R.id.editTextDate);
        editTime = findViewById(R.id.editTextTime);

        btnBack = findViewById(R.id.btnBackToExpense);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add.this,Dashboard.class);
                startActivity(intent);
            }
        });
        btnAdd = findViewById(R.id.btnAddExpense);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = editExpense.getText().toString();
                String amount = editAmount.getText().toString();

                boolean checkInput = (type.equals("") || amount.equals(""));
                if (checkInput == true)
                    Toast.makeText(Add.this, "Enter the Expense type and amount", Toast.LENGTH_LONG).show();
                else {
                    boolean isInserted = MyDB.insertAddData(editExpense.getText().toString(), editAmount.getText().toString(), editDescription.getText().toString(),editStoreName.getText().toString(),editLocation.getText().toString(),editDate.getText().toString(),editTime.getText().toString());
                    if (isInserted == true) {
                        Toast.makeText(Add.this, "Data is inserted successfully", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Add.this, "Data is NOT inserted successfully", Toast.LENGTH_LONG).show();
                    }
                }
            }


        });
    }
}