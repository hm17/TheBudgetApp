package com.hm.thebudgetapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ViewBudgetActivity extends Activity {

    public static final String CATEGORY = "category";
    public static final String NAME = "name";
    public static final String START_BALANCE = "startingBalance";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_budget);

        populateBudgetView();
    }

    private void populateBudgetView() {
        TextView category = (TextView) findViewById(R.id.viewBudgetCategory);

        String catVal = getIntent().getStringExtra(CATEGORY);
        if(catVal != null) {
            Log.v("ViewBudget", catVal);
            category.setText(catVal);
        }

    }
}
