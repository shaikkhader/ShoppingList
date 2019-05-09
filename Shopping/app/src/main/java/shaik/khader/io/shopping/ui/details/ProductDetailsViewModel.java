/*
 *  Copyright (c) $year. Philips India Ltd.
 *  All rights reserved. Reproduction in whole or in part is prohibited
 *  without the written consent of the copyright holder.
 *
 */

package shaik.khader.io.shopping.ui.details;


import android.content.Context;
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
import shaik.khader.io.shopping.db.trolley.TrolleyProduct;

/**
 *  Project           : Shopping
 *  File Name         : ProductDetailsViewModel
 *  Description       :
 *  Revision History  : version 1
 *  Date              : 2019-05-07
 *  Original author   : Shaik Khader Basha
 *  Description       : Initial version
 *  
 */
public class ProductDetailsViewModel extends ViewModel {

    private static final String TAG = ProductDetailsViewModel.class.getSimpleName();

    private TrolleyRepository mTrolleyRepository;

    private CompositeDisposable mCompositeDisposable;

    private Context mContext;

    private final MutableLiveData<List<TrolleyProduct>> productList = new MutableLiveData<>();


    @Inject
    ProductDetailsViewModel(TrolleyRepository trolleyRepository, Context context) {
        mTrolleyRepository = trolleyRepository;
        mContext = context;
        mCompositeDisposable = new CompositeDisposable();
    }

    public Context getContext() {
        return mContext;
    }

    LiveData<List<TrolleyProduct>> getProductList() {
        return productList;
    }


    void addItemToTrolley(Product product) {
        mTrolleyRepository.insertIntoTrolley(product);
        fetchData();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
            mCompositeDisposable = null;
        }
    }


    void fetchData() {
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
}
