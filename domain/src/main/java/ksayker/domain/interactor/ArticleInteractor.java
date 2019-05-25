package ksayker.domain.interactor;

import java.util.List;

import io.reactivex.Single;
import ksayker.domain.entities.EmailedArticle;
import ksayker.domain.entities.SharedArticle;
import ksayker.domain.entities.ViewedArticle;
import ksayker.domain.repository.ArticleRepository;

/**
 * @author Volchenko Yura
 * @since 24.05.19
 */
public class ArticleInteractor {
    private ArticleRepository repository;

    public ArticleInteractor(ArticleRepository repository) {
        this.repository = repository;
    }

    public Single<List<EmailedArticle>> getMostEmailedArticles() {
        return repository.getMostEmailed();
    }

    public Single<List<SharedArticle>> getMostSharedArticles() {
        return repository.getMostShared();
    }

    public Single<List<ViewedArticle>> getMostViewedArticles() {
        return repository.getMostViewed();
    }
}
