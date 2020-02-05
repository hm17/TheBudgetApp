package com.hm.thebudgetapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createNewBudget(View view) {
        Intent intent = new Intent(this, AddBudgetActivity.class);
        startActivity(intent);
    }

    public void launchBudget(View view) {
        Toast msg = Toast.makeText(this, "YO!", Toast.LENGTH_SHORT);
        msg.show();

        Intent intent = new Intent(this, ViewBudgetActivity.class);
        startActivity(intent);
    }
}
