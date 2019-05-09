package shaik.khader.io.shopping.ui.trolley;


import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
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
    private Context mContext;

    private final MutableLiveData<List<TrolleyProduct>> productList = new MutableLiveData<>();
    private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private final MutableLiveData<Boolean> noItemsError = new MutableLiveData<>();


    @Inject
    TrolleyPageViewModel(TrolleyRepository shoppingRepository, Context context) {
        mContext = context;
        mTrolleyRepository = shoppingRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    public Context getContext() {
        return mContext;
    }

    LiveData<List<TrolleyProduct>> getProductList() {
        return productList;
    }

    LiveData<Boolean> getError() {
        return repoLoadError;
    }

    LiveData<Boolean> getLoading() {
        return loading;
    }

    LiveData<Boolean> getNoItemsError() {
        return noItemsError;
    }

    void fetchData() {
        mCompositeDisposable.add(mTrolleyRepository.getAllTrolleyProducts().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<List<TrolleyProduct>>() {
                    @Override
                    public void onSuccess(List<TrolleyProduct> products) {
                        repoLoadError.setValue(false);
                        loading.setValue(false);
                        if (products.size() == 0) {
                            noItemsError.setValue(true);
                        }else {
                            productList.setValue(products);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        repoLoadError.setValue(true);
                        loading.setValue(false);
                        noItemsError.setValue(false);

                    }
                }));
    }

    void deleteProduct(TrolleyProduct trolleyProduct) {
        mTrolleyRepository.deleteProduct(trolleyProduct);
        fetchData();
    }

    int getProductCount(){
        return productList.getValue() != null ? productList.getValue().size() : 0;
    }

    float getNetAmount(){
        if (productList.getValue() == null )
            return 0;
        float totalAmount = 0;
        for (TrolleyProduct trolleyProduct : productList.getValue()){
            totalAmount+=trolleyProduct.getPrice();
        }
        return totalAmount;
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
