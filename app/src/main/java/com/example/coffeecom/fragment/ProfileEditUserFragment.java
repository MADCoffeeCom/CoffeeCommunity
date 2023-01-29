package com.example.coffeecom.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.activity.BottomNavigationActivity;
import com.vishnusivadas.advanced_httpurlconnection.PutData;


public class ProfileEditUserFragment extends Fragment {

    private static final String TAG = "ProfileEditUserFragment";
    private EditText userName, userEmail, oldPasswordTB, userPassword, confirmPasswordTextBox;
    private ImageButton btnBack;
    private Button btnUpdate;
    private String password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_profile_edit_user_details,container,false);

        userName = view.findViewById(R.id.editTextProfileName);
        userEmail = view.findViewById(R.id.editTextProfileEmail);
        userPassword = view.findViewById(R.id.editTextProfilePassword);
        confirmPasswordTextBox = view.findViewById(R.id.confirmPasswordTextBox);
        btnBack = view.findViewById(R.id.imgEditPostBack);
        btnUpdate = view.findViewById(R.id.btnProfileUpdate);
        oldPasswordTB = view.findViewById(R.id.oldPasswordTB);

        initialiseData();
//        queryUserPassword();

        btnUpdate.setOnClickListener(view13 -> {
            updateUserDetails();
            getActivity().onBackPressed();
        });

        userName.setEnabled(false);
        userName.setOnClickListener(view12 -> Toast.makeText(getContext(), "Username is not editable!", Toast.LENGTH_SHORT));
        btnBack.setOnClickListener(view1 -> ((BottomNavigationActivity)getActivity()).onBackPressed());

        return view;
    }

    private void initialiseData() {
        userName.setText(Provider.getUser().getUserName());
        userEmail.setText(Provider.getUser().getEmail());
        userPassword.setText("");
        confirmPasswordTextBox.setText("");
        userPassword.setText(password);
        confirmPasswordTextBox.setText(password);
    }

//    private void queryUserPassword() {
//        String[] field = new String[1];
//        field[0] = "userId";
//        String[] data = new String[1];
//        data[0] = Provider.getUser().getUserId();
//
//        PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/userpassword.php", "POST", field, data);
//        if (putData.startPut() && putData.onComplete()) {
//            String result = putData.getResult();
//            Log.i(TAG, "queryUserPassword: " + result);
//            password = result;
////            userPassword.setText(password);
////            confirmPasswordTextBox.setText(password);
//        }
//    }

    private void updateUserDetails() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> {
            String[] field = new String[4];
            field[0] = "email";
            field[1] = "password";
            field[2] = "oldpassword";
            field[3] = "userId";

            //Creating array for data
            String[] data = new String[4];
            data[0] = String.valueOf(userEmail.getText());
            data[1] = String.valueOf(userPassword.getText());
            data[2] = String.valueOf(oldPasswordTB.getText());
            data[3] = Provider.getUser().getUserId();

            PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/updateuserdetails.php", "POST", field, data);
            if (putData.startPut() && putData.onComplete()) {
                String result = putData.getResult();
                Log.i(TAG, "updateUserDetails: " + result);
                Toast.makeText(getContext(), result, Toast.LENGTH_SHORT);
                initialiseData();
            }
        });
    }
}