package ksayker.data.repository;

import android.content.Context;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import ksayker.data.repository.db.ArticleDao;
import ksayker.data.repository.db.DB;
import ksayker.data.repository.rest.Rest;
import ksayker.domain.entities.Article;
import ksayker.domain.entities.EmailedArticle;
import ksayker.domain.entities.SharedArticle;
import ksayker.domain.entities.ViewedArticle;
import ksayker.domain.repository.ArticleRepository;

/**
 * @author Volchenko Yura
 * @since 24.05.19
 */
public class ArticleRepositoryImpl implements ArticleRepository {
    private Context appContext;
    private Rest rest;
    private ArticleDao articleDao;

    public ArticleRepositoryImpl(Context context) {
        appContext = context.getApplicationContext();
        rest = new Rest();
        articleDao = new ArticleDao(new DB(appContext));
    }

    @Override
    public Single<List<EmailedArticle>> getMostEmailed() {
        return rest.getMostEmailed();
    }

    @Override
    public Single<List<SharedArticle>> getMostShared() {
        return rest.getMostShared();
    }

    @Override
    public Single<List<ViewedArticle>> getMostViewed() {
        return rest.getMostViewed();
    }

    @Override
    public Completable addToFavorite(Article article) {
        return Completable.fromAction(() -> articleDao.addToFavorite(article));
    }

    @Override
    public Completable removeFromFavorite(Article article) {
        return Completable.fromAction(() -> articleDao.removeFromFavorite(article));
    }
}
