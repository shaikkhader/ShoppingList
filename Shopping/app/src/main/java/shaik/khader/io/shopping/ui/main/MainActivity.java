package shaik.khader.io.shopping.ui.main;

import android.os.Bundle;

import shaik.khader.io.shopping.R;
import shaik.khader.io.shopping.base.BaseActivity;
import shaik.khader.io.shopping.ui.shoppinglist.ShoppingListFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(R.id.screenContainer, new ShoppingListFragment()).commit();
    }

}
