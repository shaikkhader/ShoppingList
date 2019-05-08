package shaik.khader.io.shopping.ui.details;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import shaik.khader.io.shopping.R;
import shaik.khader.io.shopping.base.BaseFragment;
import shaik.khader.io.shopping.data.model.Product;
import shaik.khader.io.shopping.ui.trolley.TrolleyPageFragment;
import shaik.khader.io.shopping.util.ViewModelFactory;

/**
 *  Project           : Shopping
 *  File Name         : ProductDetailsFragment
 *  Description       :
 *  Revision History  : version 1
 *  Date              : 2019-05-07
 *  Original author   : Shaik Khader Basha
 *  Description       : Initial version
 *  
 */
public class ProductDetailsFragment extends BaseFragment {


    @BindView(R.id.product_name_detail_text_view)
    TextView product_name_detail_text_view;
    @BindView(R.id.product_price_detail_text_view)
    TextView product_price_detail_text_view;
    @BindView(R.id.product_imageView)
    ImageView product_imageView;
    @BindView(R.id.add_remove_from_cart_Button)
    Button add_remove_from_cart_Button;
    @BindView(R.id.checkout_Button)
    Button checkout_Button;


    public static ProductDetailsFragment getInstance(Product product) {
        ProductDetailsFragment productDetailsFragment = new ProductDetailsFragment();
        if (product != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("product", product);
            productDetailsFragment.setArguments(bundle);
        }
        return productDetailsFragment;

    }

    private Product mProduct;

    @Inject
    ViewModelFactory viewModelFactory;

    private ProductDetailsViewModel productDetailsViewModel;

    @Override
    protected int layoutRes() {
        return R.layout.fragment_product_details;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        productDetailsViewModel = ViewModelProviders.of(this, viewModelFactory).get(ProductDetailsViewModel.class);
        mProduct = getArguments().getParcelable("product");
        productDetailsViewModel.setProduct(mProduct);
        Picasso.get().load(mProduct.getImage_url()).into(product_imageView);
        product_name_detail_text_view.setText(mProduct.getName());
        product_price_detail_text_view.setText(getResources().getString(R.string.price_place_holder,mProduct.getPrice()));
        add_remove_from_cart_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productDetailsViewModel.addItemToTrolley(mProduct);
            }
        });

        checkout_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.add(R.id.screenContainer, TrolleyPageFragment.getInstance());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                Toast.makeText(getContext(), "Product selected", Toast.LENGTH_LONG).show();
            }
        });

    }
}
