package shaik.khader.io.shopping.ui.shoppinglist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import shaik.khader.io.shopping.data.model.Product;
import shaik.khader.io.shopping.data.model.Products;
import shaik.khader.io.shopping.data.rest.ShoppingRepository;

/**
 *  Project           : Shopping
 *  File Name         : ShoppingListViewModel
 *  Description       :
 *  Revision History  : version 1
 *  Date              : 2019-05-07
 *  Original author   : Shaik Khader Basha
 *  Description       : Initial version
 *  
 */
public class ShoppingListViewModel extends ViewModel {

    private static final String TAG = ShoppingListViewModel.class.getSimpleName();


    @Inject
    ShoppingRepository mShoppingRepository;

    private CompositeDisposable mCompositeDisposable;

    private final MutableLiveData<List<Product>> productList = new MutableLiveData<>();
    private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();



    @Inject
    public ShoppingListViewModel(ShoppingRepository shoppingRepository) {
        mShoppingRepository = shoppingRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    LiveData<List<Product>> getProductList(){
        return productList;
    }

    public void fetchData() {
        mCompositeDisposable.add(mShoppingRepository.getProducts().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<Products>() {
                    @Override
                    public void onSuccess(Products products) {
                        Log.d(TAG, "products" + products);
                        productList.setValue(products.products);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "Error" + e);
                    }
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
            mCompositeDisposable = null;
        }
    }


}
