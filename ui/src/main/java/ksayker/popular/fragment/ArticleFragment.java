package ksayker.popular.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import ksayker.domain.entities.Article;
import ksayker.popular.R;
import ksayker.popular.databinding.FragmentArticleBinding;

/**
 * @author Volchenko Yura
 * @since 26.05.19
 */
public class ArticleFragment extends Fragment {
    private static final String ARG_ARTICLE = "ARG_ARTICLE";

    public static ArticleFragment newInstance(Article article) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_ARTICLE, article);

        ArticleFragment fragment = new ArticleFragment ();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentArticleBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_article, container, false);

        initView(binding);

        return binding.getRoot();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean result;
        switch (item.getItemId()){
            case android.R.id.home:
                getActivity().onBackPressed();
                result = true;
                break;
            default:
                result = super.onOptionsItemSelected(item);
                break;
        }

        return result;
    }

    private void initView(FragmentArticleBinding binding) {
        Article article = (Article) getArguments().getSerializable(ARG_ARTICLE);

        if (article != null) {
            binding.tvArticleFragmentTitle.setText(article.getTitle());
            binding.tvArticleFragmentUrl.setText(article.getUrl());
        }

        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.tbMainActivityToolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setHomeAsUpIndicator(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
