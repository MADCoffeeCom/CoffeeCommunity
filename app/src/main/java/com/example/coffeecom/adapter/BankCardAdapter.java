package com.example.coffeecom.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.activity.BottomNavigationActivity;
import com.example.coffeecom.fragment.AddBankCardFragment;
import com.example.coffeecom.model.BankCardModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class BankCardAdapter extends RecyclerView.Adapter<BankCardAdapter.ViewHolder>{

    ArrayList<BankCardModel> bankcard;
    Context activity;

    public BankCardAdapter(ArrayList<BankCardModel> bankcard, Context activity) {
        this.bankcard = bankcard;
        this.activity = activity;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bankNameText;
        ConstraintLayout bankCardCard;
        ImageButton deleteBankCardBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bankNameText = itemView.findViewById(R.id.bankCardText);
            bankCardCard = itemView.findViewById(R.id.bankCardCard);
            deleteBankCardBtn = itemView.findViewById(R.id.deleteBankCardBtn);
        }
    }

    @NonNull
    @Override
    public BankCardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_bank_card, parent, false);
        return new BankCardAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull BankCardAdapter.ViewHolder holder, int position) {
        holder.bankNameText.setText(Provider.getUser().getBankCard().get(position).getBankName());
        holder.bankCardCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("cardNo", Provider.getUser().getBankCard().get(position).getBankCardNo());
                ((BottomNavigationActivity) activity).replaceFragmentWithData(new AddBankCardFragment(), bundle);
            }
        });

        holder.deleteBankCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount(){
        try {
            return Provider.getUser().getBankCard().size();

        }catch(NullPointerException e) {
            return 0;
        }
    }

}
