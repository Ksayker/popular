package ksayker.popular.fragment;

import com.arellomobile.mvp.InjectViewState;

import java.util.List;

import io.reactivex.Single;
import ksayker.domain.entities.Article;

/**
 * @author Volchenko Yura
 * @since 25.05.19
 */
@InjectViewState
public class MostEmailedPresenter extends ListPresenter {

    @Override
    protected Single<List<Article>> getArticles() {
        return (Single<List<Article>>)(Single<?>) interactor.getMostEmailedArticles();
    }
}
