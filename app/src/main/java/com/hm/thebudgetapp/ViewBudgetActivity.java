package com.hm.thebudgetapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ViewBudgetActivity extends Activity {

    public static final String CATEGORY = "category";
    public static final String NAME = "name";
    public static final String START_BALANCE = "startingBalance";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_budget);


        Transacation[] transacations = {new Transacation("711", "gas", 12.99, 1), new Transacation("711", "gas", 12.99, 1),
                new Transacation("711", "gas", 12.99, 1), new Transacation("711", "gas", 12.99, 1),
                new Transacation("711", "gas", 12.99, 1), new Transacation("711", "gas", 12.99, 1),
                new Transacation("711", "gas", 12.99, 1),
                new Transacation("711", "gas", 12.99, 1),new Transacation("711", "gas", 12.99, 1)};



        RecyclerView recyclerView = findViewById(R.id.transaction_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TransactionAdapter adapter = new TransactionAdapter(transacations);
        recyclerView.setAdapter(adapter);

        populateBudgetView();
    }

    private void populateBudgetView() {
        TextView name = (TextView) findViewById(R.id.viewBudgetName);
        setValueFromBundle(name, NAME);

        TextView startingBalance = (TextView) findViewById(R.id.viewStartingBalance);
        setValueFromBundle(startingBalance, START_BALANCE);


        TextView category = (TextView) findViewById(R.id.viewBudgetCategory);
        setValueFromBundle(category, CATEGORY);
    }

    private void setValueFromBundle(TextView view, String key) {

        String text = "TEST";

        // Get value from Bundle
        String value = getIntent().getStringExtra(key);
        if(value != null) {
            text = value;
        }

        // Set it on view
        view.setText(text);
    }


    public void addTransaction(View view) {
        Intent intent = new Intent(this, AddTransactionActivity.class);
        startActivity(intent);
    }
}
