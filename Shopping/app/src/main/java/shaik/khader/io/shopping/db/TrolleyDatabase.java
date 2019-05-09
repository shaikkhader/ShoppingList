package shaik.khader.io.shopping.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import shaik.khader.io.shopping.db.trolley.TrolleyDao;
import shaik.khader.io.shopping.db.trolley.TrolleyProduct;

@Database(entities = {TrolleyProduct.class}, version = 2, exportSchema = false)
public abstract class TrolleyDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "shopping.db";

    public abstract TrolleyDao getTrolleyDao();
}
