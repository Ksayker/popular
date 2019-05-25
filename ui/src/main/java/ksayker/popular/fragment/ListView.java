package ksayker.popular.fragment;

import android.support.annotation.StringRes;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import ksayker.domain.entities.Article;

/**
 * @author Volchenko Yura
 * @since 24.05.19
 */
public interface ListView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void showMessage(boolean show, @StringRes int titleId, @StringRes int messageId);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showArticles(List<Article> articles);

    @StateStrategyType(SkipStrategy.class)
    void notifyDataSetChanged();
}
