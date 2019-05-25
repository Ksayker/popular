package ksayker.popular.fragment;

import com.arellomobile.mvp.InjectViewState;

import java.util.List;

import io.reactivex.Single;
import ksayker.domain.entities.Article;
import ksayker.domain.interactor.ArticleInteractor;

/**
 * @author Volchenko Yura
 * @since 25.05.19
 */
@InjectViewState
public class MostViewedPresenter extends ListPresenter {

    public MostViewedPresenter(ArticleInteractor interactor) {
        super(interactor);
    }

    @Override
    protected Single<List<Article>> loadArticles() {
        return (Single<List<Article>>)(Single<?>) interactor.getMostViewedArticles();
    }
}
