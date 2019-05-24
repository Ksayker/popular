package ksayker.popular.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ksayker.popular.R;
import ksayker.popular.adapter.ListAdapter;
import ksayker.popular.databinding.FragmentListBinding;

/**
 * @author Volchenko Yura
 * @since 24.05.19
 */
public class ListFragment extends Fragment {

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

    private void initView(FragmentListBinding binding) {
        binding.rvListFragmentList.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<String> items = new ArrayList<>();
        items.add("a123123123");
        items.add("b123123123");
        items.add("c123123123");
        items.add("d123123123");
        items.add("e123123123");
        items.add("f123123123");
        items.add("g123123123");
        items.add("h123123123");
        items.add("i123123123");
        items.add("g123123123");
        items.add("k123123123");
        items.add("k123123123");
        items.add("k123123123");
        items.add("k123123123");
        items.add("k123123123");
        items.add("k123123123");
        items.add("k123123123");
        items.add("k123123123");
        items.add("k123123123");
        items.add("k123123123");
        items.add("k123123123");
        items.add("k123123123");
        binding.rvListFragmentList.setAdapter(new ListAdapter(getContext(), items));
    }
}
