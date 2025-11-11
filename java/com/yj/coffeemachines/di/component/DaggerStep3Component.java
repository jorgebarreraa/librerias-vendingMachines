package com.yj.coffeemachines.di.component;

import android.app.AlertDialog;
import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.base.BaseFragment_MembersInjector;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.integration.IRepositoryManager;
import com.yj.coffeemachines.di.component.Step3Component;
import com.yj.coffeemachines.di.module.Step3Module_GetSportDialogFactory;
import com.yj.coffeemachines.mvp.contract.Step3Contract;
import com.yj.coffeemachines.mvp.model.Step3Model;
import com.yj.coffeemachines.mvp.model.Step3Model_Factory;
import com.yj.coffeemachines.mvp.presenter.Step3Presenter;
import com.yj.coffeemachines.mvp.presenter.Step3Presenter_Factory;
import com.yj.coffeemachines.mvp.ui.fragment.Step3Fragment;
import com.yj.coffeemachines.mvp.ui.fragment.Step3Fragment_MembersInjector;
import dagger.internal.DoubleCheck;
import dagger.internal.InstanceFactory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class DaggerStep3Component implements Step3Component {
    private Provider<AppManager> appManagerProvider;
    private Provider<Application> applicationProvider;
    private Provider<AlertDialog> getSportDialogProvider;
    private Provider<Gson> gsonProvider;
    private Provider<ImageLoader> imageLoaderProvider;
    private Provider<IRepositoryManager> repositoryManagerProvider;
    private Provider<RxErrorHandler> rxErrorHandlerProvider;
    private Provider<Step3Model> step3ModelProvider;
    private Provider<Step3Presenter> step3PresenterProvider;
    private Provider<Step3Contract.View> viewProvider;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class Builder implements Step3Component.Builder {
        private AppComponent appComponent;
        private Step3Contract.View view;

        private Builder() {
        }

        @Override // com.yj.coffeemachines.di.component.Step3Component.Builder
        public Builder appComponent(AppComponent appComponent) {
            this.appComponent = (AppComponent) Preconditions.checkNotNull(appComponent);
            return this;
        }

        @Override // com.yj.coffeemachines.di.component.Step3Component.Builder
        public Step3Component build() {
            Preconditions.checkBuilderRequirement(this.view, Step3Contract.View.class);
            Preconditions.checkBuilderRequirement(this.appComponent, AppComponent.class);
            return new DaggerStep3Component(this.appComponent, this.view);
        }

        @Override // com.yj.coffeemachines.di.component.Step3Component.Builder
        public Builder view(Step3Contract.View view) {
            this.view = (Step3Contract.View) Preconditions.checkNotNull(view);
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

    private DaggerStep3Component(AppComponent appComponent, Step3Contract.View view) {
        initialize(appComponent, view);
    }

    public static Step3Component.Builder builder() {
        return new Builder();
    }

    private void initialize(AppComponent appComponent, Step3Contract.View view) {
        this.repositoryManagerProvider = new com_jess_arms_di_component_AppComponent_repositoryManager(appComponent);
        this.gsonProvider = new com_jess_arms_di_component_AppComponent_gson(appComponent);
        this.applicationProvider = new com_jess_arms_di_component_AppComponent_application(appComponent);
        this.step3ModelProvider = DoubleCheck.provider(Step3Model_Factory.create(this.repositoryManagerProvider, this.gsonProvider, this.applicationProvider));
        this.viewProvider = InstanceFactory.create(view);
        this.rxErrorHandlerProvider = new com_jess_arms_di_component_AppComponent_rxErrorHandler(appComponent);
        this.imageLoaderProvider = new com_jess_arms_di_component_AppComponent_imageLoader(appComponent);
        this.appManagerProvider = new com_jess_arms_di_component_AppComponent_appManager(appComponent);
        this.step3PresenterProvider = DoubleCheck.provider(Step3Presenter_Factory.create(this.step3ModelProvider, this.viewProvider, this.rxErrorHandlerProvider, this.applicationProvider, this.imageLoaderProvider, this.appManagerProvider));
        this.getSportDialogProvider = DoubleCheck.provider(Step3Module_GetSportDialogFactory.create(this.viewProvider));
    }

    private Step3Fragment injectStep3Fragment(Step3Fragment step3Fragment) {
        BaseFragment_MembersInjector.injectMPresenter(step3Fragment, this.step3PresenterProvider.get());
        Step3Fragment_MembersInjector.injectMSportDialog(step3Fragment, this.getSportDialogProvider.get());
        return step3Fragment;
    }

    @Override // com.yj.coffeemachines.di.component.Step3Component
    public void inject(Step3Fragment step3Fragment) {
        injectStep3Fragment(step3Fragment);
    }
}
