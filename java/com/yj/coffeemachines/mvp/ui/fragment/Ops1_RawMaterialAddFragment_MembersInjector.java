package com.yj.coffeemachines.mvp.ui.fragment;

import com.jess.arms.base.BaseFragment_MembersInjector;
import com.yj.coffeemachines.mvp.presenter.Ops1_RawMaterialAddPresenter;
import dagger.MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Ops1_RawMaterialAddFragment_MembersInjector implements MembersInjector<Ops1_RawMaterialAddFragment> {
    private final Provider<Ops1_RawMaterialAddPresenter> mPresenterProvider;

    public Ops1_RawMaterialAddFragment_MembersInjector(Provider<Ops1_RawMaterialAddPresenter> provider) {
        this.mPresenterProvider = provider;
    }

    public static MembersInjector<Ops1_RawMaterialAddFragment> create(Provider<Ops1_RawMaterialAddPresenter> provider) {
        return new Ops1_RawMaterialAddFragment_MembersInjector(provider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Ops1_RawMaterialAddFragment ops1_RawMaterialAddFragment) {
        BaseFragment_MembersInjector.injectMPresenter(ops1_RawMaterialAddFragment, this.mPresenterProvider.get());
    }
}
