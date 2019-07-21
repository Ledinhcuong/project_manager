package vn.edu.tdc.mymanager.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;

import vn.edu.tdc.mymanager.R;
import vn.edu.tdc.mymanager.adapter.AdapterListImage;
import vn.edu.tdc.mymanager.adapter.AdapterTask;
import vn.edu.tdc.mymanager.model.ImageProduct;
import vn.edu.tdc.mymanager.model.Task;

public class ProductDetailsActivity extends BaseActivity {

    RecyclerView rvListImages;
    AdapterListImage adapterListImage;
    ArrayList<ImageProduct> listImages;

    RecyclerView rvListTask;
    ArrayList<Task> listTasks;
    AdapterTask adapterTask;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        initToolBar();
        
        setControl();

        importImage();

        importTask();
    }

    private void importTask() {

        listTasks.add(new Task("Công việc 1"));
        listTasks.add(new Task("Công việc 2"));
        listTasks.add(new Task("Công việc 3"));
        listTasks.add(new Task("Công việc 4"));

        adapterTask.notifyDataSetChanged();

    }

    private void importImage() {

        listImages.add(new ImageProduct("Hinh 1"));
        listImages.add(new ImageProduct("Hinh 1"));
        listImages.add(new ImageProduct("Hinh 1"));
        listImages.add(new ImageProduct("Hinh 1"));
        adapterListImage.notifyDataSetChanged();

    }

    private void setControl() {

        rvListImages = (RecyclerView) findViewById(R.id.rv_product_details_list_image);
        listImages = new ArrayList<>();
        adapterListImage = new AdapterListImage(ProductDetailsActivity.this, listImages);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ProductDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false);

        rvListImages.setLayoutManager(linearLayoutManager);
        rvListImages.setAdapter(adapterListImage);


        listTasks = new ArrayList<>();
        rvListTask = (RecyclerView) findViewById(R.id.rv_product_details_list_task);
        adapterTask = new AdapterTask(ProductDetailsActivity.this, listTasks);
        LinearLayoutManager layoutManager = new LinearLayoutManager(ProductDetailsActivity.this, LinearLayoutManager.VERTICAL, false);

        rvListTask.setLayoutManager(layoutManager);
        rvListTask.setAdapter(adapterTask);


    }




    // Get calling intent
    static public Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, ProductDetailsActivity.class);
        return intent;
    }

    // Init tool bar
    public void initToolBar() {

        getSupportActionBar().setTitle("Chi tiết");

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

        }

        return true;
    }
}
