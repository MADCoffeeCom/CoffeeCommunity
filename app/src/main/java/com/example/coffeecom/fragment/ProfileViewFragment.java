package com.example.coffeecom.fragment;

import static com.example.coffeecom.helper.ToTitleCase.toTitleCase;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.adapter.CoffeeTypeAdapter;
import com.example.coffeecom.adapter.ProfilePostHistoryAdapter;
import com.example.coffeecom.model.BaristaModel;
import com.example.coffeecom.model.CoffeeModel;
import com.example.coffeecom.model.PostModel;
import com.example.coffeecom.model.ProfileModel;
import com.example.coffeecom.query.NewQueryProfile;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.ArrayList;

public class ProfileViewFragment extends Fragment {

    private static final String TAG = "ProfileViewFragment";

    TextView userName, userRole, userLocation, userDesc;
    ImageButton backBtn;
    ImageView userPic;

    TextView sellingCoffeeText, userFeedText;
    RecyclerView userCoffeeRV, userFeedRV;
    TextView noFeedText;

    ProfileModel currentProfile;
    BaristaModel userBaristaAcc;
    ArrayList<CoffeeModel> sellingCoffees = new ArrayList<>();
    ArrayList<PostModel> posts = new ArrayList<>();
    String role = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_view, container, false);
        initialiseView(view);

        Bundle bundle = this.getArguments();
        if(bundle != null){
            String userId = bundle.getString("userId");
            queryUser(userId);

        }

        role = currentProfile.getBaristaId().equals("") ? "User" : "Barista";
        onBind();

        return view;
    }

    public void initialiseView(View view){
        userName = view.findViewById(R.id.userName);
        userRole = view.findViewById(R.id.userRole);
        userLocation = view.findViewById(R.id.userLocation);
        userDesc = view.findViewById(R.id.userDesc);
        backBtn = view.findViewById(R.id.backBtn);
        backBtn.setOnClickListener(view1 -> getActivity().onBackPressed());
        userPic = view.findViewById(R.id.userPic);
        sellingCoffeeText = view.findViewById(R.id.sellingCoffeeText);
        userCoffeeRV = view.findViewById(R.id.userCoffeeRV);
        userFeedText = view.findViewById(R.id.userFeedText);
        userFeedRV = view.findViewById(R.id.userFeedRV);
        noFeedText = view.findViewById(R.id.noFeedText);
    }

    public void onBind(){
        //When it is user
        userName.setText(toTitleCase(currentProfile.getUserName()));
        userRole.setText(role);
        userLocation.setText(currentProfile.getUserTaman());
        userDesc.setVisibility(View.GONE);
        sellingCoffeeText.setVisibility(View.GONE);
        userCoffeeRV.setVisibility(View.GONE);
        noFeedText.setVisibility(View.GONE);

        //code to insert picture
        if(!currentProfile.getUserPic().equals("")){
            String picUrl = currentProfile.getUserPic();
            int drawableResourceId = getContext().getResources().getIdentifier(picUrl, "drawable", getContext().getPackageName());
            Glide.with(getContext()).load(drawableResourceId).into(userPic);
        }

        //When it is barista
        if(role.equals("Barista")){
            querySellingCoffee(currentProfile.getBaristaId());
            Log.i(TAG, "role: " + role);
            Log.i(TAG, "baristaId: " + currentProfile.getBaristaId());
            for (BaristaModel barista: Provider.getBaristas()) {
                if(currentProfile.getBaristaId().equals(barista.getBaristaId())){
                    userBaristaAcc = barista;
                    break;
                }
            }
            for (CoffeeModel coffee:Provider.getCoffees()) {
                Log.i(TAG, "Provider Coffee: " + coffee.getCoffeeId());
                for (String id:currentProfile.getSellingCoffeeId()) {
                    Log.i(TAG, "User Coffee: " + id);
                    if(id.equals(coffee.getCoffeeId())){
                        sellingCoffees.add(coffee);
                    }
                }
            }
            userDesc.setText(userBaristaAcc.getBaristaDesc());
            sellingCoffeeText.setVisibility(View.VISIBLE);
            userCoffeeRV.setVisibility(View.VISIBLE);
            userDesc.setVisibility(View.VISIBLE);
            recyclerViewCoffeeType();
        }

        //Post
        for (PostModel post:Provider.getPosts()) {
            if(post.getPosterId().equals(currentProfile.getUserId())){
                posts.add(post);
            }
        }
        if (posts.isEmpty()){
            noFeedText.setVisibility(View.VISIBLE);
            userFeedRV.setVisibility(View.GONE);
        }else{
            postRecycleView();
        }

    }

    private void recyclerViewCoffeeType() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        userCoffeeRV.setLayoutManager(linearLayoutManager);
        userCoffeeRV.setAdapter(new CoffeeTypeAdapter(sellingCoffees, getActivity()));
    }

    public void postRecycleView() {
        userFeedRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        userFeedRV.setAdapter(new ProfilePostHistoryAdapter(posts, getActivity()));
    }

    public void queryUser(String id){
        String[] field = new String[1];
        field[0] = "userId";

        //Creating array for data
        String[] data = new String[1];
        data[0] = id;

        PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/profile.php", "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                String result = putData.getResult();

                String[] profileDetails = result.split(" - ");
                String userId = profileDetails[0];
                String picUrl = profileDetails[1];
                String baristaId = profileDetails[2];
                String adminId = profileDetails[3];
                String userName = profileDetails[4];
                String email = profileDetails[5];
                String streetNo = profileDetails[6];
                String taman = profileDetails[7];
                String postCode = (profileDetails[8]);
                String state = profileDetails[9];

                currentProfile = new ProfileModel(picUrl, userId, baristaId, adminId, userName, email, streetNo, taman, postCode, state);
                Log.i(TAG, "User Found" + userId);
            }
        }
    }

    public void querySellingCoffee(String baristaId){
        String[] field = new String[1];
        field[0] = "baristaId";

        //Creating array for data
        String[] data = new String[1];
        data[0] = baristaId;

        PutData putData = new PutData("http://" + Provider.getIpAddress()+ "/CoffeeCommunityPHP/coffeesoldbybarista.php", "POST", field, data);

        if (putData.startPut()) {
            if (putData.onComplete()) {
                String result = putData.getResult();
                String[] resultSplitted = result.split("split");
                for (String str: resultSplitted) {
                    String coffeeId = str;
                    currentProfile.addSellingCoffeeId(coffeeId);
                    Log.i(TAG, "coffeeId from query: "+ coffeeId);
                }
            }

        }
    }
}