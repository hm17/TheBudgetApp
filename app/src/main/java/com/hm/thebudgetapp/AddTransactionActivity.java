package com.hm.thebudgetapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.hm.thebudgetapp.DB.BudgetDAO;
import com.hm.thebudgetapp.DB.TransactionDAO;

import java.util.Calendar;
import java.util.Date;

public class AddTransactionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
    }

    public void saveTransaction(View view) {

        TextView amount = (TextView) findViewById(R.id.amount);
        TextView vendor = (TextView) findViewById(R.id.vendor);
        TextView category = (TextView) findViewById(R.id.category);
        TextView description = (TextView) findViewById(R.id.description);

        DatePicker datePicker = (DatePicker) findViewById(R.id.transactionDate);
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1;
        int year = datePicker.getYear();

        String amountVal = amount.getText().toString();
        String vendorVal = vendor.getText().toString();
        String categoryVal = category.getText().toString();
        String descriptionVal = description.getText().toString();

        // Get budget ID from bundle
        int budgetID = getIntent().getIntExtra(ViewBudgetActivity.BUDGET_ID, -1);

        Transacation transacation = new Transacation(vendorVal, descriptionVal, Double.valueOf(amountVal), budgetID);
        transacation.setCategory(categoryVal);

        // set Date on Transaction
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        Date date = calendar.getTime();
        transacation.setDate(date);

        // Save to DB
        TransactionDAO transactionDAO = new TransactionDAO();
        long resultId = transactionDAO.saveTransaction(this, transacation);
        int id = (int) resultId;

        // Update Balance and save to DB
        Double startBalance = getIntent().getDoubleExtra(ViewBudgetActivity.START_BALANCE, 0);
        Double newBalance = startBalance - transacation.getAmount();
        BudgetDAO budgetDAO = new BudgetDAO();
        budgetDAO.updateBudget(this, budgetID, newBalance);


        Intent intent = new Intent(this, ViewBudgetActivity.class);
        intent.putExtra(ViewBudgetActivity.BUDGET_ID, budgetID);
        startActivity(intent);

    }
}
