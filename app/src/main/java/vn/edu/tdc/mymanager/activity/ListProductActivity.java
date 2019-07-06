package vn.edu.tdc.mymanager.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.tdc.mymanager.R;
import vn.edu.tdc.mymanager.adapter.AdapterProduct;
import vn.edu.tdc.mymanager.fragment.DialogFragmentAddProduct;
import vn.edu.tdc.mymanager.model.Product;

public class ListProductActivity extends AppCompatActivity {

    TextView tvNoItem;
    RecyclerView rvListProduct;
    ArrayList<Product> listProduct;
    AdapterProduct adapterProduct;

    // On Create
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_product);
        initToolbar();

        setControl();

        importData();
    }

    private void importData() {

        listProduct.add(new Product("01", "01", "Sản phẩm", 10, 15, "Cạc tông"));
        listProduct.add(new Product("01", "01", "Sản phẩm", 10, 15, "Cạc tông"));
        listProduct.add(new Product("01", "01", "Sản phẩm", 10, 15, "Cạc tông"));
        listProduct.add(new Product("01", "01", "Sản phẩm", 10, 15, "Cạc tông"));

        adapterProduct.notifyDataSetChanged();

    }

    public void setControl() {

        tvNoItem = (TextView) findViewById(R.id.tv_activity_list_product_no_item);
        rvListProduct = (RecyclerView) findViewById(R.id.rv_activity_list_product);

        listProduct = new ArrayList<>();
        adapterProduct = new AdapterProduct(ListProductActivity.this, listProduct);

        LinearLayoutManager layoutManagerProduct = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        rvListProduct.setLayoutManager(layoutManagerProduct);
        rvListProduct.setAdapter(adapterProduct);


    }


    // Calling intent
    public static Intent getCallingIntent(Context context) {

        Intent intent = new Intent(context, ListProductActivity.class);
        return  intent;

    }


    // Init tool bar
    private void initToolbar() {
        // Set title
        getSupportActionBar().setTitle("Chi tiết khu vực");

        // Hiển thị nút back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    // Bắt sự kiên trên tool bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:  // Sự kiện nút back

                // Hủy màn hình
                onBackPressed();
                break;


            case R.id.menu_add_product:

                FragmentManager fm = getSupportFragmentManager();
                DialogFragmentAddProduct dialogFragmentAddProduct = DialogFragmentAddProduct.newInstance();
                dialogFragmentAddProduct.show(fm, null);


                break;

        }

        return true;
    }

    // Khởi tạo menu item
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_list_product, menu);


        return super.onCreateOptionsMenu(menu);
    }



}
