package ksayker.data.repository.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import ksayker.data.repository.db.tables.TableFavoriteArticle;

/**
 * @author Volchenko Yura
 * @since 25.05.19
 */
public class DB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MostPopular";
    private static final int DATABASE_VERSION = 1;

    private TableFavoriteArticle tableFavoriteArticle;

    public DB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        tableFavoriteArticle = new TableFavoriteArticle();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        tableFavoriteArticle.createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
