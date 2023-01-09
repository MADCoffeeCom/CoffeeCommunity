package com.example.coffeecom.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;
import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.helper.GMailSender;
import com.example.coffeecom.helper.SendMail;

import java.util.Random;

public class ForgotPasswordActivity extends AppCompatActivity {

    private static final String TAG = "ForgotPasswordActivity";

    TextView emailTextBox;
    Button resetPasswordBtn;
    ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailTextBox = findViewById(R.id.emailTextBox);
        resetPasswordBtn = findViewById(R.id.resetPasswordBtn);
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(view -> finish());

        resetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = "" + emailTextBox.getText();
                String otpText = otpGenerator();
                sendOtpToForgotter(email, otpText);
                Intent intent = new Intent(ForgotPasswordActivity.this, EnterOTPActivity.class);
                Log.i(TAG, "OTP: " + otpText);
                intent.putExtra("OTP", otpText);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });
    }

    public String otpGenerator () {
        Random randomNumber = new Random();
        String oneTimePassword = "";

        for (int i = 0; i < 10; i++) {
            int select = randomNumber.nextInt(3);
            switch (select) {
                case 0:
                    oneTimePassword += (char) (randomNumber.nextInt(123-97)+97);
                    break;
                case 1:
                    oneTimePassword += (char) (randomNumber.nextInt(91-65)+65);
                    break;
                case 2:
                    oneTimePassword += randomNumber.nextInt(10);
                    break;
                default:
                    throw new AssertionError();
            }
        }

        return oneTimePassword;
    }

    public void sendOtpToForgotter(String receiver, String otp){
        String subject = "OTP for renew password - Coffee Community";
        String mailMessage = "Hi \n\nYour otp is : " + otp + ".\nPlease kindly use it to renew your password.\nIf you didn't perform this action, please reply to this email immediately so we can take action.";
//        SendMail mailSender = new SendMail(this, receiver, subject, mailMessage);
//        mailSender.execute();

//        try {
//            GMailSender sender = new GMailSender();
//            sender.sendMail(subject,
//                    mailMessage,
//                    receiver);
//        } catch (Exception e) {
//            Log.e("SendMail", e.getMessage(), e);
//        }

        BackgroundMail.newBuilder(this)
                .withUsername(Provider.SENDER)
                .withPassword(Provider.SENDERPASSWORD)
                .withMailto(receiver)
                .withType(BackgroundMail.TYPE_PLAIN)
                .withSubject(subject)
                .withBody(mailMessage)
                .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(ForgotPasswordActivity.this, "Send Success", Toast.LENGTH_SHORT).show();
                    }
                })
                .withOnFailCallback(new BackgroundMail.OnFailCallback() {
                    @Override
                    public void onFail() {
                        Toast.makeText(ForgotPasswordActivity.this, "Send Fail", Toast.LENGTH_SHORT).show();

                    }
                })
                .send();
    }


}