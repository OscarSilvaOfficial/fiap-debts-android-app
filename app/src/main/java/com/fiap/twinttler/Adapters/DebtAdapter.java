package com.fiap.twinttler.Adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fiap.twinttler.Entities.Debt;
import com.fiap.twinttler.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class DebtAdapter extends RecyclerView.Adapter<DebtAdapter.ViewHolder> {

    private final List<Debt> debts;

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
        holder.textTitle.setText(debt.getTitle());
        holder.textAmount.setText(String.format("R$ %.2f", debt.getAmount()));

        holder.btnDelete.setOnClickListener(v -> {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("debits")
                    .document(debt.getId())
                    .delete()
                    .addOnSuccessListener(aVoid -> {
                        debts.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, debts.size());
                    });
        });
    }

    @Override
    public int getItemCount() {
        return debts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textTitle;
        private final TextView textAmount;
        private final Button btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textTitle = itemView.findViewById(R.id.debtTitleTextView);
            textAmount = itemView.findViewById(R.id.debtAmountTextView);
            btnDelete = itemView.findViewById(R.id.buttonDelete);
        }
    }
}
