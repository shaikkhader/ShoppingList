package shaik.khader.io.shopping.ui.shoppinglist;

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
public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ProductViewHolder> {


    private final List<Product> data = new ArrayList<>();
    private ProductSelectionListener productSelectionListener;


    ShoppingListAdapter(ShoppingListViewModel shoppingListViewModel, LifecycleOwner lifecycleOwner, ProductSelectionListener productSelectionListener) {
        this.productSelectionListener = productSelectionListener;
        shoppingListViewModel.getProductList().observe(lifecycleOwner, product -> {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_shopping_list_adapter, parent, false);
        return new ProductViewHolder(view, productSelectionListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int position) {
        productViewHolder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static final class ProductViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.shopping_list_productname)
        TextView productNameTextView;

        @BindView(R.id.shopping_list_product_image)
        ImageView productImageView;

        private Product product;

        ProductViewHolder(@NonNull View itemView, ProductSelectionListener productSelectionListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            if (productSelectionListener != null)
                productSelectionListener.onProductSelected(product);
        }

        void bind(Product product) {
            this.product = product;
            productNameTextView.setText(product.getName());
            Picasso.get().load(product.getImage_url()).into(productImageView);
        }
    }
}
