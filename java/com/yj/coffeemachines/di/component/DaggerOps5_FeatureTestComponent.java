package com.yj.coffeemachines.di.component;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.base.BaseFragment_MembersInjector;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.integration.IRepositoryManager;
import com.yj.coffeemachines.di.component.Ops5_FeatureTestComponent;
import com.yj.coffeemachines.mvp.contract.Ops5_FeatureTestContract;
import com.yj.coffeemachines.mvp.model.Ops5_FeatureTestModel;
import com.yj.coffeemachines.mvp.model.Ops5_FeatureTestModel_Factory;
import com.yj.coffeemachines.mvp.presenter.Ops5_FeatureTestPresenter;
import com.yj.coffeemachines.mvp.presenter.Ops5_FeatureTestPresenter_Factory;
import com.yj.coffeemachines.mvp.ui.fragment.Ops5_FeatureTestFragment;
import dagger.internal.DoubleCheck;
import dagger.internal.InstanceFactory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class DaggerOps5_FeatureTestComponent implements Ops5_FeatureTestComponent {
    private Provider<AppManager> appManagerProvider;
    private Provider<Application> applicationProvider;
    private Provider<Gson> gsonProvider;
    private Provider<ImageLoader> imageLoaderProvider;
    private Provider<Ops5_FeatureTestModel> ops5_FeatureTestModelProvider;
    private Provider<Ops5_FeatureTestPresenter> ops5_FeatureTestPresenterProvider;
    private Provider<IRepositoryManager> repositoryManagerProvider;
    private Provider<RxErrorHandler> rxErrorHandlerProvider;
    private Provider<Ops5_FeatureTestContract.View> viewProvider;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class Builder implements Ops5_FeatureTestComponent.Builder {
        private AppComponent appComponent;
        private Ops5_FeatureTestContract.View view;

        private Builder() {
        }

        @Override // com.yj.coffeemachines.di.component.Ops5_FeatureTestComponent.Builder
        public Builder appComponent(AppComponent appComponent) {
            this.appComponent = (AppComponent) Preconditions.checkNotNull(appComponent);
            return this;
        }

        @Override // com.yj.coffeemachines.di.component.Ops5_FeatureTestComponent.Builder
        public Ops5_FeatureTestComponent build() {
            Preconditions.checkBuilderRequirement(this.view, Ops5_FeatureTestContract.View.class);
            Preconditions.checkBuilderRequirement(this.appComponent, AppComponent.class);
            return new DaggerOps5_FeatureTestComponent(this.appComponent, this.view);
        }

        @Override // com.yj.coffeemachines.di.component.Ops5_FeatureTestComponent.Builder
        public Builder view(Ops5_FeatureTestContract.View view) {
            this.view = (Ops5_FeatureTestContract.View) Preconditions.checkNotNull(view);
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class com_jess_arms_di_component_AppComponent_appManager implements Provider<AppManager> {
        private final AppComponent appComponent;

        com_jess_arms_di_component_AppComponent_appManager(AppComponent appComponent) {
            this.appComponent = appComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        public AppManager get() {
            return (AppManager) Preconditions.checkNotNull(this.appComponent.appManager(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class com_jess_arms_di_component_AppComponent_application implements Provider<Application> {
        private final AppComponent appComponent;

        com_jess_arms_di_component_AppComponent_application(AppComponent appComponent) {
            this.appComponent = appComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        public Application get() {
            return (Application) Preconditions.checkNotNull(this.appComponent.application(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class com_jess_arms_di_component_AppComponent_gson implements Provider<Gson> {
        private final AppComponent appComponent;

        com_jess_arms_di_component_AppComponent_gson(AppComponent appComponent) {
            this.appComponent = appComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        public Gson get() {
            return (Gson) Preconditions.checkNotNull(this.appComponent.gson(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class com_jess_arms_di_component_AppComponent_imageLoader implements Provider<ImageLoader> {
        private final AppComponent appComponent;

        com_jess_arms_di_component_AppComponent_imageLoader(AppComponent appComponent) {
            this.appComponent = appComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        public ImageLoader get() {
            return (ImageLoader) Preconditions.checkNotNull(this.appComponent.imageLoader(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class com_jess_arms_di_component_AppComponent_repositoryManager implements Provider<IRepositoryManager> {
        private final AppComponent appComponent;

        com_jess_arms_di_component_AppComponent_repositoryManager(AppComponent appComponent) {
            this.appComponent = appComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        public IRepositoryManager get() {
            return (IRepositoryManager) Preconditions.checkNotNull(this.appComponent.repositoryManager(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class com_jess_arms_di_component_AppComponent_rxErrorHandler implements Provider<RxErrorHandler> {
        private final AppComponent appComponent;

        com_jess_arms_di_component_AppComponent_rxErrorHandler(AppComponent appComponent) {
            this.appComponent = appComponent;
        }

        @Override // javax.inject.Provider
        public RxErrorHandler get() {
            return (RxErrorHandler) Preconditions.checkNotNull(this.appComponent.rxErrorHandler(), "Cannot return null from a non-@Nullable component method");
        }
    }

    private DaggerOps5_FeatureTestComponent(AppComponent appComponent, Ops5_FeatureTestContract.View view) {
        initialize(appComponent, view);
    }

    public static Ops5_FeatureTestComponent.Builder builder() {
        return new Builder();
    }

    private void initialize(AppComponent appComponent, Ops5_FeatureTestContract.View view) {
        this.repositoryManagerProvider = new com_jess_arms_di_component_AppComponent_repositoryManager(appComponent);
        this.gsonProvider = new com_jess_arms_di_component_AppComponent_gson(appComponent);
        this.applicationProvider = new com_jess_arms_di_component_AppComponent_application(appComponent);
        this.ops5_FeatureTestModelProvider = DoubleCheck.provider(Ops5_FeatureTestModel_Factory.create(this.repositoryManagerProvider, this.gsonProvider, this.applicationProvider));
        this.viewProvider = InstanceFactory.create(view);
        this.rxErrorHandlerProvider = new com_jess_arms_di_component_AppComponent_rxErrorHandler(appComponent);
        this.imageLoaderProvider = new com_jess_arms_di_component_AppComponent_imageLoader(appComponent);
        this.appManagerProvider = new com_jess_arms_di_component_AppComponent_appManager(appComponent);
        this.ops5_FeatureTestPresenterProvider = DoubleCheck.provider(Ops5_FeatureTestPresenter_Factory.create(this.ops5_FeatureTestModelProvider, this.viewProvider, this.rxErrorHandlerProvider, this.applicationProvider, this.imageLoaderProvider, this.appManagerProvider));
    }

    private Ops5_FeatureTestFragment injectOps5_FeatureTestFragment(Ops5_FeatureTestFragment ops5_FeatureTestFragment) {
        BaseFragment_MembersInjector.injectMPresenter(ops5_FeatureTestFragment, this.ops5_FeatureTestPresenterProvider.get());
        return ops5_FeatureTestFragment;
    }

    @Override // com.yj.coffeemachines.di.component.Ops5_FeatureTestComponent
    public void inject(Ops5_FeatureTestFragment ops5_FeatureTestFragment) {
        injectOps5_FeatureTestFragment(ops5_FeatureTestFragment);
    }
}
