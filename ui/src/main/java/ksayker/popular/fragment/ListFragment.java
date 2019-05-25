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
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ksayker.domain.entities.Article;
import ksayker.popular.R;
import ksayker.popular.adapter.ListAdapter;
import ksayker.popular.databinding.FragmentListBinding;

/**
 * @author Volchenko Yura
 * @since 24.05.19
 */
public class ListFragment extends MvpAppCompatFragment implements ListView {

    @InjectPresenter
    ListPresenter presenter;

    ListAdapter adapter;

    private Dialog errorDialog;

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentListBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false);

        initView(binding);

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.init(getContext());
        presenter.requestArticle();
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

        adapter = new ListAdapter(getContext(), Collections.emptyList());

        binding.rvListFragmentList.setAdapter(adapter);
    }

    @Override
    public void showMessage(boolean show, @StringRes int titleId, @StringRes int messageId) {
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

    @Override
    public void showArticles(List<Article> articles) {
        adapter.setItems(articles);
    }
}
