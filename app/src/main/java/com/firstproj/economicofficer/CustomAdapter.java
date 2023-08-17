package com.firstproj.economicofficer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList expense_id,expense_type,expense_date,expense_storeName,expense_amount;

    CustomAdapter(Activity activity,Context context,ArrayList expense_id,ArrayList expense_type, ArrayList expense_date, ArrayList expense_storeName,
                  ArrayList expense_amount){
        this.context = context;
        this.activity = activity;
        this.expense_id = expense_id;
        this.expense_type = expense_type;
        this.expense_date = expense_date;
        this.expense_storeName = expense_storeName;
        this.expense_amount = expense_amount;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.display_expense, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.expense_id_text.setText(String.valueOf(expense_id.get(position)));
        holder.expense_type_text.setText(String.valueOf(expense_type.get(position)));
        holder.expense_date_text.setText(String.valueOf(expense_date.get(position)));
        holder.expense_store_text.setText(String.valueOf(expense_storeName.get(position)));
        holder.expense_amount_text.setText(String.valueOf(expense_amount.get(position)));

        //Recyclerview onClicklistener
       holder.main_Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Update.class);
                intent.putExtra("id", String.valueOf(expense_id.get(position)));
                intent.putExtra("type", String.valueOf(expense_type.get(position)));
                intent.putExtra("date", String.valueOf(expense_date.get(position)));
                intent.putExtra("storeName", String.valueOf(expense_storeName.get(position)));
                intent.putExtra("amount", String.valueOf(expense_amount.get(position)));

                activity.startActivityForResult(intent,1);

            }
        });




    }

    @Override
    public int getItemCount() {

        return expense_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView expense_id_text,expense_type_text,expense_date_text,expense_store_text,expense_amount_text;
        LinearLayout main_Layout;
        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            expense_id_text = itemView.findViewById(R.id.display_expense_id1);
            expense_type_text = itemView.findViewById(R.id.display_expense_type1);
            expense_date_text = itemView.findViewById(R.id.display_expense_date1);
            expense_store_text = itemView.findViewById(R.id.display_expense_store1);
            expense_amount_text = itemView.findViewById(R.id.display_expense_amount1);
            main_Layout = itemView.findViewById(R.id.mainLayout);

        }
    }
}
