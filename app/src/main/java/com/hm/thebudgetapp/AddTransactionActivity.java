package com.hm.thebudgetapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class AddTransactionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
    }

    public void saveTransaction(View view) {

        /*
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
        */
    }
}
