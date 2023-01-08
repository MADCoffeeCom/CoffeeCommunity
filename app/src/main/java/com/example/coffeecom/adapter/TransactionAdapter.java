package com.example.coffeecom.adapter;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.model.TransactionModel;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder>{

    ArrayList<TransactionModel> transactions;

    public TransactionAdapter(ArrayList<TransactionModel> transactions) {
        this.transactions = transactions;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView transactionDetailsText, amountTransactText;
        ImageView transactionIconImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            transactionDetailsText = itemView.findViewById(R.id.transactionDetailsText);
            amountTransactText = itemView.findViewById(R.id.amountTransactText);
            transactionIconImage = itemView.findViewById(R.id.transactionIconImage);
        }
    }

    @NonNull
    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_transaction, parent, false);
        return new TransactionAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.ViewHolder holder, int position) {
        holder.transactionDetailsText.setText(transactions.get(position).getPaymentType());

        String picUrl = "ic_debit";
        double amount = transactions.get(position).getTotalPayment();
        String signedAmount = "";

        if(transactions.get(position).getReceiverId().equals(Provider.getUser().getUserId())){
            picUrl = "ic_credit";
            signedAmount = "+RM" + String.format("%.2f", amount);
        }else{
            signedAmount = "-RM" + String.format("%.2f", amount);
        }

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.transactionIconImage);
        holder.amountTransactText.setText(signedAmount);

    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }


}
