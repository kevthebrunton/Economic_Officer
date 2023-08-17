package com.firstproj.economicofficer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Income extends AppCompatActivity {
    Button btnBack,btnAdd,btnDelete;
    TextView btnViewIncome;
    DatabaseHelper MyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        btnBack = findViewById(R.id.btnBackToDashBoard);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Income.this,Dashboard.class);
                startActivity(intent);
            }
        });
        MyDB = new DatabaseHelper(Income.this);

        btnViewIncome = findViewById(R.id.btnIncomeView1);
        btnViewIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor results = MyDB.getAllIncomeData();

                if (results.getCount() == 0) {
                    showMessage("Error message", "No data to show");
                    return;
                }else {
                    StringBuffer buffer = new StringBuffer();
                    while (results.moveToNext()) {
                        buffer.append("ID:" + results.getString(0) + "\n");
                        buffer.append("Income Type:" + results.getString(1) + "\n");
                        buffer.append("Amount:" + results.getString(2) + "\n");

                    }
                    showMessage("Income ", buffer.toString());

                }

            }
        });

        btnAdd = findViewById(R.id.btnAddIncomeSource);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Income.this,IncomeSource.class);
                startActivity(intent);
            }
        });

        btnDelete = findViewById(R.id.buttonDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Income.this,delete_income.class);
                startActivity(intent);
            }
        });
    }

    public void showMessage (String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}