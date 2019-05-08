/*
 *  Copyright (c) $year. Philips India Ltd.
 *  All rights reserved. Reproduction in whole or in part is prohibited
 *  without the written consent of the copyright holder.
 *
 */

package shaik.khader.io.shopping.ui.details;


import android.util.Log;

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

    TrolleyRepository mTrolleyRepository;

    private CompositeDisposable mCompositeDisposable;

    private Product mProduct;

    @Inject
    public ProductDetailsViewModel(TrolleyRepository trolleyRepository) {
        mTrolleyRepository = trolleyRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    public void setProduct(Product mProduct) {
        this.mProduct = mProduct;
    }

    void addItemToTrolley(Product product) {
        mTrolleyRepository.insertIntoTrolley(product);
        mCompositeDisposable.add(mTrolleyRepository.getAllTrolleyProducts().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<List<TrolleyProduct>>() {
                    @Override
                    public void onSuccess(List<TrolleyProduct> products) {
                        Log.d(TAG, "products" + products.size());
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
