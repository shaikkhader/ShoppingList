package shaik.khader.io.shopping.ui.details;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import shaik.khader.io.shopping.data.model.Product;
import shaik.khader.io.shopping.db.trolley.TrolleyDao;
import shaik.khader.io.shopping.db.trolley.TrolleyProduct;

@Singleton
public class TrolleyRepository {

    private TrolleyDao trolleyDao;

    @Inject
    public TrolleyRepository(TrolleyDao trolleyDao){
        this.trolleyDao = trolleyDao;
    }

    public Single<List<TrolleyProduct>> getAllTrolleyProducts() {
        return trolleyDao.getAllTrolleyProducts();
    }

    public void insertIntoTrolley(Product product) {
        TrolleyProduct trolleyProduct = new TrolleyProduct();
        trolleyProduct.setName(product.getName());
        trolleyProduct.setImageUrl(product.getImage_url());
        trolleyProduct.setRating(product.getRating());
        trolleyDao.insert(trolleyProduct);
    }
}
