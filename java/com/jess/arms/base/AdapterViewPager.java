package com.jess.arms.base;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import java.util.List;

/* loaded from: classes.dex */
public class AdapterViewPager extends FragmentStatePagerAdapter {
    private List<Fragment> mList;
    private CharSequence[] mTitles;

    public AdapterViewPager(FragmentManager fragmentManager, List<Fragment> list) {
        super(fragmentManager);
        this.mList = list;
    }

    public AdapterViewPager(FragmentManager fragmentManager, List<Fragment> list, CharSequence[] charSequenceArr) {
        super(fragmentManager);
        this.mList = list;
        this.mTitles = charSequenceArr;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.mList.size();
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter
    public Fragment getItem(int i) {
        return this.mList.get(i);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public CharSequence getPageTitle(int i) {
        CharSequence[] charSequenceArr = this.mTitles;
        return (charSequenceArr == null || i >= charSequenceArr.length) ? super.getPageTitle(i) : charSequenceArr[i];
    }
}
