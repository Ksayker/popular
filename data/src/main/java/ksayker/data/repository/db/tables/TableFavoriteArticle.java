package ksayker.data.repository.db.tables;

import android.database.sqlite.SQLiteDatabase;

/**
 * @author Volchenko Yura
 * @since 25.05.19
 */
public class TableFavoriteArticle {
    public static final String TABLE_NAME = "favorite_article";

    public static final String ID = "id_";
    public static final String SERVER_ID = "id";
    public static final String TITLE = "title";
    public static final String URL = "url";
    public static final String TYPE = "type";

    public void createTable(SQLiteDatabase database) {
        database.execSQL("create table "
                + TABLE_NAME
                + "("
                + ID + " integer primary key autoincrement, "
                + SERVER_ID + " text,"
                + TITLE + " text,"
                + URL + " text,"
                + TYPE + " integer"
                + ");");
    }
}
