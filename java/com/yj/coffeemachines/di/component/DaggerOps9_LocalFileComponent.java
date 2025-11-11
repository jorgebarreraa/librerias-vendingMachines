package com.yj.coffeemachines.di.component;

import android.app.AlertDialog;
import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.base.BaseFragment_MembersInjector;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.integration.IRepositoryManager;
import com.yj.coffeemachines.di.component.Ops9_LocalFileComponent;
import com.yj.coffeemachines.di.module.Ops9_LocalFileModule_GetSportDialogFactory;
import com.yj.coffeemachines.mvp.contract.Ops9_LocalFileContract;
import com.yj.coffeemachines.mvp.model.Ops9_LocalFileModel;
import com.yj.coffeemachines.mvp.model.Ops9_LocalFileModel_Factory;
import com.yj.coffeemachines.mvp.presenter.Ops9_LocalFilePresenter;
import com.yj.coffeemachines.mvp.presenter.Ops9_LocalFilePresenter_Factory;
import com.yj.coffeemachines.mvp.ui.fragment.Ops9_LocalFileFragment;
import com.yj.coffeemachines.mvp.ui.fragment.Ops9_LocalFileFragment_MembersInjector;
import dagger.internal.DoubleCheck;
import dagger.internal.InstanceFactory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class DaggerOps9_LocalFileComponent implements Ops9_LocalFileComponent {
    private Provider<AppManager> appManagerProvider;
    private Provider<Application> applicationProvider;
    private Provider<AlertDialog> getSportDialogProvider;
    private Provider<Gson> gsonProvider;
    private Provider<ImageLoader> imageLoaderProvider;
    private Provider<Ops9_LocalFileModel> ops9_LocalFileModelProvider;
    private Provider<Ops9_LocalFilePresenter> ops9_LocalFilePresenterProvider;
    private Provider<IRepositoryManager> repositoryManagerProvider;
    private Provider<RxErrorHandler> rxErrorHandlerProvider;
    private Provider<Ops9_LocalFileContract.View> viewProvider;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class Builder implements Ops9_LocalFileComponent.Builder {
        private AppComponent appComponent;
        private Ops9_LocalFileContract.View view;

        private Builder() {
        }

        @Override // com.yj.coffeemachines.di.component.Ops9_LocalFileComponent.Builder
        public Builder appComponent(AppComponent appComponent) {
            this.appComponent = (AppComponent) Preconditions.checkNotNull(appComponent);
            return this;
        }

        @Override // com.yj.coffeemachines.di.component.Ops9_LocalFileComponent.Builder
        public Ops9_LocalFileComponent build() {
            Preconditions.checkBuilderRequirement(this.view, Ops9_LocalFileContract.View.class);
            Preconditions.checkBuilderRequirement(this.appComponent, AppComponent.class);
            return new DaggerOps9_LocalFileComponent(this.appComponent, this.view);
        }

        @Override // com.yj.coffeemachines.di.component.Ops9_LocalFileComponent.Builder
        public Builder view(Ops9_LocalFileContract.View view) {
            this.view = (Ops9_LocalFileContract.View) Preconditions.checkNotNull(view);
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

    private DaggerOps9_LocalFileComponent(AppComponent appComponent, Ops9_LocalFileContract.View view) {
        initialize(appComponent, view);
    }

    public static Ops9_LocalFileComponent.Builder builder() {
        return new Builder();
    }

    private void initialize(AppComponent appComponent, Ops9_LocalFileContract.View view) {
        this.repositoryManagerProvider = new com_jess_arms_di_component_AppComponent_repositoryManager(appComponent);
        this.gsonProvider = new com_jess_arms_di_component_AppComponent_gson(appComponent);
        this.applicationProvider = new com_jess_arms_di_component_AppComponent_application(appComponent);
        this.ops9_LocalFileModelProvider = DoubleCheck.provider(Ops9_LocalFileModel_Factory.create(this.repositoryManagerProvider, this.gsonProvider, this.applicationProvider));
        this.viewProvider = InstanceFactory.create(view);
        this.rxErrorHandlerProvider = new com_jess_arms_di_component_AppComponent_rxErrorHandler(appComponent);
        this.imageLoaderProvider = new com_jess_arms_di_component_AppComponent_imageLoader(appComponent);
        this.appManagerProvider = new com_jess_arms_di_component_AppComponent_appManager(appComponent);
        this.ops9_LocalFilePresenterProvider = DoubleCheck.provider(Ops9_LocalFilePresenter_Factory.create(this.ops9_LocalFileModelProvider, this.viewProvider, this.rxErrorHandlerProvider, this.applicationProvider, this.imageLoaderProvider, this.appManagerProvider));
        this.getSportDialogProvider = DoubleCheck.provider(Ops9_LocalFileModule_GetSportDialogFactory.create(this.viewProvider));
    }

    private Ops9_LocalFileFragment injectOps9_LocalFileFragment(Ops9_LocalFileFragment ops9_LocalFileFragment) {
        BaseFragment_MembersInjector.injectMPresenter(ops9_LocalFileFragment, this.ops9_LocalFilePresenterProvider.get());
        Ops9_LocalFileFragment_MembersInjector.injectMSportDialog(ops9_LocalFileFragment, this.getSportDialogProvider.get());
        return ops9_LocalFileFragment;
    }

    @Override // com.yj.coffeemachines.di.component.Ops9_LocalFileComponent
    public void inject(Ops9_LocalFileFragment ops9_LocalFileFragment) {
        injectOps9_LocalFileFragment(ops9_LocalFileFragment);
    }
}
