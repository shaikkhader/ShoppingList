package shaik.khader.io.shopping.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;
import shaik.khader.io.shopping.ShoppingApplication;
import shaik.khader.io.shopping.di.module.ActivityBindingModule;
import shaik.khader.io.shopping.di.module.ApplicationModule;
import shaik.khader.io.shopping.di.module.ContextModule;
import shaik.khader.io.shopping.di.module.TrolleyDatabaseModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, ActivityBindingModule.class, ApplicationModule.class, ContextModule.class, TrolleyDatabaseModule.class})
public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {

    void inject(ShoppingApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }
}