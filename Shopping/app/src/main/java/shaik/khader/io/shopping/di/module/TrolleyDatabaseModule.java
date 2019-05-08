package shaik.khader.io.shopping.di.module;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import shaik.khader.io.shopping.db.trolley.TrolleyDao;
import shaik.khader.io.shopping.db.TrolleyDatabase;

@Module
public class TrolleyDatabaseModule {
    @Singleton
    @Provides
    public TrolleyDatabase provideTvMazeDatabase(Context context) {
        return Room.databaseBuilder(context,
                TrolleyDatabase.class, TrolleyDatabase.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Singleton
    @Provides
    public TrolleyDao provideShowDao(TrolleyDatabase trolleyDatabase) {
        return trolleyDatabase.getTrolleyDao();
    }
}

