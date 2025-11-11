package com.jess.arms.base;

import androidx.fragment.app.Fragment;
import com.jess.arms.mvp.IPresenter;
import java.util.List;
import javax.inject.Inject;

/* loaded from: classes.dex */
public abstract class BaseLazyLoadFragment<P extends IPresenter> extends BaseFragment<P> {
    private boolean isDataLoaded;
    private boolean isViewCreated;
    private boolean isVisibleToUser;

    @Inject
    Unused mUnused;

    private void dispatchParentVisibleState() {
        List<Fragment> fragments = getChildFragmentManager().getFragments();
        if (fragments.isEmpty()) {
            return;
        }
        for (Fragment fragment : fragments) {
            if (fragment instanceof BaseLazyLoadFragment) {
                BaseLazyLoadFragment baseLazyLoadFragment = (BaseLazyLoadFragment) fragment;
                if (baseLazyLoadFragment.isVisibleToUser) {
                    baseLazyLoadFragment.tryLoadData();
                }
            }
        }
    }

    private boolean isParentVisible() {
        Fragment parentFragment = getParentFragment();
        return parentFragment == null || ((parentFragment instanceof BaseLazyLoadFragment) && ((BaseLazyLoadFragment) parentFragment).isVisibleToUser);
    }

    protected abstract void lazyLoadData();

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.isViewCreated = true;
        tryLoadData();
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.isVisibleToUser = z;
        tryLoadData();
    }

    public void tryLoadData() {
        if (this.isViewCreated && this.isVisibleToUser && isParentVisible() && !this.isDataLoaded) {
            lazyLoadData();
            this.isDataLoaded = true;
            dispatchParentVisibleState();
        }
    }
}
