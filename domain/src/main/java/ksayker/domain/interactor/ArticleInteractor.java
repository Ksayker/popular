package ksayker.domain.interactor;

import java.util.List;

import io.reactivex.Single;
import ksayker.domain.entities.Article;
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

    public Single<List<Article>> getMostEmailed() {
        return repository.getMostEmailed();
    }
}
