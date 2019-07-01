package vn.edu.tdc.mymanager;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter;
import vn.edu.tdc.mymanager.adapter.AdapterArea;

public class InventoryManagementAcitivity extends AppCompatActivity {

    RecyclerView rvListArea;
    ArrayList<Inventory> listAreas;
    AdapterArea adapterArea;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_management);

        initToolbar();

        // Anh xạ các thành phần
        setControl();

        // Đưa dữ liệu vào hiển thị
        importData();

        // Set event
        setEvent();




    }

    private void setEvent() {

        // Sự kiện khi click vào một item trong list
        adapterArea.setOnItemClickedListener(new AdapterArea.OnItemClickedListener() {
            @Override
            public void onItemClick(int position) {
              Intent intent = ListProductActivity.getCallingIntent(InventoryManagementAcitivity.this);
              startActivity(intent);
            }
        });

    }

    private void importData() {

        listAreas.add(new Inventory("KH01", "Khu vực 1"));
        listAreas.add(new Inventory("KH02", "Khu vực 2"));
        listAreas.add(new Inventory("KH03", "Khu vực 3"));
        listAreas.add(new Inventory("KH04", "Khu vực 4"));

        adapterArea.notifyDataSetChanged();

    }

    private void setControl() {
        rvListArea = (RecyclerView) findViewById(R.id.rv_list_area);
        listAreas = new ArrayList<>();

        adapterArea = new AdapterArea(this, listAreas);

        // Layout quản lý danh sách
        // Tạo layout manager quản lý recycler view
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);

        rvListArea.setLayoutManager(gridLayoutManager);
        rvListArea.setAdapter(adapterArea);


    }

    private void initToolbar() {
        // Set title
        getSupportActionBar().setTitle("Danh sách kho");

        // Hiển thị nút back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    // Get calling intent
    static  public Intent getCallingIntent(Context context) {

        Intent intent = new Intent(context, InventoryManagementAcitivity.class);

        return intent;

    }

    // Bắt sự kiên trên tool bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:  // Sự kiện nút back

                // Hủy màn hình
                onBackPressed();
                return true;

            case  R.id.add_inventory:

                View view = getLayoutInflater().inflate(R.layout.botton_sheet_dialog_add_area, null);

                final BottomSheetDialog dialog = new BottomSheetDialog(this);
                dialog.setContentView(view);

                // Ánh xạ cá thành phần trong dialog
                final EditText edtId = (EditText) dialog.findViewById(R.id.edt_dialog_add_area_id);
                final EditText edtNameArea = (EditText) dialog.findViewById(R.id.edt_dialog_add_area_name) ;

                Button btnAdd = dialog.findViewById(R.id.btn_dialog_add_area_add);

                // Sự kiện nút thêm trong dialog
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        // Lấy dữ liệu được điền
                        String id = edtId.getText() + "";
                        String nameArea = edtNameArea.getText() + "";

                        if (id.trim().compareTo("")  == 0 || nameArea.trim().compareTo("") == 0) {
                            Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ các thông tin", Toast.LENGTH_SHORT).show();
                            return;
                        }


                        listAreas.add(new Inventory(id, nameArea));

                        adapterArea.notifyItemInserted(listAreas.size() - 1);

                        dialog.dismiss();

                    }
                });

                Button btnCancel = dialog.findViewById(R.id.btn_dialog_add_area_cancel);
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();
                    }
                });

                dialog.show();

                break;
        }

        return true;
    }


    // Khởi tạo menu item
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_inventory, menu);


        return super.onCreateOptionsMenu(menu);
    }






}
