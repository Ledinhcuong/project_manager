package vn.edu.tdc.mymanager.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vn.edu.tdc.mymanager.AdapterListStaft;
import vn.edu.tdc.mymanager.R;
import vn.edu.tdc.mymanager.Staft;
import vn.edu.tdc.mymanager.activity.HomeActivity;
import vn.edu.tdc.mymanager.database.DatabaseManager;

public class StaftManagerFragment extends Fragment {


    RecyclerView rvListStaft;
    ArrayList<Staft> listStafts;
    AdapterListStaft adapterListStaft;
    DatabaseManager myData;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_staft_manager, container, false);
        setHasOptionsMenu(true);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initToolbar();

        setControl(view);

        importData();

    }

    private void importData() {

        // Thực hiện lấy dữ liệu từ cơ sở dữ liệu
        Thread thread = new Thread(new GetStaft());
        thread.start();



    }

    private class GetStaft implements Runnable{


        @Override
        public void run() { // Thực hiện lấy dữ liệu dưới background

            listStafts.addAll(myData.getAllListStaft());

            if(listStafts.size() > 0) {

                getActivity().runOnUiThread(new Runnable() {  // Gọi xư lý phía ui
                    @Override
                    public void run() {
                        adapterListStaft.notifyDataSetChanged();
                    }
                });
            }
        }

    }

    public void setControl(View view) {

        myData = new DatabaseManager(getContext());

        listStafts = new ArrayList<>();
        adapterListStaft = new AdapterListStaft(getContext(), listStafts);
        rvListStaft = (RecyclerView) view.findViewById(R.id.rv_fragment_staft_manager_list_staft);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvListStaft.setLayoutManager(layoutManager);
        rvListStaft.setAdapter(adapterListStaft);

    }

    private void initToolbar() {

        ((HomeActivity) getActivity()).getSupportActionBar().setTitle("Quản lý nhân viên");

    }

    // Khởi tạo menu item
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_staft_manager, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
