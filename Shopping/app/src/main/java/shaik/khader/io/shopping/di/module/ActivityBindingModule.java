package shaik.khader.io.shopping.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import shaik.khader.io.shopping.ui.main.MainActivity;
import shaik.khader.io.shopping.ui.main.MainFragmentBindingModule;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {MainFragmentBindingModule.class})
    abstract MainActivity bindMainActivity();


}
