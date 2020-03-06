package com.hm.thebudgetapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hm.thebudgetapp.DB.BudgetDAO;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopFragment extends Fragment {

    private BudgetDAO budgetDAO = new BudgetDAO();

    private BudgetAdapter adapter;

    public TopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_top, container, false);

        populateBudgetAdapter();
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        return recyclerView;
    }

    @Override
    public void onResume() {
        super.onResume();

        //update whatever your list
        adapter.notifyDataSetChanged();
    }

    void populateBudgetAdapter(){
        List<Budget> results = budgetDAO.getBudgets(getActivity());
        Budget[] budgets = results.toArray(new Budget[results.size()]);
        adapter = new BudgetAdapter(budgets);
    }

}
