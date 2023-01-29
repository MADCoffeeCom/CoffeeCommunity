package com.example.coffeecom.helper;

import com.example.coffeecom.model.UploadResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public abstract class MyAPI {

    @Multipart
    @POST("Api.php?apicall=upload")
    public static Call<UploadResponse> uploadImage(
            @Part MultipartBody.Part image,
            @Part("desc") RequestBody desc
    ) {
        return null;
    }

    class Companion {
        public MyAPI invoke() {
            return new Retrofit.Builder()
                    .baseUrl("http://192.168.0.106/CoffeeCommunityPHP/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MyAPI.class);
        }
    }
}
