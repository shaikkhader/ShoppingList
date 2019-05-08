package shaik.khader.io.shopping.ui.main;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import shaik.khader.io.shopping.ui.details.ProductDetailsFragment;
import shaik.khader.io.shopping.ui.shoppinglist.ShoppingListFragment;
import shaik.khader.io.shopping.ui.trolley.TrolleyPageFragment;

@Module
public abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    abstract ShoppingListFragment provideShoppingListFragment();

    @ContributesAndroidInjector
    abstract ProductDetailsFragment provideProductDetailsFragment();

    @ContributesAndroidInjector
    abstract TrolleyPageFragment provideTrolleyPageFragment();


}
