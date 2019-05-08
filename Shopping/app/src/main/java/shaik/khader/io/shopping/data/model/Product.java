package shaik.khader.io.shopping.data.model;

/**
 *  Project           : Shopping
 *  File Name         : Product
 *  Description       :
 *  Revision History  : version 1
 *  Date              : 2019-05-07
 *  Original author   : Shaik Khader Basha
 *  Description       : Initial version
 *  
 */
public class Product {

    private String name;

    private String price;

    private String image_url;

    private String rating;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
