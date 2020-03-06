package com.hm.thebudgetapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hm.thebudgetapp.DB.BudgetDAO;
import com.hm.thebudgetapp.DB.TransactionDAO;

import java.util.List;

public class ViewBudgetActivity extends Activity {

    public static final String BUDGET_ID = "id";
    public static final String CATEGORY = "category";
    public static final String NAME = "name";
    public static final String START_BALANCE = "startingBalance";

    private int id;
    private TransactionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_budget);

        populateBudgetView();
        getTransactions();

        RecyclerView recyclerView = findViewById(R.id.transaction_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        //update whatever your list
        adapter.notifyDataSetChanged();
    }

    private void getTransactions() {

        // Get transactions from DB and add to adapter.
        TransactionDAO transactionDAO = new TransactionDAO();
        List<Transacation> results = transactionDAO.getTransactions(this, id);

        Transacation[] transactions = results.toArray(new Transacation[results.size()]);
        adapter = new TransactionAdapter(transactions);

    }

    private void populateBudgetView() {
        TextView name = (TextView) findViewById(R.id.viewBudgetName);
        TextView startingBalance = (TextView) findViewById(R.id.viewStartingBalance);
        TextView category = (TextView) findViewById(R.id.viewBudgetCategory);

        // If id is passed via bundle, get Budget from DB
        id = getIntent().getIntExtra(BUDGET_ID, -1);
        Log.v("View Budget", String.valueOf(id));
        if(id >= 0) {
            BudgetDAO budgetDAO = new BudgetDAO();
            Budget budget = budgetDAO.getBudget(this, Integer.valueOf(id));

            name.setText(budget.getName());
            startingBalance.setText(String.valueOf(budget.getBalance()));
            category.setText(budget.getCategory());
        }
        else {
            // Get Budget info from bundle

            setValueFromBundle(name, NAME);
            setValueFromBundle(startingBalance, START_BALANCE);
            setValueFromBundle(category, CATEGORY);
        }

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
