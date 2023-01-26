package com.example.coffeecom.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.model.ArticleModel;
import com.example.coffeecom.query.QueryArticle;

public class CreateArticleFragment extends Fragment {

    ImageButton backBtn;
    TextView articleTitleTextBox, articleTypeTextBox, articlePicTextBox, articleContentTextBox, createArticleTitleText;
    Button articleCreateBtn;

    ArticleModel currentArticle;
    int currentArticleIndex;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_article, container, false);
        backBtn = view.findViewById(R.id.backBtn);
        backBtn.setOnClickListener(view1 -> getActivity().onBackPressed());
        articleTitleTextBox = view.findViewById(R.id.articleTitleTextBox);
        articleTypeTextBox = view.findViewById(R.id.articleTypeTextBox);
        articlePicTextBox = view.findViewById(R.id.articlePicTextBox);
        articleContentTextBox = view.findViewById(R.id.articleContentTextBox);
        articleCreateBtn = view.findViewById(R.id.articleCreateBtn);
        createArticleTitleText = view.findViewById(R.id.createArticleTitleText);

        Bundle bundle = this.getArguments();
        if(bundle != null){
            for (int i = 0; i < Provider.getArticles().size(); i++) {
                if(Provider.getArticles().get(i).getArticleId().equals(bundle.getString("articleId"))){
                    currentArticle = Provider.getArticles().get(i);
                    currentArticleIndex = i;
                }
            }
            createArticleTitleText.setText("Edit Article");
            articleCreateBtn.setText("Update");
        }

        articleTitleTextBox.setText(currentArticle.getArticleTitle());
        articleTypeTextBox.setText(currentArticle.getArticleType());
        articlePicTextBox.setText(currentArticle.getArticlePic());
        articleContentTextBox.setText(currentArticle.getArticleContent());

        articleCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = "" + articleTitleTextBox.getText();
                String type = "" + articleTypeTextBox.getText();
                String pic = "" + articlePicTextBox.getText();
                String content = "" + articleContentTextBox.getText();

                if(!title.equals("") && !type.equals("") && !pic.equals("") && !content.equals("")){
                    if(bundle == null) {
                        QueryArticle.addArticle(title, type, content, pic);
                        Toast.makeText(getContext(), "Create successful!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        QueryArticle.editArticle(currentArticle.getArticleId(), title, type, content, pic);
                        Provider.getArticles().get(currentArticleIndex).setArticleTitle(title);
                        Provider.getArticles().get(currentArticleIndex).setArticleType(type);
                        Provider.getArticles().get(currentArticleIndex).setArticlePic(pic);
                        Provider.getArticles().get(currentArticleIndex).setArticleContent(content);
                        Toast.makeText(getContext(), "Update successful!", Toast.LENGTH_SHORT).show();
                    }
                    getActivity().onBackPressed();
                }
            }
        });


        return view;
    }
}