package com.example.coffeecom.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.fragment.AddBankCardFragment;

public class BankCardAdapter extends RecyclerView.Adapter<BankCardAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bankNameText;
        ConstraintLayout bankCardCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bankNameText = itemView.findViewById(R.id.bankCardText);
            bankCardCard = itemView.findViewById(R.id.bankCardCard);
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
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                AddBankCardFragment addBankCard = new AddBankCardFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.containerMainPage,addBankCard).addToBackStack(null).commit();
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
