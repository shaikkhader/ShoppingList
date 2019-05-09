package shaik.khader.io.shopping.ui.trolley;

import shaik.khader.io.shopping.data.model.Product;
import shaik.khader.io.shopping.db.trolley.TrolleyProduct;

/**
 *  Project           : Shopping
 *  File Name         : ProductSelectionListener
 *  Description       :
 *  Revision History  : version 1
 *  Date              : 2019-05-07
 *  Original author   : Shaik Khader Basha
 *  Description       : Initial version
 *  
 */
public interface TrolleyItemActionListener {

    void deleteProduct(TrolleyProduct product);
}
