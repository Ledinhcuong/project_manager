package vn.edu.tdc.mymanager.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import vn.edu.tdc.mymanager.activity.HomeActivity;
import vn.edu.tdc.mymanager.activity.ListProductActivity;
import vn.edu.tdc.mymanager.R;
import vn.edu.tdc.mymanager.adapter.AdapterArea;
import vn.edu.tdc.mymanager.database.DatabaseManager;
import vn.edu.tdc.mymanager.dialog.DialogFragmentAddProduct;
import vn.edu.tdc.mymanager.dialog.DialogFragmentCalendar;
import vn.edu.tdc.mymanager.model.Inventory;

public class InventoryManagementFragment extends Fragment {

    RecyclerView rvListArea;
    ArrayList<Inventory> listAreas;
    AdapterArea adapterArea;
    TextView tvTitleList;

    LinearLayout btnSelectCalendar;

    DatabaseManager myData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inventory_management, container, false);
        setHasOptionsMenu(true);
        return  view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


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
              Intent intent = ListProductActivity.getCallingIntent(getContext());
              startActivity(intent);
            }
        });


        // Sự kiện khi click vào lịch
        btnSelectCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                DialogFragmentCalendar dialogFragmentCalendar = DialogFragmentCalendar.newInstance();
                dialogFragmentCalendar.OnButtonClick(new DialogFragmentCalendar.OnButtonClickListenner() {
                    @Override
                    public void OnButtonSlectClick(String calendar) {

                        /*
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        Date date = new Date();
                        date.setTime(calendar);  // Chuyển milisendconds to date
                        */

                        /*
                        String dateSelect = format.format(date);
                        */

                        // Thực hiện truy vấn cơ sở dữ liệu
                        listAreas.clear();  // Xóa dữ liệu cũ

                        Toast.makeText(getActivity(), calendar, Toast.LENGTH_SHORT).show();
                        listAreas.addAll(myData.getListAreaToDate(calendar));

                        if (listAreas.size() > 0) {
                            tvTitleList.setText("Danh sách khu vực (" + calendar + ")");

                        } else {
                            tvTitleList.setText("Không có khu vực nào");
                        }

                        adapterArea.notifyDataSetChanged();

                    }

                    @Override
                    public void OnButtonCancelClick() {

                    }
                });

                dialogFragmentCalendar.show(fm, null);

            }
        });

    }

    private void importData() {

        // Lấy dữ liệu trong cơ sở dữ liệu
        new Thread(new Runnable() {  // Chay duoi background
            @Override
            public void run() {

                // Lay danh sach trong co so du lieu
                listAreas.addAll(myData.getAllArea());

                // Chay len giao dien UI
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        adapterArea.notifyDataSetChanged();
                    }
                });


            }
        }).start();



        adapterArea.notifyDataSetChanged();

    }

    private void setControl() {

        myData = new DatabaseManager(getContext());  // Khỏi tạo đối tượng quản lý database

        rvListArea = (RecyclerView) getActivity().findViewById(R.id.rv_list_area);
        tvTitleList = (TextView) getActivity().findViewById(R.id.tv_inventory_management_title_list);
        listAreas = new ArrayList<>();

        adapterArea = new AdapterArea(getContext(), listAreas);

        // Layout quản lý danh sách
        // Tạo layout manager quản lý recycler view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        rvListArea.setLayoutManager(linearLayoutManager);
        rvListArea.setAdapter(adapterArea);

        btnSelectCalendar = (LinearLayout) getActivity().findViewById(R.id.lnl_fragment_inventory_management_select_calendar);

    }

    private void initToolbar() {

        ((HomeActivity) getActivity()).getSupportActionBar().setTitle("Danh sách");


    }

    // Bắt sự kiên trên tool bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case  R.id.add_inventory:  // Sự kiện khi nhấn nút thêm

                // Sử dụng bottom sheet dialog (Gọi theo cách sử dụng dialog)
                View view = getLayoutInflater().inflate(R.layout.botton_sheet_dialog_add_area, null);

                final BottomSheetDialog dialog = new BottomSheetDialog(getContext());
                dialog.setContentView(view);

                // Ánh xạ cá thành phần trong dialog
                final EditText edtId = (EditText) dialog.findViewById(R.id.edt_dialog_add_area_id);
                edtId.setInputType(InputType.TYPE_CLASS_TEXT);   // Set type

                final EditText edtNameArea = (EditText) dialog.findViewById(R.id.edt_dialog_add_area_name) ;
                edtNameArea.setInputType(InputType.TYPE_CLASS_TEXT);


                Button btnAdd = dialog.findViewById(R.id.btn_dialog_add_area_add);

                // Sự kiện nút thêm trong dialog
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        String nameArea = edtNameArea.getText() + "";

                        if (nameArea.trim().compareTo("") == 0) {
                            Toast.makeText(getContext(), "Vui lòng nhập tên khu vực", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date date = new Date();


                        String dateToday = dateFormat.format(date);  // Lấy ngày hôm nay

                        myData.addNewArea(nameArea, dateToday);  // thêm vào trong cơ sở dữ liệu
                        int newID = myData.getIdArea(nameArea);

                        listAreas.add(new Inventory(newID, nameArea, dateToday));
                        adapterArea.notifyItemInserted(listAreas.size() - 1);  // Cập nhật danh sách đang hiển thỉ


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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_inventory, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }






}
