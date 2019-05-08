package shaik.khader.io.shopping.ui.shoppinglist;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import shaik.khader.io.shopping.data.model.Product;
import shaik.khader.io.shopping.data.model.Products;
import shaik.khader.io.shopping.data.rest.ShoppingRepository;
import shaik.khader.io.shopping.db.trolley.TrolleyProduct;
import shaik.khader.io.shopping.ui.details.TrolleyRepository;

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


    ShoppingRepository mShoppingRepository;

    TrolleyRepository mTrolleyRepository;

    private CompositeDisposable mCompositeDisposable;

    private final MutableLiveData<List<Product>> productList = new MutableLiveData<>();
    private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();


    @Inject
    public ShoppingListViewModel(ShoppingRepository shoppingRepository, TrolleyRepository trolleyRepository) {
        mShoppingRepository = shoppingRepository;
        mTrolleyRepository = trolleyRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    LiveData<List<Product>> getProductList() {
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
