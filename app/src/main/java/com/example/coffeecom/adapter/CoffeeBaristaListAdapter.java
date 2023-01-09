package com.example.coffeecom.adapter;

import static com.example.coffeecom.helper.ToTitleCase.toTitleCase;

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

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.activity.BottomNavigationActivity;
import com.example.coffeecom.fragment.CoffeeDetailsFragment;
import com.example.coffeecom.model.BaristaModel;
import com.example.coffeecom.model.CartCardModel;
import com.example.coffeecom.model.CoffeeModel;
import com.example.coffeecom.model.ProfileModel;
import com.example.coffeecom.query.QueryCartItem;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.ArrayList;

public class CoffeeBaristaListAdapter extends RecyclerView.Adapter<CoffeeBaristaListAdapter.ViewHolder> {

    private static final String TAG = "CoffeeBaristaListAdapter";
    ArrayList<CoffeeModel> coffees;
    ArrayList<BaristaModel> baristaWithCoffee;
    BaristaModel currentBarista;
    Context activity;
    char mode; //b for in barista or c for in coffee view


    public CoffeeBaristaListAdapter(ArrayList<CoffeeModel> coffeesWithType, ArrayList<BaristaModel> baristaWithCoffee, char mode, Context activity) {
        this.coffees = coffeesWithType;
        this.baristaWithCoffee = baristaWithCoffee;
        this.mode = mode;
        this.activity = activity;
    }

    public CoffeeBaristaListAdapter(BaristaModel currentBarista, ArrayList<CoffeeModel> coffee, char mode, Context activity) {
        this.coffees = coffee;
        this.currentBarista = currentBarista;
        this.mode = mode;
        this.activity = activity;
    }

