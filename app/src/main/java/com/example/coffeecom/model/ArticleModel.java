package com.example.coffeecom.model;

public class ArticleModel {
    private String articleId, adminId;
    private String articleTitle, articleType, articleContent, articlePic;
    private int articleUpVote, articleDownVote;

    //ArticleType gt general, history, beans, learn

    public ArticleModel(String articleId, String adminId, String articleTitle, String articleType, String articleContent, String articlePic, int articleUpVote, int articleDownVote) {
        this.articleId = articleId;
        this.adminId = adminId;
        this.articleTitle = articleTitle;
        this.articleType = articleType;
        this.articleContent = articleContent;
        this.articlePic = articlePic;
        this.articleUpVote = articleUpVote;
        this.articleDownVote = articleDownVote;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticlePic() {
        return articlePic;
    }

    public void setArticlePic(String articlePic) {
        this.articlePic = articlePic;
    }

    public int getArticleUpVote() {
        return articleUpVote;
    }

    public void setArticleUpVote(int articleUpVote) {
        this.articleUpVote = articleUpVote;
    }

    public int getArticleDownVote() {
        return articleDownVote;
    }

    public void setArticleDownVote(int articleDownVote) {
        this.articleDownVote = articleDownVote;
    }
}
