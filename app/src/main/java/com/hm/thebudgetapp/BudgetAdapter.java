package com.hm.thebudgetapp;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BudgetAdapter extends RecyclerView.Adapter<BudgetAdapter.ViewHolder> {
    private Budget[] dataset;

    public BudgetAdapter(Budget[] dataset) {
        this.dataset = dataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Budget budget = dataset[position];
        holder.nameView.setText(budget.getName());
        holder.categoryView.setText(budget.getCategory());
        holder.balanceView.setText("$" + String.valueOf(budget.getBalance()));
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.v("BudgetAdapter", String.valueOf(budget.getId()));
                Intent intent = new Intent(view.getContext(), ViewBudgetActivity.class);
                intent.putExtra(ViewBudgetActivity.BUDGET_ID, budget.getId());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameView;
        public TextView categoryView;
        public TextView balanceView;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.nameView = (TextView) itemView.findViewById(R.id.nameView);
            this.categoryView = (TextView) itemView.findViewById(R.id.categoryView);
            this.balanceView = (TextView) itemView.findViewById(R.id.balanceView);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }

}
