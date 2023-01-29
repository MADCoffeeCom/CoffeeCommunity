package com.example.coffeecom.fragment;

import static com.example.coffeecom.helper.ToTitleCase.toTitleCase;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.activity.BottomNavigationActivity;
import com.example.coffeecom.adapter.RatingBarAdapter;
import com.example.coffeecom.helper.DownloadImageHelper;
import com.example.coffeecom.model.BaristaModel;
import com.example.coffeecom.model.CartCardModel;
import com.example.coffeecom.model.CoffeeModel;
import com.example.coffeecom.model.ProfileModel;
import com.example.coffeecom.query.QueryCoffee;
import com.example.coffeecom.query.QueryRating;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.concurrent.CompletableFuture;


public class CoffeeDetailsFragment extends Fragment {

    private ImageView coffeeDetailsBackBtn;
    private ImageView coffeeImage;
    private TextView coffeeDetailsNameText, baristaNameCoffeeDetailsText, baristaLocationCoffeeDetailsText, ingredientText;
    private TextView coffeeDescCoffeeDetailsText;
    private TextView noRatingText;
    private ImageButton editCoffeeBtn, deleteCoffeeBtn;

    RecyclerView ratingRecyclerView;
    RecyclerView.Adapter ratingAdapter;

    private TextView totalPriceCoffeeDetailsText, noOfCoffeeOrderedText;
    private ImageView plusBtnCoffeeDetails, minusBtnCoffeeDetails;
    private Button addToCartBtnCoffeeDetails;

    int noOfOrder = 1;

    int currentBaristaIndex = 0;
    int currentCoffeeIndex = 0;

    BaristaModel currentBarista;
    CoffeeModel currentCoffee;

    @Override
    public void onResume() {
        super.onResume();
        initialiseCurrentBaristaAndCoffee();
        onBindCoffeeDetails();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_coffee_details,container,false);

        initialiseId(view);
        initialiseCurrentBaristaAndCoffee();
        onBindCoffeeDetails();

