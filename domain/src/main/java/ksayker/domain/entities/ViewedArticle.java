package ksayker.domain.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Volchenko Yura
 * @since 25.05.19
 */
public class ViewedArticle extends Article {
    @SerializedName("views")
    @Expose
    private int viewsCount ;

    public int getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(int viewsCount) {
        this.viewsCount = viewsCount;
    }

    @Override
    public int getType() {
        return TYPE_VIEWED_ARTICLE;
    }
}
