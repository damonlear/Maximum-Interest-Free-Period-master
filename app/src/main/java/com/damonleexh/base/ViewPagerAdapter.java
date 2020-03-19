package com.damonleexh.base;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter {
    private String[] mTitleArray;
    private List<Fragment> mFragments;
    private FragmentActivity activity;


    public ViewPagerAdapter(@NonNull FragmentActivity activity, String[] titleArray, List<Fragment> mFragments) {
        super(activity);

        this.activity = activity;
        this.mTitleArray = titleArray;
        this.mFragments = mFragments;
        if (mTitleArray.length != mFragments.size()) {
            throw new ArrayIndexOutOfBoundsException("mTitleArray size != mFragmentList length");
        }
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getItemCount() {
        return mFragments.size();
    }

    public void setFragmentList(List<Fragment> fragments) {
        if (this.mFragments != null) {
            FragmentManager fm = activity.getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            for (Fragment f : this.mFragments) {
                ft.remove(f);
            }
            ft.commit();
            ft = null;
            fm.executePendingTransactions();
        }
        this.mFragments = fragments;
        notifyDataSetChanged();
    }


}


