package com.example.coffeecom.adapter;

import static com.example.coffeecom.helper.CoordinateToDistance.coordinateToDistance;
import static com.example.coffeecom.helper.ToTitleCase.toTitleCase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.fragment.BaristaListFragment;
import com.example.coffeecom.model.BaristaModel;

import java.util.ArrayList;

public class BaristaCardAdapter extends RecyclerView.Adapter<BaristaCardAdapter.ViewHolder>{

    ArrayList<BaristaModel> baristas;


    public BaristaCardAdapter(ArrayList<BaristaModel> baristas) {
        this.baristas = baristas;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView baristaPic;
        TextView baristaCardName, baristaLocation, baristaDistance;
        ConstraintLayout baristaSmallCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            baristaPic = itemView.findViewById(R.id.baristaPic);
            baristaCardName = itemView.findViewById(R.id.baristaCardName);
            baristaLocation = itemView.findViewById(R.id.baristaLocation);
            baristaDistance = itemView.findViewById(R.id.baristaDistance);
            baristaSmallCard = itemView.findViewById(R.id.baristaSmallCard);
        }
    }

    @NonNull
    @Override
    public BaristaCardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_barista_small, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull BaristaCardAdapter.ViewHolder holder, int position) {

        //barista pic but nid to update to sql code
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(baristas.get(position).getUserPic(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.baristaPic);

        holder.baristaCardName.setText(toTitleCase(baristas.get(position).getUserName()));
        holder.baristaLocation.setText(toTitleCase(baristas.get(position).getUserTaman()));
        holder.baristaDistance.setText(coordinateToDistance(baristas.get(position).getUserLocation()));

        holder.baristaSmallCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //input code to open details coffee page
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                BaristaListFragment baristaList = new BaristaListFragment();
                Provider.setCurrentBaristaId(baristas.get(holder.getAdapterPosition()).getBaristaId());
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.containerMainPage,baristaList).addToBackStack(null).commit();
                //this code still need to refine
//                Intent intent = new Intent(holder.itemView.getContext(), BaristaListActivity.class);
//                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Provider.getBaristas().size();
    }


}
