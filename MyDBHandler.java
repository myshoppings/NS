package com.example.sultan.myshoppings;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "myshopping.db";
    public static final String TABLE_GROCERY = "grocery";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";


    //We need to pass database information along to superclass
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_GROCERY + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT " +
                ");";
        db.execSQL(query);
    }

    
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROCERY);
        onCreate(db);
    }
    
    
    //Add a new row to the database
    public void addGrocery(Grocery grocery){

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, grocery.get_groceryname());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_GROCERY, null, values);
        db.close();
    }

    
    //Delete a user from the database
    public void deleteGrocery(Grocery grocery) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_GROCERY + " WHERE " + COLUMN_NAME + " = '"+ grocery.get_groceryname() +"';");

    }
    
    
      //print out the database as string
    public ArrayList<String> databaseToString(){
        String dbString = "";
        ArrayList<String> listOfTotalGroceries = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_GROCERY + " WHERE 1";
       // String query = "SELECT * FROM " + TABLE_NAME;

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();
        //Position after the last row means the end of the results
      
          while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("name")) != null) {
                dbString += c.getString(c.getColumnIndex("name"));
                listOfTotalGroceries.add(dbString);
                dbString = "\n";
            }
            c.moveToNext();
        }
        db.close();
        return listOfTotalGroceries;
        
    }
    
}
