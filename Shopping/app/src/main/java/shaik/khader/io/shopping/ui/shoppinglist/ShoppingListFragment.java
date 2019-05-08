package shaik.khader.io.shopping.ui.shoppinglist;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import shaik.khader.io.shopping.R;
import shaik.khader.io.shopping.base.BaseFragment;
import shaik.khader.io.shopping.data.model.Product;
import shaik.khader.io.shopping.util.ViewModelFactory;

/**
 *  Project           : Shopping
 *  File Name         : ShoppingListFragment
 *  Description       :
 *  Revision History  : version 1
 *  Date              : 2019-05-07
 *  Original author   : Shaik Khader Basha
 *  Description       : Initial version
 *  
 */
public class ShoppingListFragment extends BaseFragment implements ProductSelectionListener {


    @BindView(R.id.shopping_list_recycler_view)
    RecyclerView shopping_list_recycler_view;
    @BindView(R.id.generic_error_text_view)
    TextView generic_error_text_view;
    @BindView(R.id.loading_progress_view)
    View loadingView;


    @Inject
    ViewModelFactory viewModelFactory;

    private ShoppingListViewModel shoppingListViewModel;

    @Override
    protected int layoutRes() {
        return R.layout.fragment_shopping_list;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        shoppingListViewModel = ViewModelProviders.of(this, viewModelFactory).get(ShoppingListViewModel.class);
        shoppingListViewModel.fetchData();

        shopping_list_recycler_view.setAdapter(new ShoppingListAdapter(shoppingListViewModel, this, this));
        shopping_list_recycler_view.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    public void onProductSelected(Product product) {
        Toast.makeText(getContext(), "Product selected", Toast.LENGTH_LONG).show();
    }
}