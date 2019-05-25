package ksayker.domain.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Volchenko Yura
 * @since 25.05.19
 */
public class SharedArticle extends Article {
    @SerializedName("share_count")
    @Expose
    private int shareCount ;

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    @Override
    public int getType() {
        return TYPE_SHARED_ARTICLE;
    }
}
