package shaik.khader.io.shopping.db.trolley;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "trolley_products")
public class TrolleyProduct {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @Nullable
    private String name;

    @Nullable
    private String imageUrl;

    @Nullable
    private float price;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Nullable
    private float rating;

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    @Nullable
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(@Nullable String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof TrolleyProduct && ((TrolleyProduct) obj).getId() == getId();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
