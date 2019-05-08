package shaik.khader.io.shopping.ui.trolley;


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
import shaik.khader.io.shopping.db.trolley.TrolleyProduct;
import shaik.khader.io.shopping.ui.details.TrolleyRepository;

/**
 *  Project           : Shopping
 *  File Name         : TrolleyPageViewModel
 *  Description       :
 *  Revision History  : version 1
 *  Date              : 2019-05-07
 *  Original author   : Shaik Khader Basha
 *  Description       : Initial version
 *  
 */
public class TrolleyPageViewModel extends ViewModel {

    private static final String TAG = TrolleyPageViewModel.class.getSimpleName();

    private TrolleyRepository mTrolleyRepository;
    private CompositeDisposable mCompositeDisposable;

    private final MutableLiveData<List<TrolleyProduct>> productList = new MutableLiveData<>();
    private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    @Inject
    public TrolleyPageViewModel(TrolleyRepository shoppingRepository) {
        mTrolleyRepository = shoppingRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    public void fetchData() {
        mCompositeDisposable.add(mTrolleyRepository.getAllTrolleyProducts().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<List<TrolleyProduct>>() {
                    @Override
                    public void onSuccess(List<TrolleyProduct> products) {
                        Log.d(TAG, "products" + products);
                        productList.setValue(products);
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

    public LiveData<List<TrolleyProduct>> getProductList() {
        return productList;
    }
}
