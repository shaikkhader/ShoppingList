package shaik.khader.io.shopping.data.rest;

import io.reactivex.Single;
import retrofit2.http.GET;
import shaik.khader.io.shopping.data.model.Products;

public interface ShoppingService {

    @GET(".")
    Single<Products> getProductsList();


}
