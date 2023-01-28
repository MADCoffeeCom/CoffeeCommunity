package com.example.coffeecom.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
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
import com.example.coffeecom.activity.BottomNavigationActivity;
import com.example.coffeecom.fragment.CoffeeDetailsFragment;
import com.example.coffeecom.fragment.CoffeeListFragment;
import com.example.coffeecom.fragment.HomeActivityFragment;
import com.example.coffeecom.model.BaristaModel;
import com.example.coffeecom.model.CoffeeModel;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CoffeeTypeAdapter extends RecyclerView.Adapter<CoffeeTypeAdapter.ViewHolder> {

    ArrayList<CoffeeModel> coffees;
    Context activity;

    public CoffeeTypeAdapter(ArrayList<CoffeeModel> coffees, Context activity) {
        this.coffees = coffees;
        this.activity = activity;
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView coffeeTitle;
        ImageView coffeePic;
        ConstraintLayout coffeeCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            coffeeTitle = itemView.findViewById(R.id.coffeeCardTitle);
            coffeePic = itemView.findViewById(R.id.coffeeCardImage);
            coffeeCard = itemView.findViewById(R.id.coffeeTypeCard);
        }
    }

    public void filterList(ArrayList<CoffeeModel> coffee) {
        coffees = coffee;
        notifyDataSetChanged();
    }

    //Create viewholder or card based on the xml file
    @Override
    public CoffeeTypeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_coffee_type_small, parent, false);
        return new ViewHolder(inflate);
    }

    //fill in the xml file with necessary information
    @Override
    public void onBindViewHolder(@NonNull CoffeeTypeAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.coffeeTitle.setText(coffees.get(position).getCoffeeTitle());

        //code to insert picture
        String picUrl = coffees.get(position).getCoffeePic();
//        String picUrl = "http://" + Provider.getIpAddress() + "/images/" + coffees.get(position).getCoffeePic();
//        Log.i("picture",""+picUrl);
        CompletableFuture cf = null;
        CompletableFuture cf2 = null;
        try {
            Provider.getBaristas().clear();
            Provider.getCoffees().clear();
//            DownloadImageTask dit = new DownloadImageTask(holder.coffeePic);
//            String picUrl2 = "https://i.imgur.com/bhOjb3v.jpeg";
//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    dit.execute(picUrl);
//                }
//            });
//            thread.join();
//            thread.start();
//            cf.supplyAsync(() -> dit.execute(picUrl2)).join();

//            Log.i("picture", image.toString());
//            Bitmap emptyBitmap = Bitmap.createBitmap(image.getWidth(), image.getHeight(), image.getConfig());
//            if (image.sameAs(emptyBitmap)) {
//                Log.i("picture", "it is empty");
//                // myBitmap is empty/blank
//            }
//            dit.onPostExecute(image);
//
//            holder.coffeePic.setImageBitmap(image);
        } catch (Exception e) {
            e.printStackTrace();
        }


        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.coffeePic);

        holder.coffeeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Coffeetype in adapter", coffees.get(position).getCoffeeType());
                Provider.setCurrentCoffeeId(coffees.get(position).getCoffeeId());
                ((BottomNavigationActivity)activity).replaceFragment(new CoffeeDetailsFragment());

            }
        });
    }

    //loop for how many times
    @Override
    public int getItemCount() {
        return coffees.size();
    }
//    public void loadGlideImage(Context context, ImageView imageView, String url) {
//        GlideApp.with(context.getApplicationContext())
//                .load(url)
//                .signature(new ObjectKey(url))
//                .dontAnimate()
//                .into(imageView);
//    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Log.i("bruhImage",urldisplay);
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}


