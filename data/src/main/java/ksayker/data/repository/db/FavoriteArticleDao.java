package ksayker.data.repository.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ksayker.data.repository.db.tables.TableFavoriteArticle;
import ksayker.domain.entities.Article;
import ksayker.domain.entities.BaseArticle;
import ksayker.domain.entities.EmailedArticle;
import ksayker.domain.entities.SharedArticle;
import ksayker.domain.entities.ViewedArticle;

/**
 * @author Volchenko Yura
 * @since 25.05.19
 */
public class FavoriteArticleDao {
    private DB db;

    public FavoriteArticleDao(DB db) {
        this.db = db;
    }

    private ContentValues articleToCv(Article article) {
        ContentValues cv = new ContentValues();

        cv.put(TableFavoriteArticle.SERVER_ID, String.valueOf(article.getServerId()));
        cv.put(TableFavoriteArticle.TITLE, article.getTitle());
        cv.put(TableFavoriteArticle.URL, article.getUrl());
        cv.put(TableFavoriteArticle.TYPE, article.getType());

        return cv;
    }
    private Article cursorToArticle(Cursor cursor) {
        Article article;
        int type = cursor.getInt(cursor.getColumnIndex(TableFavoriteArticle.SERVER_ID));
        if (type == BaseArticle.TYPE_EMAILED_ARTICLE) {
            article = new EmailedArticle();
        } else if (type == BaseArticle.TYPE_SHARED_ARTICLE) {
            article = new SharedArticle();
        } else {
            article = new ViewedArticle();
        }

        int serverIdColumn = cursor.getColumnIndex(TableFavoriteArticle.SERVER_ID);
        if (!cursor.isNull(serverIdColumn)) {
            article.setServerId(Long.parseLong(cursor.getString(serverIdColumn)));
        }
        int titleColumn = cursor.getColumnIndex(TableFavoriteArticle.TITLE);
        if (!cursor.isNull(titleColumn)) {
            article.setTitle(cursor.getString(titleColumn));
        }
        int urlColumn = cursor.getColumnIndex(TableFavoriteArticle.URL);
        if (!cursor.isNull(urlColumn)) {
            article.setUrl(cursor.getString(urlColumn));
        }

        return article;
    }

    public void addToFavorite(Article article) {
        SQLiteDatabase database = db.getWritableDatabase();
        ContentValues cv = articleToCv(article);

        if (database.update(TableFavoriteArticle.TABLE_NAME, cv, TableFavoriteArticle.SERVER_ID + "='" + article.getServerId() + "'", null) != 1) {
            database.insert(TableFavoriteArticle.TABLE_NAME, null, cv);
        }
    }

    public void removeFromFavorite(Article article) {
        SQLiteDatabase database = db.getWritableDatabase();

        database.delete(TableFavoriteArticle.TABLE_NAME, TableFavoriteArticle.SERVER_ID + "='" + article.getServerId() + "'", null);
    }

    public List<Article> getFavoriteArticles() {
        SQLiteDatabase database = db.getReadableDatabase();
        List<Article> articles = new ArrayList<>();
        Cursor cursor = database.query(TableFavoriteArticle.TABLE_NAME, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Article article = cursorToArticle(cursor);
            articles.add(article);
        }
        cursor.close();

        return articles;
    }

    public boolean checkFavorite(Article article) {
        boolean result;
        SQLiteDatabase database = db.getReadableDatabase();
        String where = TableFavoriteArticle.SERVER_ID + "='" + article.getServerId() + "'";
        Cursor cursor = database.query(TableFavoriteArticle.TABLE_NAME, null, where, null, null, null, null);

        result = cursor.moveToFirst();
        cursor.close();

        return result;
    }

    public Article getFavoriteArticle(long articleServerId) {
        SQLiteDatabase database = db.getReadableDatabase();
        String where = TableFavoriteArticle.SERVER_ID + "='" + articleServerId + "'";
        Cursor cursor = database.query(TableFavoriteArticle.TABLE_NAME, null, where, null, null, null, null);
        Article article = Article.NONE;
        if (cursor.moveToFirst()) {
            article = cursorToArticle(cursor);
        }
        cursor.close();

        return article;
    }
}
