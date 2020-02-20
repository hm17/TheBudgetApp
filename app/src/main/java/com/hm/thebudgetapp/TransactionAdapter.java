package com.hm.thebudgetapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    private Transacation[] dataset;

    public TransactionAdapter(Transacation[] dataset) {
        this.dataset = dataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.transaction_item, parent, false);
        TransactionAdapter.ViewHolder viewHolder = new TransactionAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Transacation myListData = dataset[position];

        holder.vendorView.setText(dataset[position].getVendor());
        holder.descriptionView.setText(dataset[position].getDescription());
        holder.amountView.setText("$" + String.valueOf(dataset[position].getAmount()));
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(view.getContext(), ViewBudgetActivity.class);
                //intent.putExtra(PizzaDetailActivity.EXTRA_PIZZA_ID, position);
                //view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView vendorView;
        public TextView descriptionView;
        public TextView amountView;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.vendorView = (TextView) itemView.findViewById(R.id.vendorView);
            this.descriptionView = (TextView) itemView.findViewById(R.id.descriptionView);
            this.amountView = (TextView) itemView.findViewById(R.id.amountView);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }
}
