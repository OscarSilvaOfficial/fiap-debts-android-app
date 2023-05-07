package com.fiap.twinttler.Adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fiap.twinttler.Entities.Debt;
import com.fiap.twinttler.R;

import java.util.List;
import java.util.Locale;

public class DebtAdapter extends RecyclerView.Adapter<DebtAdapter.ViewHolder> {

    private List<Debt> debts;

    public DebtAdapter(List<Debt> debts) {
        this.debts = debts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_debt, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Debt debt = debts.get(position);
        holder.titleTextView.setText(debt.getTitle());
        holder.amountTextView.setText(String.format(Locale.getDefault(), "R$ %.2f", debt.getAmount()));
    }

    @Override
    public int getItemCount() {
        return debts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView amountTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.debtTitleTextView);
            amountTextView = itemView.findViewById(R.id.debtAmountTextView);
        }
    }
}
