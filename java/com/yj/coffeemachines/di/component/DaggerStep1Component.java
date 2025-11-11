package com.yj.coffeemachines.di.component;

import android.app.AlertDialog;
import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.base.BaseFragment_MembersInjector;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.integration.IRepositoryManager;
import com.yj.coffeemachines.di.component.Step1Component;
import com.yj.coffeemachines.di.module.Step1Module_GetSportDialogFactory;
import com.yj.coffeemachines.mvp.contract.Step1Contract;
import com.yj.coffeemachines.mvp.model.Step1Model;
import com.yj.coffeemachines.mvp.model.Step1Model_Factory;
import com.yj.coffeemachines.mvp.presenter.Step1Presenter;
import com.yj.coffeemachines.mvp.presenter.Step1Presenter_Factory;
import com.yj.coffeemachines.mvp.ui.fragment.Step1Fragment;
import com.yj.coffeemachines.mvp.ui.fragment.Step1Fragment_MembersInjector;
import dagger.internal.DoubleCheck;
import dagger.internal.InstanceFactory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class DaggerStep1Component implements Step1Component {
    private Provider<AppManager> appManagerProvider;
    private Provider<Application> applicationProvider;
    private Provider<AlertDialog> getSportDialogProvider;
    private Provider<Gson> gsonProvider;
    private Provider<ImageLoader> imageLoaderProvider;
    private Provider<IRepositoryManager> repositoryManagerProvider;
    private Provider<RxErrorHandler> rxErrorHandlerProvider;
    private Provider<Step1Model> step1ModelProvider;
    private Provider<Step1Presenter> step1PresenterProvider;
    private Provider<Step1Contract.View> viewProvider;

    /* loaded from: classes.dex */
    private static final class Builder implements Step1Component.Builder {
        private AppComponent appComponent;
        private Step1Contract.View view;

        private Builder() {
        }

        @Override // com.yj.coffeemachines.di.component.Step1Component.Builder
        public Builder appComponent(AppComponent appComponent) {
            this.appComponent = (AppComponent) Preconditions.checkNotNull(appComponent);
            return this;
        }

        @Override // com.yj.coffeemachines.di.component.Step1Component.Builder
        public Step1Component build() {
            Preconditions.checkBuilderRequirement(this.view, Step1Contract.View.class);
            Preconditions.checkBuilderRequirement(this.appComponent, AppComponent.class);
            return new DaggerStep1Component(this.appComponent, this.view);
        }

        @Override // com.yj.coffeemachines.di.component.Step1Component.Builder
        public Builder view(Step1Contract.View view) {
            this.view = (Step1Contract.View) Preconditions.checkNotNull(view);
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

    private DaggerStep1Component(AppComponent appComponent, Step1Contract.View view) {
        initialize(appComponent, view);
    }

    public static Step1Component.Builder builder() {
        return new Builder();
    }

    private void initialize(AppComponent appComponent, Step1Contract.View view) {
        this.repositoryManagerProvider = new com_jess_arms_di_component_AppComponent_repositoryManager(appComponent);
        this.gsonProvider = new com_jess_arms_di_component_AppComponent_gson(appComponent);
        this.applicationProvider = new com_jess_arms_di_component_AppComponent_application(appComponent);
        this.step1ModelProvider = DoubleCheck.provider(Step1Model_Factory.create(this.repositoryManagerProvider, this.gsonProvider, this.applicationProvider));
        this.viewProvider = InstanceFactory.create(view);
        this.rxErrorHandlerProvider = new com_jess_arms_di_component_AppComponent_rxErrorHandler(appComponent);
        this.imageLoaderProvider = new com_jess_arms_di_component_AppComponent_imageLoader(appComponent);
        this.appManagerProvider = new com_jess_arms_di_component_AppComponent_appManager(appComponent);
        this.step1PresenterProvider = DoubleCheck.provider(Step1Presenter_Factory.create(this.step1ModelProvider, this.viewProvider, this.rxErrorHandlerProvider, this.applicationProvider, this.imageLoaderProvider, this.appManagerProvider));
        this.getSportDialogProvider = DoubleCheck.provider(Step1Module_GetSportDialogFactory.create(this.viewProvider));
    }

    private Step1Fragment injectStep1Fragment(Step1Fragment step1Fragment) {
        BaseFragment_MembersInjector.injectMPresenter(step1Fragment, this.step1PresenterProvider.get());
        Step1Fragment_MembersInjector.injectMSportDialog(step1Fragment, this.getSportDialogProvider.get());
        return step1Fragment;
    }

    @Override // com.yj.coffeemachines.di.component.Step1Component
    public void inject(Step1Fragment step1Fragment) {
        injectStep1Fragment(step1Fragment);
    }
}
