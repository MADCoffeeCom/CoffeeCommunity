package com.example.coffeecom.fragment;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.model.HelpdeskModel;
import com.example.coffeecom.query.QueryArticle;
import com.example.coffeecom.query.QueryHelpdesk;

public class HelpdeskEditFragment extends Fragment {

    ImageButton backBtn, deleteHelpdeskBtn;
    TextView helpIdText, editHelpdeskText;
    EditText quesText, ansText;
    Button editHelpdeskBtn;

    HelpdeskModel helpdesk;
    int currentHelpIndex;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_helpdesk_edit, container, false);
        backBtn = view.findViewById(R.id.backBtn);
        backBtn.setOnClickListener(view1 -> getActivity().onBackPressed());
        helpIdText = view.findViewById(R.id.helpIdText);
        quesText = view.findViewById(R.id.quesText);
        ansText = view.findViewById(R.id.ansText);
        editHelpdeskBtn = view.findViewById(R.id.editHelpdeskBtn);
        deleteHelpdeskBtn = view.findViewById(R.id.deleteHelpdeskBtn);
        editHelpdeskText = view.findViewById(R.id.editHelpdeskText);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            for (int i = 0; i < Provider.getHelpdesks().size(); i++) {
                if (Provider.getHelpdesks().get(i).getHelpId().equals(bundle.getString("helpId"))) {
                    helpdesk = Provider.getHelpdesks().get(i);
                    currentHelpIndex = i;
                }
            }

            helpIdText.setText(helpdesk.getHelpId());
            quesText.setText(helpdesk.getQuestion());
            ansText.setText(helpdesk.getAnswer());

            deleteHelpdeskBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setCancelable(true);
                    builder.setTitle("Delete Helpdesk");
                    builder.setMessage("Are you sure want to delete " + helpdesk.getHelpId() + "?");
                    builder.setPositiveButton("Delete", (dialog, which) -> {
                        QueryHelpdesk.removeHelpdesk(helpdesk.getHelpId());
                        Toast.makeText(getContext(), "Delete Helpdesk success!", Toast.LENGTH_SHORT).show();
                        getActivity().onBackPressed();
                    });
                    builder.setNegativeButton("Cancel", (dialog, id) -> dialog.cancel());
                    builder.show();
                }
            });
        }else{
            //create Helpdesk mode
            deleteHelpdeskBtn.setVisibility(View.GONE);
            editHelpdeskText.setText("Add Help");
            helpIdText.setText("");
            editHelpdeskBtn.setText("Add");
        }

        editHelpdeskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ques = "" + quesText.getText();
                String text = "" + ansText.getText();

                if (!ques.equals("") && !text.equals("")) {
                    if (bundle == null) {
                        QueryHelpdesk.addHelpdesk(ques, text);
                        Toast.makeText(getContext(), "Create successful!", Toast.LENGTH_SHORT).show();
                    } else {
                        String helpId = "" + helpIdText.getText();
                        QueryHelpdesk.updateHelpdesk(helpId, ques, text);
                        Toast.makeText(getContext(), "Update successful!", Toast.LENGTH_SHORT).show();
                    }
                    getActivity().onBackPressed();
                }
            }
        });


        return view;
    }
}