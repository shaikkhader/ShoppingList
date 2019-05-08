package shaik.khader.io.shopping.ui.trolley;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import javax.inject.Inject;

import butterknife.BindView;
import shaik.khader.io.shopping.R;
import shaik.khader.io.shopping.base.BaseFragment;
import shaik.khader.io.shopping.data.model.Product;
import shaik.khader.io.shopping.ui.details.ProductDetailsFragment;
import shaik.khader.io.shopping.ui.shoppinglist.ProductSelectionListener;
import shaik.khader.io.shopping.ui.shoppinglist.ShoppingListAdapter;
import shaik.khader.io.shopping.ui.shoppinglist.ShoppingListViewModel;
import shaik.khader.io.shopping.util.ViewModelFactory;

/**
 *  Project           : Shopping
 *  File Name         : TrolleyPageFragment
 *  Description       :
 *  Revision History  : version 1
 *  Date              : 2019-05-07
 *  Original author   : Shaik Khader Basha
 *  Description       : Initial version
 *  
 */
public class TrolleyPageFragment extends BaseFragment implements ProductSelectionListener {

    @BindView(R.id.trolley_list_recycler_view)
    RecyclerView trolley_list_recycler_view;
    @BindView(R.id.generic_error_text_view)
    TextView generic_error_text_view;
    @BindView(R.id.loading_progress_view)
    View loadingView;

    public static TrolleyPageFragment getInstance() {
        TrolleyPageFragment trolleyPageFragment = new TrolleyPageFragment();
        return trolleyPageFragment;

    }


    @Inject
    ViewModelFactory viewModelFactory;

    private TrolleyPageViewModel trolleyPageViewModel;

    @Override
    protected int layoutRes() {
        return R.layout.fragment_trolley_page;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        trolleyPageViewModel = ViewModelProviders.of(this, viewModelFactory).get(TrolleyPageViewModel.class);
        trolleyPageViewModel.fetchData();

        trolley_list_recycler_view.setAdapter(new TrolleyListAdapter(trolleyPageViewModel, this, this));
        trolley_list_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onProductSelected(Product product) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.screenContainer, ProductDetailsFragment.getInstance(product));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
