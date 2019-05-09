package shaik.khader.io.shopping.ui.trolley;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import shaik.khader.io.shopping.db.trolley.TrolleyProduct;
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
public class TrolleyPageFragment extends BaseFragment implements TrolleyItemActionListener {

    @BindView(R.id.trolley_list_recycler_view)
    RecyclerView trolley_list_recycler_view;
    @BindView(R.id.generic_error_text_view)
    TextView generic_error_text_view;
    @BindView(R.id.loading_progress_view)
    View loadingView;
    @BindView(R.id.summary_layout)
    ConstraintLayout summary_layout;
    @BindView(R.id.checkout_button)
    Button checkout_button;
    @BindView(R.id.total_items_text_view)
    TextView total_items_text_view;
    @BindView(R.id.net_total_price_text_view)
    TextView net_total_price_text_view;

    public static TrolleyPageFragment getInstance() {
        return new TrolleyPageFragment();
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

        observableViewModel();

        checkout_button.setOnClickListener(checkout -> {
            Toast.makeText(getContext(), getResources().getString(R.string.to_be_continued), Toast.LENGTH_LONG).show();
        });


    }


    @Override
    public void deleteProduct(TrolleyProduct product) {
        trolleyPageViewModel.deleteProduct(product);
    }


    private void observableViewModel() {
        trolleyPageViewModel.getProductList().observe(this, products -> {
            if (products != null) {
                if (products.size() > 0) {
                    total_items_text_view.setText(getResources().getString(R.string.no_of_items, String.valueOf(trolleyPageViewModel.getProductCount())));
                    net_total_price_text_view.setText(getResources().getString(R.string.net_total, String.valueOf(trolleyPageViewModel.getNetAmount())));
                    summary_layout.setVisibility(View.VISIBLE);
                    trolley_list_recycler_view.setVisibility(View.VISIBLE);
                } else {
                    summary_layout.setVisibility(View.GONE);
                    generic_error_text_view.setText(trolleyPageViewModel.getContext().getResources().getString(R.string.no_items_error_statement));
                }
            }
        });

        trolleyPageViewModel.getError().observe(this, isError -> {
            if (isError != null) if (isError) {
                generic_error_text_view.setVisibility(View.VISIBLE);
                trolley_list_recycler_view.setVisibility(View.GONE);
                generic_error_text_view.setText(trolleyPageViewModel.getContext().getResources().getString(R.string.generic_error_statement));
            } else {
                generic_error_text_view.setVisibility(View.GONE);
                generic_error_text_view.setText(null);
            }
        });

        trolleyPageViewModel.getLoading().observe(this, isLoading -> {
            if (isLoading != null) {
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    generic_error_text_view.setVisibility(View.GONE);
                    trolley_list_recycler_view.setVisibility(View.GONE);
                }
            }
        });

        trolleyPageViewModel.getNoItemsError().observe(this, noItems -> {
            if (noItems != null) if (noItems) {
                summary_layout.setVisibility(View.GONE);
                generic_error_text_view.setVisibility(View.VISIBLE);
                trolley_list_recycler_view.setVisibility(View.GONE);
                generic_error_text_view.setText(trolleyPageViewModel.getContext().getResources().getString(R.string.no_items_error_statement));
            } else {
                summary_layout.setVisibility(View.VISIBLE);
                generic_error_text_view.setVisibility(View.GONE);
                generic_error_text_view.setText(null);
            }
        });
    }
}
