package ksayker.domain.entities;

/**
 * @author Volchenko Yura
 * @since 25.05.19
 */
public interface BaseArticle {
    int TYPE_EMAILED_ARTICLE = 1;
    int TYPE_SHARED_ARTICLE = 2;
    int TYPE_VIEWED_ARTICLE = 3;

    int getType();
}
