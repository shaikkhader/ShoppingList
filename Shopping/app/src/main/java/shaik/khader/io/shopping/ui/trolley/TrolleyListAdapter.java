package shaik.khader.io.shopping.ui.trolley;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shaik.khader.io.shopping.R;
import shaik.khader.io.shopping.data.model.Product;
import shaik.khader.io.shopping.db.trolley.TrolleyProduct;
import shaik.khader.io.shopping.ui.shoppinglist.ProductSelectionListener;
import shaik.khader.io.shopping.ui.shoppinglist.ShoppingListViewModel;

/**
 *  Project           : Shopping
 *  File Name         : ShoppingListAdapter
 *  Description       :
 *  Revision History  : version 1
 *  Date              : 2019-05-07
 *  Original author   : Shaik Khader Basha
 *  Description       : Initial version
 *  
 */
public class TrolleyListAdapter extends RecyclerView.Adapter<TrolleyListAdapter.ProductViewHolder> {


    private final List<TrolleyProduct> data = new ArrayList<>();
    private TrolleyItemActionListener trolleyItemActionListener;

    private TrolleyPageViewModel trolleyPageViewModel;


    TrolleyListAdapter(TrolleyPageViewModel trolleyPageViewModel, LifecycleOwner lifecycleOwner, TrolleyItemActionListener productSelectionListener) {
        this.trolleyItemActionListener = productSelectionListener;
        this.trolleyPageViewModel = trolleyPageViewModel;
        trolleyPageViewModel.getProductList().observe(lifecycleOwner, product -> {
            data.clear();
            if (product != null) {
                data.addAll(product);
                notifyDataSetChanged();
            }
        });
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_trolley_list_adapter, parent, false);
        return new ProductViewHolder(view, trolleyItemActionListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int position) {
        productViewHolder.bind(trolleyPageViewModel,data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static final class ProductViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.trolley_list_productname)
        TextView productNameTextView;

        @BindView(R.id.trolley_list_product_image)
        ImageView productImageView;

        @BindView(R.id.trolley_list_product_price)
        TextView trolley_list_product_price;

        @BindView(R.id.trolley_list_item_delete)
        ImageView trolley_list_item_delete;

        private TrolleyProduct product;

        ProductViewHolder(@NonNull View itemView, final TrolleyItemActionListener trolleyItemActionListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            trolley_list_item_delete.setOnClickListener(v -> {
                if (this.product != null && trolleyItemActionListener != null) {
                    trolleyItemActionListener.deleteProduct(product);
                }
            });

        }

        void bind(TrolleyPageViewModel trolleyPageViewModel,TrolleyProduct product) {
            this.product = product;
            productNameTextView.setText(product.getName());
            trolley_list_product_price.setText(trolleyPageViewModel.getContext().getResources().getString(R.string.price_place_holder,String.valueOf(product.getPrice())));
            Picasso.get().load(product.getImageUrl()).into(productImageView);
        }
    }
}
