package ksayker.popular.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.annimon.stream.Stream;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Volchenko Yura
 * @since 24.05.19
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {
    private List<String> fragmentTitles;
    private List<Fragment> fragments;

    private int currentPosition = -1;
    private List<Runnable> pendingOperations;

    public TabsPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);

        fragments = new ArrayList<>();
        fragmentTitles = new ArrayList<>();
        pendingOperations = new ArrayList<>();
    }

    private void notifyOnFlip(Fragment fragment, boolean isFlippedTo) {
        if (fragment instanceof FlippableFragment) {
            Runnable command = () -> {
                FlippableFragment flippable = (FlippableFragment) fragment;
                if (isFlippedTo) {
                    flippable.onPageFlipTo();
                } else {
                    flippable.onPageFlipFrom();
                }
            };
            if (fragment.isAdded()) {
                command.run();
            } else {
                pendingOperations.add(command);
            }
        }
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (currentPosition != position) {
            if (currentPosition >= 0) {
                // notify fragment we flipped from
                Fragment previousFragment = fragments.get(currentPosition);
                notifyOnFlip(previousFragment, false);
            }
            // notify fragment we flipped to
            Fragment currentFragment = (Fragment) object;
            notifyOnFlip(currentFragment, true);

            currentPosition = position;
        }
        super.setPrimaryItem(container, position, object);
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        super.finishUpdate(container);
        Stream.of(pendingOperations)
                .forEach(Runnable::run);
        pendingOperations.clear();
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

    public interface FlippableFragment {
        void onPageFlipTo();

        void onPageFlipFrom();
    }
}
