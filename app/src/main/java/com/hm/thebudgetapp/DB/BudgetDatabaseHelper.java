package com.hm.thebudgetapp.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BudgetDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "budgetapp";
    private static final int DB_VERSION = 5;

    public BudgetDatabaseHelper (Context context) {

        super(context, DB_NAME, null, DB_VERSION);
        //super(context, null, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateDB(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateDB(db, oldVersion, newVersion);
    }

    private void updateDB(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion < 1) {
            db.execSQL("CREATE TABLE BUDGET ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "CATEGORY TEXT, "
                    + "BALANCE TEXT);");

        }

        if(oldVersion <2) {
            db.execSQL("CREATE TABLE BUDGET_TRANSACTION ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "budgetId INTEGER, "
                    + "VENDOR TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "DATE TEXT, "
                    + "CATEGORY TEXT, "
                    + "AMOUNT TEXT);");
        }

        if(oldVersion <4) {
            insertBudget(db, "Food", "food", String.valueOf(150.00));
            insertBudget(db, "Inventory", "merch", String.valueOf(12));
            insertBudget(db, "Gas", "misc", String.valueOf(0));
            insertTransaction(db, 1, "Wawa", "gas", String.valueOf(47.88));
        }
        /*
        if(oldVersion <5) {
            db.execSQL("ALTER TABLE BUDGET_TRANSACTION "
                    + "ADD ;");

            db.execSQL("ALTER TABLE BUDGET_TRANSACTION "
                    + "ADD CATEGORY TEXT;");
        }*/

        if(oldVersion <6) {
            db.execSQL("DELETE FROM BUDGET;");
            db.execSQL("DELETE FROM BUDGET_TRANSACTION");
        }

    }

    private static void insertBudget(SQLiteDatabase db, String name, String category, String balance) {
        ContentValues budgetValues = new ContentValues();
        budgetValues.put("NAME", name);
        budgetValues.put("CATEGORY", category);
        budgetValues.put("BALANCE", balance);
        db.insert("BUDGET", null, budgetValues);
    }

    private static void insertTransaction(SQLiteDatabase db, int budgetId, String vendor, String description, String amount) {
        ContentValues transactionValues = new ContentValues();
        transactionValues.put("budgetId", budgetId);
        transactionValues.put("VENDOR", vendor);
        transactionValues.put("DESCRIPTION", description);
        transactionValues.put("AMOUNT", amount);
        db.insert("BUDGET_TRANSACTION", null, transactionValues);
    }
}
