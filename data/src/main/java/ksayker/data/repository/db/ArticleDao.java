package ksayker.data.repository.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import ksayker.data.repository.db.tables.TableArticle;
import ksayker.domain.entities.Article;

/**
 * @author Volchenko Yura
 * @since 25.05.19
 */
public class ArticleDao {
    private DB db;

    public ArticleDao(DB db) {
        this.db = db;
    }

    private ContentValues articleToCv(Article article) {
        ContentValues cv = new ContentValues();

        cv.put(TableArticle.SERVER_ID, article.getServerId());
        cv.put(TableArticle.TITLE, article.getTitle());
        cv.put(TableArticle.URL, article.getUrl());
        cv.put(TableArticle.TYPE, article.getType());

        return cv;
    }

    public void addToFavorite(Article article) {
        SQLiteDatabase database = db.getWritableDatabase();
        ContentValues cv = articleToCv(article);

        if (database.update(TableArticle.TABLE_NAME, cv, TableArticle.SERVER_ID + "=" + article.getServerId(), null) != 1) {
            database.insert(TableArticle.TABLE_NAME, null, cv);
        }
    }

    public void removeFromFavorite(Article article) {
        SQLiteDatabase database = db.getWritableDatabase();

        database.delete(TableArticle.TABLE_NAME, TableArticle.SERVER_ID + "=" + article.getServerId(), null);
    }
}
