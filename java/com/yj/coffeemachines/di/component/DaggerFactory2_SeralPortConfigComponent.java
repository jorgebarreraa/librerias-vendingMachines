package com.yj.coffeemachines.di.component;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.base.BaseFragment_MembersInjector;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.integration.IRepositoryManager;
import com.yj.coffeemachines.di.component.Factory2_SeralPortConfigComponent;
import com.yj.coffeemachines.mvp.contract.Factory2_SeralPortConfigContract;
import com.yj.coffeemachines.mvp.model.Factory2_SeralPortConfigModel;
import com.yj.coffeemachines.mvp.model.Factory2_SeralPortConfigModel_Factory;
import com.yj.coffeemachines.mvp.presenter.Factory2_SeralPortConfigPresenter;
import com.yj.coffeemachines.mvp.presenter.Factory2_SeralPortConfigPresenter_Factory;
import com.yj.coffeemachines.mvp.ui.fragment.Factory2_SeralPortConfigFragment;
import dagger.internal.DoubleCheck;
import dagger.internal.InstanceFactory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class DaggerFactory2_SeralPortConfigComponent implements Factory2_SeralPortConfigComponent {
    private Provider<AppManager> appManagerProvider;
    private Provider<Application> applicationProvider;
    private Provider<Factory2_SeralPortConfigModel> factory2_SeralPortConfigModelProvider;
    private Provider<Factory2_SeralPortConfigPresenter> factory2_SeralPortConfigPresenterProvider;
    private Provider<Gson> gsonProvider;
    private Provider<ImageLoader> imageLoaderProvider;
    private Provider<IRepositoryManager> repositoryManagerProvider;
    private Provider<RxErrorHandler> rxErrorHandlerProvider;
    private Provider<Factory2_SeralPortConfigContract.View> viewProvider;

    /* loaded from: classes.dex */
    private static final class Builder implements Factory2_SeralPortConfigComponent.Builder {
        private AppComponent appComponent;
        private Factory2_SeralPortConfigContract.View view;

        private Builder() {
        }

        @Override // com.yj.coffeemachines.di.component.Factory2_SeralPortConfigComponent.Builder
        public Builder appComponent(AppComponent appComponent) {
            this.appComponent = (AppComponent) Preconditions.checkNotNull(appComponent);
            return this;
        }

        @Override // com.yj.coffeemachines.di.component.Factory2_SeralPortConfigComponent.Builder
        public Factory2_SeralPortConfigComponent build() {
            Preconditions.checkBuilderRequirement(this.view, Factory2_SeralPortConfigContract.View.class);
            Preconditions.checkBuilderRequirement(this.appComponent, AppComponent.class);
            return new DaggerFactory2_SeralPortConfigComponent(this.appComponent, this.view);
        }

        @Override // com.yj.coffeemachines.di.component.Factory2_SeralPortConfigComponent.Builder
        public Builder view(Factory2_SeralPortConfigContract.View view) {
            this.view = (Factory2_SeralPortConfigContract.View) Preconditions.checkNotNull(view);
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

    private DaggerFactory2_SeralPortConfigComponent(AppComponent appComponent, Factory2_SeralPortConfigContract.View view) {
        initialize(appComponent, view);
    }

    public static Factory2_SeralPortConfigComponent.Builder builder() {
        return new Builder();
    }

    private void initialize(AppComponent appComponent, Factory2_SeralPortConfigContract.View view) {
        this.repositoryManagerProvider = new com_jess_arms_di_component_AppComponent_repositoryManager(appComponent);
        this.gsonProvider = new com_jess_arms_di_component_AppComponent_gson(appComponent);
        this.applicationProvider = new com_jess_arms_di_component_AppComponent_application(appComponent);
        this.factory2_SeralPortConfigModelProvider = DoubleCheck.provider(Factory2_SeralPortConfigModel_Factory.create(this.repositoryManagerProvider, this.gsonProvider, this.applicationProvider));
        this.viewProvider = InstanceFactory.create(view);
        this.rxErrorHandlerProvider = new com_jess_arms_di_component_AppComponent_rxErrorHandler(appComponent);
        this.imageLoaderProvider = new com_jess_arms_di_component_AppComponent_imageLoader(appComponent);
        this.appManagerProvider = new com_jess_arms_di_component_AppComponent_appManager(appComponent);
        this.factory2_SeralPortConfigPresenterProvider = DoubleCheck.provider(Factory2_SeralPortConfigPresenter_Factory.create(this.factory2_SeralPortConfigModelProvider, this.viewProvider, this.rxErrorHandlerProvider, this.applicationProvider, this.imageLoaderProvider, this.appManagerProvider));
    }

    private Factory2_SeralPortConfigFragment injectFactory2_SeralPortConfigFragment(Factory2_SeralPortConfigFragment factory2_SeralPortConfigFragment) {
        BaseFragment_MembersInjector.injectMPresenter(factory2_SeralPortConfigFragment, this.factory2_SeralPortConfigPresenterProvider.get());
        return factory2_SeralPortConfigFragment;
    }

    @Override // com.yj.coffeemachines.di.component.Factory2_SeralPortConfigComponent
    public void inject(Factory2_SeralPortConfigFragment factory2_SeralPortConfigFragment) {
        injectFactory2_SeralPortConfigFragment(factory2_SeralPortConfigFragment);
    }
}