        editCoffeeBtn.setVisibility(View.GONE);
        deleteCoffeeBtn.setVisibility(View.GONE);
        if (Provider.getUser().getSellingCoffeeId().contains(currentCoffee.getCoffeeId())) {
            editCoffeeBtn.setVisibility(View.VISIBLE);
            deleteCoffeeBtn.setVisibility(View.VISIBLE);
            editCoffeeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("coffeeId", currentCoffee.getCoffeeId().toString());
                    ((BottomNavigationActivity)getActivity()).replaceFragmentWithData(new AddCoffeeFragment(), bundle);

                }
            });
            deleteCoffeeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setCancelable(true);
                    builder.setTitle("Delete Coffee");
                    builder.setMessage("Are you sure want to delete " + currentCoffee.getCoffeeTitle() + "?");
                    builder.setPositiveButton("Delete", (dialog, which) -> {
                        QueryCoffee.deleteCoffee(currentCoffee.getCoffeeId());
                        QueryCoffee.queryCoffee();
                        Toast.makeText(getContext(), "Delete Coffee success!", Toast.LENGTH_SHORT).show();
                        getActivity().onBackPressed();
                    });
                    builder.setNegativeButton("Cancel", (dialog, id) -> dialog.cancel());
                    builder.show();
                }
            });
        }

        plusBtnCoffeeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i("CoffeeBaristaListAdapter", "Here: " + Provider.getUser().getCartCard().size());
                Log.i("diao","ni");
                noOfOrder++;
                noOfCoffeeOrderedText.setText(String.valueOf(noOfOrder));
                totalPriceCoffeeDetailsText.setText(String.format("%.2f", (currentCoffee.getCoffeePrice() * noOfOrder )));

            }
        });

        minusBtnCoffeeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(noOfOrder > 1) {
                    noOfOrder--;
                    noOfCoffeeOrderedText.setText(String.valueOf(noOfOrder));
                    totalPriceCoffeeDetailsText.setText(String.format("%.2f", (currentCoffee.getCoffeePrice() * noOfOrder )));
                }
            }
        });

        addToCartBtnCoffeeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartCardModel currentCardCart = Provider.getUser().getOneCardCart(currentCoffee.getCoffeeId());
                boolean containThisCoffee = false;
                int coffeeAmountInCart;
                if (currentCardCart != null){
                    coffeeAmountInCart = currentCardCart.getCoffeeQuantity()+noOfOrder;
                    currentCardCart.setCoffeeQuantity(coffeeAmountInCart);
                    containThisCoffee = true;
                    updateCoffeeInCart(Provider.getUser(), currentCoffee, coffeeAmountInCart);
                }
                else{
                    if (containThisCoffee == false) {
                        coffeeAmountInCart = 1;
                        CartCardModel cartCardModel = new CartCardModel(currentCoffee.getCoffeeId(), currentCoffee.getCoffeePic(), currentCoffee.getCoffeeTitle(), currentCoffee.getCoffeePrice(), noOfOrder, currentCoffee.getBaristaId());
                        Provider.getUser().addCartCard(cartCardModel);
                        if (!Provider.getBaristaIdInCart().contains(currentCoffee.getBaristaId())){
                            Provider.addBaristaIdInCart(currentCoffee.getBaristaId());
                        }
                        addCoffeeIntoCart(Provider.getUser(), currentCoffee, noOfOrder);
                    }
                }
            }
        });

        coffeeDetailsBackBtn.setOnClickListener(view1 -> getActivity().onBackPressed());

        recyclerViewRating();

        // Inflate the layout for this fragment
        return view;
    }

    private void initialiseId(View view){
        coffeeDetailsNameText = view.findViewById(R.id.coffeeDetailsNameText);
        baristaNameCoffeeDetailsText = view.findViewById(R.id.baristaNameCoffeeDetailsText);
        baristaLocationCoffeeDetailsText = view.findViewById(R.id.baristaLocationCoffeeDetailsText);
        coffeeDescCoffeeDetailsText = view.findViewById(R.id.coffeeDescCoffeeDetailsText);
        noRatingText = view.findViewById(R.id.noRatingText);
        ingredientText = view.findViewById(R.id.ingredientText);

        ratingRecyclerView = view.findViewById(R.id.ratingRecyclerView);

        totalPriceCoffeeDetailsText = view.findViewById(R.id.totalPriceCoffeeDetailsText);
        noOfCoffeeOrderedText = view.findViewById(R.id.noOfCoffeeOrderedText);

        plusBtnCoffeeDetails = view.findViewById(R.id.plusBtnCoffeeDetails);
        minusBtnCoffeeDetails = view.findViewById(R.id.minusBtnCoffeeDetails);
        addToCartBtnCoffeeDetails = view.findViewById(R.id.addToCartBtnCoffeeDetails);
        coffeeDetailsBackBtn = view.findViewById(R.id.coffeeDetailsBackBtn);

        ratingRecyclerView = view.findViewById(R.id.ratingRecyclerView);
        coffeeImage = view.findViewById(R.id.coffeeDetailsPic);
        editCoffeeBtn = view.findViewById(R.id.editCoffeeBtn);
        deleteCoffeeBtn = view.findViewById(R.id.deleteCoffeeBtn);

    }

    public void initialiseCurrentBaristaAndCoffee(){
        for (int j = 0; j < Provider.getCoffees().size(); j++) {
            if(Provider.getCoffees().get(j).getCoffeeId().equals(Provider.getCurrentCoffeeId())){
                currentCoffeeIndex = j;
                currentCoffee = Provider.getCoffees().get(j);
                break;
            }
        }

        for (int i = 0; i < Provider.getBaristas().size(); i++) {
            if (Provider.getBaristas().get(i).getBaristaId().equals(currentCoffee.getBaristaId())){
                currentBaristaIndex = i;
                currentBarista = Provider.getBaristas().get(i);
                break;
            }
        }
    }

    public void onBindCoffeeDetails(){
        QueryRating.queryRating(currentBarista.getBaristaId());
        coffeeDetailsNameText.setText(currentCoffee.getCoffeeTitle());
        baristaNameCoffeeDetailsText.setText(toTitleCase(currentBarista.getUserName()));
        baristaLocationCoffeeDetailsText.setText(currentBarista.getUserTaman());
        coffeeDescCoffeeDetailsText.setText(currentCoffee.getCoffeeDesc());
        ingredientText.setText(currentCoffee.getIngredients());
        totalPriceCoffeeDetailsText.setText(String.format("%.2f", (currentCoffee.getCoffeePrice() * noOfOrder )));
        noOfCoffeeOrderedText.setText(String.valueOf(noOfOrder));
        //Insert coffee pic with URL
        String picUrl = "http://" + Provider.getIpAddress() + "/images/" + currentCoffee.getCoffeePic()+".jpg";
        CompletableFuture cf = null;
        try {
            DownloadImageHelper dit = new DownloadImageHelper(coffeeImage);
            Bitmap bitmap = cf.supplyAsync(() -> dit.execute(picUrl)).join().get();
        } catch (Exception e) {
            e.printStackTrace();
        }


//        Insert coffee pic with Drawable
//        int drawableResourceId = getContext().getResources().getIdentifier(currentCoffee.getCoffeePic(), "drawable", getContext().getPackageName());
//        Glide.with(getContext()).load(drawableResourceId).into(coffeeImage);
    }


    private void recyclerViewRating() {
        if (!currentBarista.getRatings().isEmpty()) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            ratingRecyclerView.setLayoutManager(linearLayoutManager);
            Log.i("ni","it");
            ratingAdapter = new RatingBarAdapter(currentBarista.getRatings());
            ratingRecyclerView.setAdapter(ratingAdapter);
        }else{
            noRatingText.setVisibility(View.VISIBLE);
        }
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
                            Toast.makeText(getContext().getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d("myTag", result);
                            Toast.makeText(getContext().getApplicationContext(), result, Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(getContext().getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d("myTag", result);
                            Toast.makeText(getContext().getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                        }
                        //End ProgressBar (Set visibility to GONE)
                    }
                }
                //End Write and Read data with URL
            }
        });
    }
}