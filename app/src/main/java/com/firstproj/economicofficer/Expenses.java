package com.firstproj.economicofficer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Expenses extends AppCompatActivity {
    Button btnBack;
    TextView btnViewAllData;
    DatabaseHelper MyDB;
    RecyclerView recyclerView;
    ArrayList<String> expense_id,expense_type,expense_date,expense_storeName,expense_amount;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);


        recyclerView = findViewById(R.id.recyclerView);

        btnBack = findViewById(R.id.btnBackToDashBoard2);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Expenses.this, Dashboard.class);
                startActivity(intent);
            }
        });

        btnViewAllData = findViewById(R.id.btnViewAllData);
        btnViewAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor results = MyDB.getAllData();

                if (results.getCount() == 0) {
                    showMessage("Error message", "No data to show");
                    return;
                }else {
                    StringBuffer buffer = new StringBuffer();
                    while (results.moveToNext()) {
                        buffer.append("ID:" + results.getString(0) + "\n");
                        buffer.append("Expense Type:" + results.getString(1) + "\n");
                        buffer.append("Amount:" + results.getString(2) + "\n");
                        buffer.append("Description:" + results.getString(3) + "\n");
                        buffer.append("Store Name:" + results.getString(4) + "\n");
                        buffer.append("Location:" + results.getString(5) + "\n");
                        buffer.append("Time:" + results.getString(6) + "\n");
                        buffer.append("Date:" + results.getString(7) + "\n\n");
                    }
                    showMessage("Expense List", buffer.toString());

                }

            }
        });


        MyDB = new DatabaseHelper(Expenses.this);
        expense_id = new ArrayList<>();
        expense_type = new ArrayList<>();
        expense_date = new ArrayList<>();
        expense_storeName = new ArrayList<>();
        expense_amount = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(Expenses.this,this,expense_id, expense_type, expense_date, expense_storeName, expense_amount);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Expenses.this));


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = MyDB.readAllData();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data to show.",Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                expense_id.add(cursor.getString(0));
                expense_type.add(cursor.getString(1));
                expense_date.add(cursor.getString(7));
                expense_storeName.add(cursor.getString(4));
                expense_amount.add(cursor.getString(2));

            }

            }
    }

    public void showMessage (String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}

/***
        public void showMessage (String title, String message){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle(title);
            builder.setMessage(message);
            builder.show();
        }***/


/***
 Cursor results = MyDB.getAllData();

 if (results.getCount() == 0) {
 showMessage("Error message", "No data to show");
 return;
 }else {
 StringBuffer buffer = new StringBuffer();
 while (results.moveToNext()) {
 buffer.append("ID:" + results.getString(0) + "\n");
 buffer.append("Expense Type:" + results.getString(1) + "\n");
 buffer.append("Amount:" + results.getString(2) + "\n");
 buffer.append("Description:" + results.getString(3) + "\n");
 buffer.append("Store Name:" + results.getString(4) + "\n");
 buffer.append("Location:" + results.getString(5) + "\n");
 buffer.append("Time:" + results.getString(6) + "\n");
 buffer.append("Date:" + results.getString(7) + "\n\n");
 }
 showMessage("List of Data", buffer.toString());

 }***/



