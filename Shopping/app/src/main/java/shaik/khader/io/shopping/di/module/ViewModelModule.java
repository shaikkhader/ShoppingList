package shaik.khader.io.shopping.di.module;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import shaik.khader.io.shopping.di.util.ViewModelKey;
import shaik.khader.io.shopping.ui.shoppinglist.ShoppingListViewModel;
import shaik.khader.io.shopping.util.ViewModelFactory;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ShoppingListViewModel.class)
    abstract ViewModel bindShoppingListViewModel(ShoppingListViewModel shoppingListViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
