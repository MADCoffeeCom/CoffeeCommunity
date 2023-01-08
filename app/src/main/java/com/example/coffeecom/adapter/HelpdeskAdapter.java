package com.example.coffeecom.adapter;

import static android.app.PendingIntent.getActivity;
import static android.content.Context.LAYOUT_INFLATER_SERVICE;


import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeecom.R;
import com.example.coffeecom.fragment.HelpdeskFragment;
import com.example.coffeecom.model.HelpdeskModel;

public class HelpdeskAdapter extends RecyclerView.Adapter<HelpdeskAdapter.ViewHolder>{

    private static final String TAG = "HelpdeskAdapter";

    HelpdeskModel helpdeskModel;
    Context activity;

    public HelpdeskAdapter(HelpdeskModel helpdeskModel, Context activity) {
        this.helpdeskModel = helpdeskModel;
        this.activity = activity;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView helpdeskQuestionText;
        ConstraintLayout helpdeskQuestionCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            helpdeskQuestionText = itemView.findViewById(R.id.helpdeskQuestionText);
            helpdeskQuestionCard = itemView.findViewById(R.id.helpdeskQuestionCard);
        }
    }

    @NonNull
    @Override
    public HelpdeskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_helpdesk_question, parent, false);
        return new HelpdeskAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull HelpdeskAdapter.ViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder: " + helpdeskModel.getAnswer().size());
        holder.helpdeskQuestionText.setText(helpdeskModel.getQuestion().get(position));
        holder.helpdeskQuestionCard.setOnClickListener(view -> showPopup(view, position));
    }

    @Override
    public int getItemCount() {
        return helpdeskModel.getQuestion().size();
    }

    public void showPopup(View view, int position) {
        View popupView = LayoutInflater.from(activity).inflate(R.layout.pop_up_helpdesk, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);

        TextView helpDeskAnswer = (TextView) popupView.findViewById(R.id.helpDeskAnswer);
        helpDeskAnswer.setText(helpdeskModel.getAnswer().get(position));

//        popupWindow.showAsDropDown(popupView, 0, 0);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        popupView.setOnTouchListener((v, event) -> {
            popupWindow.dismiss();
            return true;
        });
    }


}