    public void filterList(ArrayList<CoffeeModel> filterlist) {
        coffees = filterlist;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView coffeeListPic, addToCartBtn;
        TextView cardTitle, coffeeDesc, coffeePricePerItemText, baristaLocationText;
        ConstraintLayout coffeeBaristaListCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            coffeeListPic = itemView.findViewById(R.id.coffeeListPic);
            cardTitle = itemView.findViewById(R.id.titleTextHoriCard);
            coffeeDesc = itemView.findViewById(R.id.descTextHoriCard);
            coffeePricePerItemText = itemView.findViewById(R.id.coffeePricePerItemText);
            baristaLocationText = itemView.findViewById(R.id.baristaLocationText);
            coffeeBaristaListCard = itemView.findViewById(R.id.coffeeBaristaListCard);
            addToCartBtn = itemView.findViewById(R.id.addToCartBtn);
        }
    }

    @NonNull
    @Override
    public CoffeeBaristaListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_coffee_barista_list_horizontal, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CoffeeBaristaListAdapter.ViewHolder holder, int position) {
        QueryCartItem.queryCartItem();
        if (mode == 'b') {
            onBindWhenBaristaView(holder, position);
        } else {
            onBindWhenCoffeeView(holder, position);
        }

        //coffee pic
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(coffees.get(position).getCoffeePic(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.coffeeListPic);
    }

    private void onBindWhenCoffeeView(ViewHolder holder, int position) {
        holder.cardTitle.setText(toTitleCase(baristaWithCoffee.get(position).getUserName()));
        holder.coffeeDesc.setText(coffees.get(position).getCoffeeDesc());
        holder.coffeePricePerItemText.setText(String.valueOf(coffees.get(position).getCoffeePrice()));
        holder.baristaLocationText.setText(baristaWithCoffee.get(position).getUserTaman());

        holder.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CoffeeModel currentSelectedCoffee = coffees.get(position);
                boolean containThisCoffee = false;
                int coffeeAmountInCart;
                Log.i("CoffeeBaristaListAdapter", "Here: " + Provider.getUser().getCartCard().size());
                for (CartCardModel cc : Provider.getUser().getCartCard()) {
                    Log.i("CoffeeBaristaListAdapter", "Here: " + cc.getCoffeeName() + " " + currentSelectedCoffee.getCoffeeTitle());
                    if (cc.getCoffeeName().equals(currentSelectedCoffee.getCoffeeTitle())) {
                        coffeeAmountInCart = cc.getCoffeeQuantity() + 1;
                        cc.setCoffeeQuantity(coffeeAmountInCart);
                        containThisCoffee = true;
                        updateCoffeeInCart(Provider.getUser(), currentSelectedCoffee, coffeeAmountInCart);
                        notifyDataSetChanged();
                        break;
                    }
                }
                if (containThisCoffee == false) {
                    coffeeAmountInCart = 1;
                    CartCardModel cartCardModel = new CartCardModel(currentSelectedCoffee.getCoffeeId(), currentSelectedCoffee.getCoffeePic(), currentSelectedCoffee.getCoffeeTitle(), currentSelectedCoffee.getCoffeePrice(), coffeeAmountInCart, currentSelectedCoffee.getBaristaId());
                    Provider.getUser().addCartCard(cartCardModel);
                    if (!Provider.getBaristaIdInCart().contains(currentSelectedCoffee.getBaristaId())){
                        Provider.addBaristaIdInCart(currentSelectedCoffee.getBaristaId());
                    }
                    addCoffeeIntoCart(Provider.getUser(), currentSelectedCoffee, coffeeAmountInCart);
                    notifyDataSetChanged();
                }
            }
        });

        holder.coffeeBaristaListCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to details coffee view
                Provider.setCurrentCoffeeId(coffees.get(position).getCoffeeId());
                ((BottomNavigationActivity)activity).replaceFragment(new CoffeeDetailsFragment());
            }
        });

        //coffee pic
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(coffees.get(position).getCoffeePic(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.coffeeListPic);
    }

    private void onBindWhenBaristaView(@NonNull CoffeeBaristaListAdapter.ViewHolder holder, int position) {
        holder.cardTitle.setText(coffees.get(position).getCoffeeTitle());
        holder.coffeeDesc.setText(coffees.get(position).getCoffeeDesc());
        holder.coffeePricePerItemText.setText(String.valueOf(coffees.get(position).getCoffeePrice()));
        holder.baristaLocationText.setText(currentBarista.getUserTaman());

        holder.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CoffeeModel currentSelectedCoffee = coffees.get(position);
                boolean containThisCoffee = false;
                int coffeeAmountInCart;
                Log.i("CoffeeBaristaListAdapter", "Here: " + Provider.getUser().getCartCard().size());
                for (CartCardModel cc : Provider.getUser().getCartCard()) {
                    Log.i("CoffeeBaristaListAdapter", "Here: " + cc.getCoffeeName() + " " + currentSelectedCoffee.getCoffeeTitle());
                    if (cc.getCoffeeName().equals(currentSelectedCoffee.getCoffeeTitle())) {
                        coffeeAmountInCart = cc.getCoffeeQuantity() + 1;
                        cc.setCoffeeQuantity(coffeeAmountInCart);
                        containThisCoffee = true;
                        updateCoffeeInCart(Provider.getUser(), currentSelectedCoffee, coffeeAmountInCart);
                        notifyDataSetChanged();
                        break;
                    }
                }
                if (containThisCoffee == false) {
                    coffeeAmountInCart = 1;
                    CartCardModel cartCardModel = new CartCardModel(currentSelectedCoffee.getCoffeeId(), currentSelectedCoffee.getCoffeePic(), currentSelectedCoffee.getCoffeeTitle(), currentSelectedCoffee.getCoffeePrice(), coffeeAmountInCart, currentSelectedCoffee.getBaristaId());
                    Provider.getUser().addCartCard(cartCardModel);
                    if (!Provider.getBaristaIdInCart().contains(currentSelectedCoffee.getBaristaId())){
                        Provider.addBaristaIdInCart(currentSelectedCoffee.getBaristaId());
                    }
                    addCoffeeIntoCart(Provider.getUser(), currentSelectedCoffee, coffeeAmountInCart);
                    notifyDataSetChanged();
                }
            }
        });

        holder.coffeeBaristaListCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to details coffee view
                Provider.setCurrentCoffeeId(coffees.get(position).getCoffeeId());
                CoffeeDetailsFragment coffeeFrag = new CoffeeDetailsFragment();
                try{
                    ((BottomNavigationActivity)activity).replaceFragment(coffeeFrag);
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return coffees.size();
    }

    public void addCoffeeIntoCart(ProfileModel currentUser, CoffeeModel currentSelectedCoffee, int amount) {
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
                data[1] = currentSelectedCoffee.getCoffeeId();
                data[2] = Integer.toString(amount);

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/addCoffeeToCart.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        if (result.equals("Successfully added coffee to cart")) {
                            Toast.makeText(activity.getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d("myTag", result);
                            Toast.makeText(activity.getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                        }
                        //End ProgressBar (Set visibility to GONE)
                    }
                }
                //End Write and Read data with URL
            }
        });
    }

    public void updateCoffeeInCart(ProfileModel currentUser, CoffeeModel currentSelectedCoffee, int amount) {
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
                data[1] = currentSelectedCoffee.getCoffeeId();
                data[2] = Integer.toString(amount);

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/updateCoffeeInCart.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        if (result.equals("Successfully updated coffee in cart")) {
                            Toast.makeText(activity.getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d("myTag", result);
                            Toast.makeText(activity.getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                        }
                        //End ProgressBar (Set visibility to GONE)
                    }
                }
                //End Write and Read data with URL
            }
        });
    }
}


