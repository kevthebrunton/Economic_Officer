package com.firstproj.economicofficer;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DBNAME = "UserData.db";

    private static final String Table_Users = "users";
    private static final String Col_user_id = "user_id";
    private static final String Col_name = "name";
    private static final String Col_email = "email";
    private static final String Col_password = "password";


    private static final String Table_ExpenseDetails = "ExpenseDetails";

    private static final String Col_expenseId = "expense_id";
    private static final String Col_type =  "EXPENSE_TYPE";
    private static final String Col_amount =  "AMOUNT";
    private static final String Col_description =  "DESCRIPTION";
    private static final String Col_storeName =  "STORE_NAME";

    private static final String Col_location =  "LOCATION";

    private static final String Col_date =  "DATE";

    private static final String Col_time =  "TIME";



    private static final String Table_IncomeDetails = "IncomeDetails";
    private static final String Col_incomeId = "incomeid";
    private static final String Col_incomeType = "type";
    private static final String Col_incomeAmount = "incomeAmount";


    DatabaseHelper(@Nullable Context context) {

        super(context, DBNAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        String createUsersTable = "CREATE TABLE " + Table_Users +" (" +
                Col_user_id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Col_name + " TEXT NOT NULL, " +
                Col_email + " TEXT NOT NULL," +
                Col_password + " TEXT NOT NULL)";

        String createExpenseDetailsTable = " CREATE TABLE " + Table_ExpenseDetails + "("+
                Col_expenseId + " INTEGER PRIMARY KEY ,"+
                Col_type + " TEXT, " +
                Col_amount + " INTEGER," +
                Col_description + " TEXT," +
                Col_storeName + " TEXT," +
                Col_location + " TEXT," +
                Col_date + " TEXT," +
                Col_time + " TEXT," +
                "FOREIGN KEY("+ Col_expenseId +") REFERENCES " + Table_Users + "(" + Col_user_id +"))";

        String createIncomeDetailsTable = " CREATE TABLE " + Table_IncomeDetails + "("+
                Col_incomeId + " INTEGER PRIMARY KEY , " +
                Col_incomeType + " TEXT, " +
                Col_incomeAmount + " INTEGER, " +
                "FOREIGN KEY("+ Col_incomeId +") REFERENCES " + Table_Users + "(" + Col_user_id +"))";
        MyDB.execSQL(createUsersTable);
        MyDB.execSQL(createExpenseDetailsTable);
        MyDB.execSQL(createIncomeDetailsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("DROP TABLE IF EXISTS " + Table_Users);
        MyDB.execSQL("DROP TABLE IF EXISTS " + Table_ExpenseDetails);
        MyDB.execSQL("DROP TABLE IF EXISTS " + Table_IncomeDetails);

        onCreate(MyDB);
    }

    public Boolean insertData(String name,String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(Col_name,name);
        contentValues.put(Col_email, email);
        contentValues.put(Col_password, password);
        long result = MyDB.insert(Table_Users, null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public boolean insertAddData(String expense_type, String amount, String description, String store_name, String location, String date, String time) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_type,expense_type);
        contentValues.put(Col_amount,amount);
        contentValues.put(Col_description,description);
        contentValues.put(Col_storeName,store_name);
        contentValues.put(Col_location,location);
        contentValues.put(Col_date,date);
        contentValues.put(Col_time,time);

        long result = MyDB.insert(Table_ExpenseDetails,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;


    }

    public boolean insertIncomeData(String income_type, String income_amount){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_incomeType,income_type);
        contentValues.put(Col_incomeAmount, income_amount);

        long result = MyDB.insert(Table_IncomeDetails, null, contentValues);

        if (result ==-1)
            return false;
        else
            return true;
    }



    public Boolean checkemail(String email) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email = ?", new String[]{email});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email = ? and password = ?", new String[] {email,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }


    Cursor readAllData(){
        String query = " SELECT * FROM " + Table_ExpenseDetails;
        SQLiteDatabase MyDB = this.getWritableDatabase();

        Cursor cursor = null;
        if (MyDB != null){
            cursor = MyDB.rawQuery(query, null);
        }
        return cursor;

    }

    public void updateData(String id,String type,String date, String storeName, String amount) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Col_type, type);
        cv.put(Col_date, date);
        cv.put(Col_storeName, storeName);
        cv.put(Col_amount, amount);


        long result = MyDB.update(Table_ExpenseDetails, cv, "expense_id = ?", new String[]{id});

        if (result == -1) {
            Toast.makeText(context, "Failed to update ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully updated ", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteOneRow(String id){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        long result = MyDB.delete(Table_ExpenseDetails, "expense_id = ?", new String[]{id});
        if (result == -1){
            Toast.makeText(context, "Failed to Delete ", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Deleted Successfully! ", Toast.LENGTH_SHORT).show();
        }
    }
    public Cursor getAllData(){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor results = MyDB.rawQuery("SELECT * FROM " + Table_ExpenseDetails  ,null);

        return results;


    }

    public Cursor getAllIncomeData(){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor results = MyDB.rawQuery("SELECT * FROM " + Table_IncomeDetails  ,null);

        return results;


    }
    public void deleteIncomeRow(String id){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        long result = MyDB.delete(Table_IncomeDetails, "incomeid = ?", new String[]{id});
        if (result == -1){
            Toast.makeText(context, "Failed to Delete ", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Deleted Successfully! ", Toast.LENGTH_SHORT).show();
        }
    }


}

