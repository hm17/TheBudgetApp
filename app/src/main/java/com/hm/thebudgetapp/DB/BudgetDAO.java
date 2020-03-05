package com.hm.thebudgetapp.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.hm.thebudgetapp.Budget;

import java.util.ArrayList;
import java.util.List;

public class BudgetDAO {

    private SQLiteOpenHelper budgetDatabaseHelper;
    private SQLiteDatabase db;

    public BudgetDAO(){

    }

    public long saveBudget(Context context, Budget budget) {
        budgetDatabaseHelper = new BudgetDatabaseHelper(context);

        try {
            db = budgetDatabaseHelper.getWritableDatabase();

            ContentValues budgetValues = new ContentValues();
            budgetValues.put("NAME", budget.getName());
            budgetValues.put("CATEGORY", budget.getCategory());
            budgetValues.put("BALANCE", budget.getBalance());
            return db.insert("BUDGET", null, budgetValues);
        } catch(SQLiteException e) {
            Log.v("TopFragment", "error: " + e.getMessage());
            Toast toast = Toast.makeText(context, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        return -1;

    }

    public Budget getBudget(Context context, int id)  {
        Budget budget = new Budget();

        budgetDatabaseHelper = new BudgetDatabaseHelper(context);
        try {
            db = budgetDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("BUDGET",
                    new String[] {"_id", "NAME", "CATEGORY", "BALANCE"},
                    "_id = ?",
                    new String[] {String.valueOf(id)}, null, null, null);
            Log.v("TopFragment", "cursor: " + cursor.getCount());

            // Move to first record in the Cursor
            if (cursor.moveToFirst()) {
                    Log.v("TopFragment", "cursor: " + cursor.getColumnName(0));
                    Log.v("TopFragment", "cursor: " + cursor.toString());

                    // Get details from cursor
                    String nameText = cursor.getString(1);
                    Log.v("TopFragment", "nameText: " + nameText);
                    String categoryText = cursor.getString(2);
                    Log.v("TopFragment", "categoryText: " + categoryText);
                    Double balanceValue = Double.valueOf(cursor.getString(3));
                    Log.v("TopFragment", "balanceValue: " + balanceValue);
                    budget = new Budget(nameText, categoryText, balanceValue);
            }
            cursor.close();
            db.close();
        } catch(SQLiteException e) {
            Log.v("TopFragment", "error: " + e.getMessage());
            Toast toast = Toast.makeText(context, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        return budget;
    }

    public List<Budget> getBudgets(Context context) {
        List<Budget> budgets = new ArrayList<Budget>();

        budgetDatabaseHelper = new BudgetDatabaseHelper(context);
        try {
            db = budgetDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("BUDGET",
                    new String[] {"_id", "NAME", "CATEGORY", "BALANCE"},
                    null, null, null, null, null);
            Log.v("TopFragment", "cursor: " + cursor.getCount());

            // Move to first record in the Cursor
            if (cursor.moveToFirst()) {
                for(int i=0; i<cursor.getCount(); i++) {
                    // Get details from cursor
                    String nameText = cursor.getString(1);
                    String categoryText = cursor.getString(2);
                    Double balanceValue = Double.valueOf(cursor.getString(3));
                    int id = Integer.valueOf(cursor.getString(0));
                    Budget budget = new Budget(nameText, categoryText, balanceValue);
                    budget.setId(id);
                    budgets.add(budget);

                    cursor.moveToNext();
                }

            }
            cursor.close();
            db.close();
        } catch(SQLiteException e) {
            Log.v("TopFragment", "error: " + e.getMessage());
            Toast toast = Toast.makeText(context, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        return budgets;
    }
}
