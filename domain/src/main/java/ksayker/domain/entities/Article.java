package ksayker.domain.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Volchenko Yura
 * @since 24.05.19
 */
public abstract class Article implements BaseArticle, Serializable {
    public static Article NONE = new Article() {
        @Override
        public int getType() {
            return 0;
        }
    };

    @SerializedName("id")
    @Expose
    private long serverId;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("title")
    @Expose
    private String title;

    private boolean isFavorite = false;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public long getServerId() {
        return serverId;
    }

    public void setServerId(long id) {
        this.serverId = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean isFavorite() {
        return isFavorite;
    }

    @Override
    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
