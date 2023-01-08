package com.example.coffeecom.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.fragment.BaristaListFragment;
import com.example.coffeecom.fragment.CoffeeCartFragment;
import com.example.coffeecom.fragment.CoffeeDetailsFragment;
import com.example.coffeecom.fragment.MapsFragment;
import com.example.coffeecom.model.CartCardModel;
import com.example.coffeecom.model.CartModel;
import com.example.coffeecom.model.CoffeeModel;
import com.example.coffeecom.model.ProfileModel;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.ArrayList;
import java.util.List;

public class CartCardAdapter extends RecyclerView.Adapter<CartCardAdapter.ViewHolder> {
    private ArrayList<CartCardModel> cartCardModelList;
    private LayoutInflater inflater;
    private Context context;
    ArrayList<String> coffeePic = new ArrayList<>();

    TextView coffeeName;
    TextView coffeePrice;
    TextView coffeeQuantity;
    ImageView deleteCardBtn;

    public CartCardAdapter(Context context, ArrayList<CartCardModel> cartCardModelList) {
        this.context = context;
        this.cartCardModelList = cartCardModelList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.viewholder_coffee_cart_card, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        coffeePic.add("coffee1");
        String picUrl = coffeePic.get(position);
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.coffeePic);
        CartCardModel cartCardModel = cartCardModelList.get(position);
        holder.bindData(cartCardModel);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) context;
                Provider.setCurrentCoffeeId(cartCardModelList.get(position).getCoffeeId());
                CoffeeDetailsFragment coffeeDetailsFragment = new CoffeeDetailsFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.containerMainPage,coffeeDetailsFragment).addToBackStack("coffeeDetailFragment").commit();
            }
        });

        deleteCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        deleteCardBtn.setEnabled(false);
                        deleteCoffeeInCart(Provider.getUser(),cartCardModelList.get(position).getCoffeeId(),cartCardModelList.get(position).getCoffeeQuantity(), position);
                    }
                });
                thread.start();
                try {
                    thread.join();
                    if (!thread.isAlive()){
                        deleteCardBtn.setEnabled(true);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return cartCardModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView coffeePic;

        public ViewHolder(View itemView) {
            super(itemView);
            coffeePic = itemView.findViewById(R.id.coffeeCartPic);
            coffeeName = itemView.findViewById(R.id.coffeeNameCardText);
            coffeePrice = itemView.findViewById(R.id.coffeePriceCardText);
            coffeeQuantity = itemView.findViewById(R.id.coffeeQuantityCardText);
            deleteCardBtn = itemView.findViewById(R.id.deleteCardBtn);
        }

        public void bindData(CartCardModel cartCardModel) {
            coffeeName.setText(cartCardModel.getCoffeeName());
            coffeePrice.setText(Double.toString(cartCardModel.getCoffeePrice()));
            coffeeQuantity.setText(Integer.toString(cartCardModel.getCoffeeQuantity()));
        }
    }

    public void deleteCoffeeInCart(ProfileModel currentUser, String coffeeId, int amount, int position){

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Starting Write and Read data with URL
                //Creating array for parameters
                String[] field = new String[3];
                field[0] = "userId";
                field[1] = "coffeeId";
                field[2] = "amount";

                //Creating array for data
                String[] data = new String[3];
                data[0] = currentUser.getUserId();
                data[1] = coffeeId;
                data[2] = Integer.toString(amount);

                if (amount <= 1){
                    PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/deleteCoffeeInCart.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if (result.equals("Successfully deleted coffee in cart")) {
                                Toast.makeText(context.getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                            } else {
                                Log.d("myTag", result);
                                Toast.makeText(context.getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                            }
                            //End ProgressBar (Set visibility to GONE)
                        }
                    }
                    for (CartModel cm : Provider.getCartModelList()) {
                        for(CartCardModel cc : cm.getCartCardModelsList()){
                            if (cc.getCoffeeId().equals(coffeeId)){
                                if (cm.getCartCardModelsList().get(0).equals(cc)){
                                    cm.replaceSecondToFirst();
                                    notifyDataSetChanged();
                                }
                                cm.removeCartCard(cc);
                                if (cm.getCartCardModelsList().size()==0){
                                    Provider.getBaristaIdInCart().remove(cm.getBarista().getBaristaId());
                                    Provider.removeCartModel(cm);
                                }
                            }
                        }
                    }
                    notifyDataSetChanged();
                }
                else{
                    data[2] = Integer.toString(amount-1);
                    PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/updateCoffeeInCart.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if (result.equals("Successfully updated coffee in cart")) {
                                Toast.makeText(context.getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                            } else {
                                Log.d("myTag", result);
                                Toast.makeText(context.getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                            }
                            //End ProgressBar (Set visibility to GONE)
                        }
                    }
                    for (CartModel cm : Provider.getCartModelList()) {
                        for(CartCardModel cc : cm.getCartCardModelsList()){
                            Log.i("CartCardAdapter", ""+ cc.getCoffeeId() + " compare: " + coffeeId + " " + cm.getBarista());
                            if (cc.getCoffeeId().equals(coffeeId)){
                                Log.i("CartCardAdapter", cc.getCoffeeId() + " decrease and now have " + (amount-1));
                                cc.setCoffeeQuantity(amount-1);
                            }
                        }
                    }
                    notifyDataSetChanged();
                    //End Write and Read data with URL
                }
            }});
        }
    }

