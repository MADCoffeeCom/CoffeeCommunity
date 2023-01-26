package com.example.coffeecom.fragment;

import static com.example.coffeecom.helper.ToTitleCase.toTitleCase;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.adapter.BaristaCardAdapter;
import com.example.coffeecom.adapter.CoffeeTypeAdapter;
import com.example.coffeecom.model.BankCardModel;
import com.example.coffeecom.model.BaristaModel;
import com.example.coffeecom.model.CoffeeModel;
import com.example.coffeecom.query.QueryBankCard;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;

import java.util.ArrayList;


public class AddBankCardFragment extends Fragment {

    TextView addBankCardTitleText;
    TextView cardNumberTextBox, nameOnCardTextBox, ccvTextBox, expiryDateTextBox, bankNameText;
    Button addCardBtn;
    ImageButton backBtn;
    private boolean isEdit; // e for edit, a for add

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_add_bank_card,container,false);
        addCardBtn = view.findViewById(R.id.addCardBtn);
        addBankCardTitleText = view.findViewById(R.id.addBankCardTitleText);
        backBtn = view.findViewById(R.id.backBtn);

        cardNumberTextBox = view.findViewById(R.id.cardNumberTextBox);
        nameOnCardTextBox = view.findViewById(R.id.nameOnCardTextBox);
        ccvTextBox = view.findViewById(R.id.ccvTextBox);
        expiryDateTextBox = view.findViewById(R.id.expiryDateTextBox);
        bankNameText = view.findViewById(R.id.bankNameText);

        Bundle bundle = this.getArguments();
        isEdit = bundle != null ? true : false;

        if(isEdit){
            addBankCardTitleText.setText("Edit Card");
            addCardBtn.setText("Edit Card");
            cardNumberTextBox.setEnabled(false);
            String cardNo = bundle.getString("cardNo");
            for (int i = 0; i < Provider.getUser().getBankCard().size(); i++) {
                if(Provider.getUser().getBankCard().get(i).getBankCardNo().equals(cardNo)){
                    BankCardModel currentCard = Provider.getUser().getBankCard().get(i);
                    cardNumberTextBox.setText(currentCard.getBankCardNo());
                    nameOnCardTextBox.setText(currentCard.getBankHolderName());
                    ccvTextBox.setText("" + currentCard.getCardCvv());
                    expiryDateTextBox.setText("" + currentCard.getCardExpiryDate());
                    bankNameText.setText(currentCard.getBankName());
                    break;
                }
            }

            addCardBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String cardNo = String.valueOf(cardNumberTextBox.getText());
                    String cardName = String.valueOf(nameOnCardTextBox.getText());
                    String ccv = "" + ccvTextBox.getText();
                    String exp = "" + expiryDateTextBox.getText();
                    String bank = String.valueOf(bankNameText.getText());

                    BankCardModel card = new BankCardModel(cardNo, cardName, ccv, exp, bank);
                    updateCard(card);
                }
            });

        }else{
            addBankCardTitleText.setText("Add Card");
            addCardBtn.setText("Add Card");

            addCardBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String cardNo = String.valueOf(cardNumberTextBox.getText());
                    String cardName = String.valueOf(nameOnCardTextBox.getText());
                    String ccv = "" + ccvTextBox.getText();
                    String exp = "" + expiryDateTextBox.getText();
                    String bank = String.valueOf(bankNameText.getText());

                    BankCardModel card = new BankCardModel(cardNo, cardName, ccv, exp, bank);
                    insertCard(card);
                }
            });
        }
        backBtn.setOnClickListener(view1 -> getActivity().onBackPressed());

        return view;
    }

    public void updateCard(BankCardModel card) {
        for (int i = 0; i < Provider.getUser().getBankCard().size(); i++) {
            if(Provider.getUser().getBankCard().get(i).getBankCardNo().equals(card.getBankCardNo())){
                BankCardModel currentCard = Provider.getUser().getBankCard().get(i);
                currentCard.setBankHolderName(card.getBankHolderName());
                currentCard.setCardExpiryDate(card.getCardExpiryDate());
                currentCard.setCardCvv(card.getCardCvv());
                currentCard.setBankName(card.getBankName());
                break;
            }
        }
        QueryBankCard.updateBankCard(card);
        Toast.makeText(getContext(), "Update Successfully!", Toast.LENGTH_SHORT).show();
        getActivity().onBackPressed();
    }

    public void insertCard(BankCardModel card) {
        Provider.getUser().addBankCard(card);
        QueryBankCard.addBankCard(card);
        Toast.makeText(getContext(), "Add Successfully!", Toast.LENGTH_SHORT).show();
        getActivity().onBackPressed();
    }
}