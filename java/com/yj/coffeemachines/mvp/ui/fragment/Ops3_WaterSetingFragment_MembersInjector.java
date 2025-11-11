package com.yj.coffeemachines.mvp.ui.fragment;

import com.jess.arms.base.BaseFragment_MembersInjector;
import com.yj.coffeemachines.mvp.presenter.Ops3_WaterSetingPresenter;
import dagger.MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Ops3_WaterSetingFragment_MembersInjector implements MembersInjector<Ops3_WaterSetingFragment> {
    private final Provider<Ops3_WaterSetingPresenter> mPresenterProvider;

    public Ops3_WaterSetingFragment_MembersInjector(Provider<Ops3_WaterSetingPresenter> provider) {
        this.mPresenterProvider = provider;
    }

    public static MembersInjector<Ops3_WaterSetingFragment> create(Provider<Ops3_WaterSetingPresenter> provider) {
        return new Ops3_WaterSetingFragment_MembersInjector(provider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Ops3_WaterSetingFragment ops3_WaterSetingFragment) {
        BaseFragment_MembersInjector.injectMPresenter(ops3_WaterSetingFragment, this.mPresenterProvider.get());
    }
}
