package ksayker.popular.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Volchenko Yura
 * @since 24.05.19
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {
    private List<String> fragmentTitles;
    private List<Fragment> fragments;

    public TabsPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);

        fragments = new ArrayList<>();
        fragmentTitles = new ArrayList<>();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitles.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    public void addFragment(Fragment fragment, String tabTitle) {
        fragments.add(fragment);
        fragmentTitles.add(tabTitle);
    }
}
