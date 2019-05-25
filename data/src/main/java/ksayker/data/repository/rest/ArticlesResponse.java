package ksayker.data.repository.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import ksayker.domain.entities.Article;

/**
 * @author Volchenko Yura
 * @since 25.05.19
 */
public class ArticlesResponse<T extends Article> {
    @SerializedName("results")
    @Expose
    private List<T> articles;

    public List<T> getArticles() {
        return articles;
    }

    public void setArticles(List<T> articles) {
        this.articles = articles;
    }
}
