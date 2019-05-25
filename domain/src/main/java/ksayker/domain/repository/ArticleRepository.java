package ksayker.domain.repository;

import java.util.List;

import io.reactivex.Single;
import ksayker.domain.entities.Article;

/**
 * @author Volchenko Yura
 * @since 24.05.19
 */
public interface ArticleRepository {
    Single<List<Article>> getMostEmailed();
}
