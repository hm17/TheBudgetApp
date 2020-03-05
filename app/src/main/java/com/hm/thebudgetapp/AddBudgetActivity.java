package com.hm.thebudgetapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hm.thebudgetapp.DB.BudgetDAO;

public class AddBudgetActivity extends Activity {

    private BudgetDAO budgetDAO = new BudgetDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_budget);
    }

    /**
     * onClick method for creating a new Budget
     *
     * @param view
     */
    public void saveBudget(View view) {
        TextView startingBalance = (TextView) findViewById(R.id.startingBalance);
        TextView name = (TextView) findViewById(R.id.budgetName);
        TextView category = (TextView) findViewById(R.id.budgetCategory);

        String nameVal = name.getText().toString();
        String startVal = startingBalance.getText().toString();
        String catVal = category.getText().toString();

        Budget budget = new Budget(nameVal, catVal, Double.valueOf(startVal));
        budgetDAO.saveBudget(this, budget);

        Log.v("Add Budget",  startVal + nameVal + catVal);

        // Save values to Bundle and pass to ViewBudget
        Intent intent = new Intent(this, ViewBudgetActivity.class);
        intent.putExtra(ViewBudgetActivity.START_BALANCE, startVal);
        intent.putExtra(ViewBudgetActivity.NAME, nameVal);
        intent.putExtra(ViewBudgetActivity.CATEGORY, catVal);
        startActivity(intent);
    }
}
