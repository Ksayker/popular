package ksayker.popular.fragment;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import ksayker.data.repository.ArticleRepositoryImpl;
import ksayker.domain.entities.Article;
import ksayker.domain.entities.EmailedArticle;
import ksayker.domain.interactor.ArticleInteractor;
import ksayker.popular.R;

/**
 * @author Volchenko Yura
 * @since 24.05.19
 */
@InjectViewState
abstract public class ListPresenter extends MvpPresenter<ListView> {
    // TODO: 24.05.19 remove context
    private Context context;
    private CompositeDisposable disposables = new CompositeDisposable();

    protected ArticleInteractor interactor;

    abstract protected Single<List<Article>> getArticles();

    public void init(Context context) {
        this.context = context;
        interactor = new ArticleInteractor(new ArticleRepositoryImpl(context));
    }

    // TODO: 24.05.19 refactor
    public void requestArticle() {
        disposables.add(getArticles()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((articles, throwable) -> {
                    if (throwable == null) {
                        getViewState().showArticles(articles);
                    } else {
                        getViewState().showMessage(true, R.string.title_listFragment_internetError,
                                R.string.message_listFragment_internetError);
                    }
                }));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposables.dispose();
    }

    public void hideMessage() {
        getViewState().showMessage(false, 0, 0);
    }

    public void addToFavorite(Article article) {
        interactor.addToFavorite(article)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    public void removeFromFavorite(Article article) {
        interactor.removeFromFavorite(article)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe();
    }
}
