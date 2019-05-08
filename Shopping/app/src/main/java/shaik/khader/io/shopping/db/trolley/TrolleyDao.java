package shaik.khader.io.shopping.db.trolley;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface TrolleyDao {

    @Query("SELECT * FROM trolley_products")
    Single<List<TrolleyProduct>> getAllTrolleyProducts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TrolleyProduct trolleyProduct);

    @Delete
    void remove(TrolleyProduct trolleyProduct);

}
