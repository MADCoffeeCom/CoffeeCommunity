package com.example.coffeecom.query;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.coffeecom.Provider;
import com.example.coffeecom.model.BankCardModel;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class QueryBankCard {
    private static final String TAG = "QueryBankCard";

    public static void queryBankCard() {
        Provider.getUser().getBankCard().clear();
        Log.i(TAG, "queryBankCard: Run here once");

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[1];
                field[0] = "walletId";

                String[] data = new String[1];
                data[0] = Provider.getUser().getWalletId();

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/bankcard.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        String[] splitResult = result.split("split");
                        for (String bruh:splitResult) {
                            String[] str = bruh.split(" - ");
                            String cardNo = str[0];
                            String cardHolderName = str[1];
                            String cardCvv = str[2];
                            String expDate = str[3];
                            String bankName = str[4];

                            BankCardModel card = new BankCardModel(cardNo, cardHolderName, cardCvv, expDate, bankName);
                            Provider.getUser().addBankCard(card);

                        }
                    }
                }
            }
        });
    }

    public static void addBankCard(BankCardModel card) {
        Log.i(TAG, "addBankCard: Run here once");

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[6];
                field[0] = "cardNo";
                field[1] = "walletId";
                field[2] = "cardHolderName";
                field[3] = "cardCvv";
                field[4] = "expDate";
                field[5] = "bankName";

                String[] data = new String[6];
                data[0] = card.getBankCardNo();
                data[1] = Provider.getUser().getWalletId();
                data[2] = card.getBankHolderName();
                data[3] = "" + card.getCardCvv();
                data[4] = "" + card.getCardExpiryDate();
                data[5] = card.getBankName();

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/addbankcard.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        Log.i(TAG, "run: " + result);
                    }
                }
            }
        });
    }

    public static void updateBankCard(BankCardModel card) {
        Log.i(TAG, "addBankCard: Run here once");

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[5];
                field[0] = "cardNo";
                field[1] = "cardHolderName";
                field[2] = "cardCvv";
                field[3] = "expDate";
                field[4] = "bankName";

                String[] data = new String[5];
                data[0] = card.getBankCardNo();
                data[1] = card.getBankHolderName();
                data[2] = "" + card.getCardCvv();
                data[3] = "" + card.getCardExpiryDate();
                data[4] = card.getBankName();

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/updatebankcard.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        Log.i(TAG, "run: " + result);
                    }
                }
            }
        });
    }

    public static void removeBankCard(String cardNo) {
        Log.i(TAG, "addBankCard: Run here once");

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[1];
                field[0] = "cardNo";

                String[] data = new String[1];
                data[0] = cardNo;

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/removebankcard.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        Log.i(TAG, "run: " + result);
                    }
                    for (int i = 0; i < Provider.getUser().getBankCard().size(); i++) {
                        if(Provider.getUser().getBankCard().get(i).getBankCardNo().equals(cardNo)){
                            Provider.getUser().getBankCard().remove(i);
                        }
                    }
                }
            }
        });
    }

}
