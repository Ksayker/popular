package ksayker.domain.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Volchenko Yura
 * @since 25.05.19
 */
public class EmailedArticle extends Article {
    @SerializedName("email_count")
    @Expose
    private int emailCount;

    public int getEmailCount() {
        return emailCount;
    }

    public void setEmailCount(int emailCount) {
        this.emailCount = emailCount;
    }
}
