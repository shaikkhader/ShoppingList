package shaik.khader.io.shopping.data.rest;

import javax.inject.Inject;

import io.reactivex.Single;
import shaik.khader.io.shopping.data.model.Products;


public class ShoppingRepository {

    private final ShoppingService shoppingService;

    @Inject
    public ShoppingRepository(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    public Single<Products> getProducts() {
        return shoppingService.getProductsList();
    }

}
