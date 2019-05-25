package ksayker.popular.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ksayker.popular.R;
import ksayker.popular.adapter.TabsPagerAdapter;
import ksayker.popular.databinding.FragmentTabsBinding;

/**
 * @author Volchenko Yura
 * @since 24.05.19
 */
public class TabsFragment extends Fragment {

    public static TabsFragment newInstance() {
        return new TabsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentTabsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tabs, container, false);

        initView(binding);

        return binding.getRoot();
    }

    private void initView(FragmentTabsBinding fragmentBinding) {
        fragmentBinding.tbMainActivityToolbar.setTitle(R.string.app_name);
        fragmentBinding.tbMainActivityToolbar.setTitleTextColor(ContextCompat.getColor(getContext(), R.color.mainActivity_title));

        ViewPager viewPager = fragmentBinding.vpMainActivityPager;
        TabLayout tlTabs = fragmentBinding.tlMainActivityTabs;

        TabsPagerAdapter pagerAdapter = new TabsPagerAdapter(getChildFragmentManager());
        pagerAdapter.addFragment(ListFragment.newInstance(), getString(R.string.title_mainFragment_mostEmailed));
//        pagerAdapter.addFragment(ListFragment.newInstance(), getString(R.string.title_mainFragment_mostShared));
//        pagerAdapter.addFragment(ListFragment.newInstance(), getString(R.string.title_mainFragment_mostViewed));

        viewPager.setAdapter(pagerAdapter);
        tlTabs.setupWithViewPager(viewPager);
    }
}
