package com.hm.thebudgetapp;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hm.thebudgetapp.DB.BudgetDatabaseHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopFragment extends Fragment {

    public TopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_top, container, false);

        Budget testBudget = new Budget("", "", 0);

        // Create a cursor
        SQLiteOpenHelper budgetDatabaseHelper = new BudgetDatabaseHelper(getActivity());
        try {
            SQLiteDatabase db = budgetDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("BUDGET",
                                        new String[] {"_id", "NAME", "CATEGORY", "BALANCE"},
                                        null, null, null, null, null);
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
                testBudget = new Budget(nameText, categoryText, balanceValue);
            }
            cursor.close();
            db.close();
        } catch(SQLiteException e) {
            Log.v("TopFragment", "error: " + e.getMessage());
            Toast toast = Toast.makeText(getActivity(), "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }


        Budget[] budgets = {new Budget("FOOD", "food", 0), new Budget("SHOPPING", "misc", 65.30),
                new Budget("FOOD", "food", 0), new Budget("SHOPPING", "misc", 65.30),
                new Budget("FOOD", "food", 0), new Budget("SHOPPING", "misc", 65.30),
                new Budget("FOOD", "food", 0), new Budget("SHOPPING", "misc", 65.30),
                testBudget};

        BudgetAdapter adapter = new BudgetAdapter(budgets);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        return recyclerView;

    }


}
