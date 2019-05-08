package shaik.khader.io.shopping;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import shaik.khader.io.shopping.di.component.ApplicationComponent;
import shaik.khader.io.shopping.di.component.DaggerApplicationComponent;

public class ShoppingApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        ApplicationComponent component = DaggerApplicationComponent.builder().application(this).build();
        component.inject(this);

        return component;
    }
}
