package ksayker.popular.fragment;

import android.util.Pair;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import ksayker.domain.entities.Article;
import ksayker.domain.interactor.ArticleInteractor;
import ksayker.popular.R;

/**
 * @author Volchenko Yura
 * @since 24.05.19
 */
@InjectViewState
abstract public class ListPresenter extends MvpPresenter<ListView> {
    private CompositeDisposable disposables = new CompositeDisposable();

    protected List<Article> articles = Collections.emptyList();

    protected ArticleInteractor interactor;

    protected abstract Single<List<Article>> loadArticles();

    public ListPresenter(ArticleInteractor interactor) {
        this.interactor = interactor;
    }

    public void requestArticle() {
        if (articles.isEmpty()) {
            disposables.add(loadArticles()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe((articles, throwable) -> {
                        if (throwable == null) {
                            this.articles = articles;

                            getViewState().showArticles(articles);
                        } else {
                            getViewState().showMessage(true, R.string.title_listFragment_internetError,
                                    R.string.message_listFragment_internetError);
                        }
                    }));
        }
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

    public void checkFavorite() {
        Observable.fromIterable(articles)
                .flatMap(article -> Observable.zip(
                        Observable.just(article), interactor.checkFavorite(article).toObservable(), Pair::new))
                .doOnNext(pair -> pair.first.setFavorite(pair.second))
                .toList()
                .doAfterSuccess(pairs -> getViewState().notifyDataSetChanged())
                .subscribe();
    }
}
