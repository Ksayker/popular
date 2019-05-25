package ksayker.domain.repository;

import java.util.List;

import io.reactivex.Single;
import ksayker.domain.entities.EmailedArticle;
import ksayker.domain.entities.SharedArticle;
import ksayker.domain.entities.ViewedArticle;

/**
 * @author Volchenko Yura
 * @since 24.05.19
 */
public interface ArticleRepository {
    Single<List<EmailedArticle>> getMostEmailed();

    Single<List<SharedArticle>> getMostShared();

    Single<List<ViewedArticle>> getMostViewed();
}
