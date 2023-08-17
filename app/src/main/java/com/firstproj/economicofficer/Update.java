package com.firstproj.economicofficer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update extends AppCompatActivity {

    EditText expense_input,date_input,store_input,amount_input;
    Button update_button, delete_button,btnBack;
    String id, type, date, storeName,amount;
    DatabaseHelper MyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        expense_input = findViewById(R.id.inputExpenseType2);
        date_input = findViewById(R.id.editTextDate2);
        store_input = findViewById(R.id.editTextTextPersonName32);
        amount_input = findViewById(R.id.editTextNumber2);

        update_button = findViewById(R.id.btnUpdateExpense);
        delete_button = findViewById(R.id.btnDeleteExpense);
        getAndSetIntentData();
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDB = new DatabaseHelper(Update.this);
                type = expense_input.getText().toString().trim();
                date = date_input.getText().toString().trim();
                storeName = store_input.getText().toString().trim();
                amount = amount_input.getText().toString().trim();
                MyDB.updateData(id, type, date, storeName, amount);

            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();

            }
        });

        btnBack = findViewById(R.id.btnBackToExpense);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Update.this,Expenses.class);
                startActivity(intent);
            }
        });



    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("type") &&
                getIntent().hasExtra("date") && getIntent().hasExtra("storeName") &&
                getIntent().hasExtra("amount")){
            //Getting data from intent
            id = getIntent().getStringExtra("id");
            type = getIntent().getStringExtra("type");
            date = getIntent().getStringExtra("date");
            storeName = getIntent().getStringExtra("storeName");
            amount = getIntent().getStringExtra("amount");


            //Setting Intent data
            expense_input.setText(type);
            date_input.setText(date);
            store_input.setText(storeName);
            amount_input.setText(amount);


        }else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }



    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Update.this);
        builder.setTitle("Delete" + type + " ? ");
        builder.setMessage("Are you sure you want to delete " + type + " ? ");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDB = new DatabaseHelper(Update.this);
                MyDB.deleteOneRow(id);
                finish();
            }
        });

        builder.setNegativeButton(" No ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}