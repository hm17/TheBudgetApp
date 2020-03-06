package com.hm.thebudgetapp.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.hm.thebudgetapp.Transacation;

import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    private SQLiteOpenHelper budgetDatabaseHelper;
    private SQLiteDatabase db;

    public TransactionDAO() {}

    public List<Transacation> getTransactions(Context context, int budgetId) {
        List<Transacation> transacations = new ArrayList<Transacation>();

        budgetDatabaseHelper = new BudgetDatabaseHelper(context);
        try {
            db = budgetDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("BUDGET_TRANSACTION",
                    new String[] {"_id", "budgetId", "VENDOR", "DESCRIPTION", "AMOUNT"},
                    "budgetId = ?", new String[] {String.valueOf(budgetId)}, null, null, null);
            Log.v("TransactionDAO", "budgetId: " + String.valueOf(budgetId));
            Log.v("TransactionDAO", "cursor: " + cursor.getCount());

            // Move to first record in the Cursor
            if (cursor.moveToFirst()) {
                for(int i=0; i<cursor.getCount(); i++) {
                    // Get details from cursor
                    String vendor = cursor.getString(2);
                    String description = cursor.getString(3);
                    Double amount = Double.valueOf(cursor.getString(4));
                    int id = Integer.valueOf(cursor.getString(0));

                    Log.v("TransactionDAO", description + " " + amount);
                    Transacation transacation = new Transacation(vendor, description, amount, budgetId);
                    transacation.setBudgetId(budgetId);
                    transacation.setId(id);
                    transacations.add(transacation);

                    cursor.moveToNext();
                }

            }
            cursor.close();
            db.close();
        } catch(SQLiteException e) {
            Log.v("TransactionDAO", "error: " + e.getMessage());
            Toast toast = Toast.makeText(context, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        return transacations;
    }
}
