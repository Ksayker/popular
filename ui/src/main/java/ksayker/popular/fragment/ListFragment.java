package ksayker.popular.fragment;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.Collections;
import java.util.List;

import ksayker.data.repository.ArticleRepositoryImpl;
import ksayker.domain.entities.Article;
import ksayker.domain.interactor.ArticleInteractor;
import ksayker.popular.R;
import ksayker.popular.adapter.ListAdapter;
import ksayker.popular.adapter.TabsPagerAdapter;
import ksayker.popular.databinding.FragmentListBinding;

/**
 * @author Volchenko Yura
 * @since 24.05.19
 */
public class ListFragment extends MvpAppCompatFragment implements ListView, ListAdapter.OnAddToFavoriteListener,
        TabsPagerAdapter.FlippableFragment {
    private static final String MODE_ARG = "MODE_ARG";
    private static final String MODE_MOST_EMAILED = "MODE_MOST_EMAILED";
    private static final String MODE_MOST_SHARED = "MODE_MOST_SHARED";
    private static final String MODE_MOST_VIEWED = "MODE_MOST_VIEWED";
    private static final String MODE_FAVORITE = "MODE_FAVORITE";


    @InjectPresenter
    ListPresenter presenter;

    ListAdapter adapter;

    private Dialog errorDialog;

    public static ListFragment newInstance(Mode mode) {
        Bundle args = new Bundle();
        ListFragment fragment = new ListFragment();
        fragment.setArguments(args);

        if (mode == Mode.MODE_MOST_EMAILED) {
            args.putString(MODE_ARG, MODE_MOST_EMAILED);
        } else if (mode == Mode.MODE_MOST_SHARED) {
            args.putString(MODE_ARG, MODE_MOST_SHARED);
        } else if (mode == Mode.MODE_MOST_VIEWED) {
            args.putString(MODE_ARG, MODE_MOST_VIEWED);
        } else if (mode == Mode.MODE_FAVORITE) {
            args.putString(MODE_ARG, MODE_FAVORITE);
        } else {
            args.putString(MODE_ARG, MODE_MOST_EMAILED);
        }

        return fragment;
    }

    @ProvidePresenter
    ListPresenter provideListPresenter() {
        Bundle args = getArguments();
        ListPresenter presenter;
        String mode = args.getString(MODE_ARG);

        ArticleInteractor interactor = new ArticleInteractor(new ArticleRepositoryImpl(getContext()));
        switch (mode) {
            default:
            case MODE_MOST_EMAILED:
                presenter = new MostEmailedPresenter(interactor);
                break;
            case MODE_MOST_SHARED:
                presenter = new MostSharedPresenter(interactor);
                break;
            case MODE_MOST_VIEWED:
                presenter = new MostViewedPresenter(interactor);
                break;
            case MODE_FAVORITE:
                presenter = new FavoritePresenter(interactor);
                break;
        }

        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentListBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false);

        initView(binding);

        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (errorDialog != null) {
            errorDialog.setOnDismissListener(null);
            errorDialog.dismiss();
        }
    }

    private void initView(FragmentListBinding binding) {
        binding.rvListFragmentList.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new ListAdapter(getContext(), Collections.emptyList(), this);

        binding.rvListFragmentList.setAdapter(adapter);
    }

    @Override
    public void showMessage(boolean show, @StringRes int titleId, @StringRes int messageId) {
        if (isVisible()) {
            if (show) {
                errorDialog = new AlertDialog.Builder(getContext())
                        .setTitle(titleId)
                        .setMessage(messageId)
                        .setPositiveButton(R.string.all_ok, (dialog, which) -> dialog.dismiss())
                        .setOnDismissListener(dialog -> presenter.hideMessage())
                        .show();
            } else {
                if (errorDialog != null) {
                    errorDialog.dismiss();
                    errorDialog = null;
                }
            }
        }
    }

    @Override
    public void showArticles(List<Article> articles) {
        adapter.setItems(articles);
    }

    @Override
    public void notifyDataSetChanged() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void addToFavorite(Article article) {
        presenter.addToFavorite(article);
    }

    @Override
    public void removeFromFavorite(Article article) {
        presenter.removeFromFavorite(article);
    }

    @Override
    public void onPageFlipTo() {
        presenter.checkFavorite();
        presenter.requestArticle();
    }

    @Override
    public void onPageFlipFrom() {
    }

    public enum Mode {
        MODE_MOST_EMAILED,
        MODE_MOST_SHARED,
        MODE_MOST_VIEWED,
        MODE_FAVORITE
    }
}
