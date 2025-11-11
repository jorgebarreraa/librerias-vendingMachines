package com.yj.coffeemachines.mvp.ui.fragment;

import com.jess.arms.base.BaseFragment_MembersInjector;
import com.yj.coffeemachines.mvp.presenter.Ops4_FeatureSettingPresenter;
import dagger.MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Ops4_FeatureSettingFragment_MembersInjector implements MembersInjector<Ops4_FeatureSettingFragment> {
    private final Provider<Ops4_FeatureSettingPresenter> mPresenterProvider;

    public Ops4_FeatureSettingFragment_MembersInjector(Provider<Ops4_FeatureSettingPresenter> provider) {
        this.mPresenterProvider = provider;
    }

    public static MembersInjector<Ops4_FeatureSettingFragment> create(Provider<Ops4_FeatureSettingPresenter> provider) {
        return new Ops4_FeatureSettingFragment_MembersInjector(provider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Ops4_FeatureSettingFragment ops4_FeatureSettingFragment) {
        BaseFragment_MembersInjector.injectMPresenter(ops4_FeatureSettingFragment, this.mPresenterProvider.get());
    }
}
