package com.hm.thebudgetapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


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

        Budget[] budgets = {new Budget("FOOD", "food", 0), new Budget("SHOPPING", "misc", 65.30),
                new Budget("FOOD", "food", 0), new Budget("SHOPPING", "misc", 65.30),
                new Budget("FOOD", "food", 0), new Budget("SHOPPING", "misc", 65.30),
                new Budget("FOOD", "food", 0), new Budget("SHOPPING", "misc", 65.30)};

        BudgetAdapter adapter = new BudgetAdapter(budgets);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        return recyclerView;

    }


}
