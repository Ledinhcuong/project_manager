package vn.edu.tdc.mymanager.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter;
import vn.edu.tdc.mymanager.R;
import vn.edu.tdc.mymanager.activity.HomeActivity;
import vn.edu.tdc.mymanager.adapter.AdapterFuncion;
import vn.edu.tdc.mymanager.model.Function;

public class HomeFragment extends Fragment {


    // Các thuộc tính
    RecyclerView rvListFunction;
    ArrayList<Function> listFunction;
    AdapterFuncion adapterFunction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initToolbar();

        // Ánh xạ các thành phần
        setControl();


        // Import settings
        importSettings();

        // Set sự kiện
        setEvent();

    }

    private void initToolbar() {

        ((HomeActivity) getActivity()).getSupportActionBar().setTitle("Màn hình chủ");


    }


    private void setControl() {

        rvListFunction = (RecyclerView) getActivity().findViewById(R.id.rv_list_function);
        listFunction = new ArrayList<>();
        adapterFunction = new AdapterFuncion(getContext(), listFunction);

        LinearLayoutManager layoutManagerFuntion = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        rvListFunction.setLayoutManager(layoutManagerFuntion);
        rvListFunction.setAdapter(new ScaleInAnimationAdapter(adapterFunction));

    }


    private void importSettings() {

        listFunction.add(new Function("Quản lý kho", "Quản lý các kho hàng", R.drawable.tracauicon));
        listFunction.add(new Function("Quản lý nhân viên", "Quản lý các nhân viên", R.drawable.tuvungicon));
        listFunction.add(new Function("Cài đặt", "Thiết lập các tùy chọn", R.drawable.setting_icon));
        adapterFunction.notifyDataSetChanged(); // Thông báo cập nhật lại dữ liệu

    }


    private void setEvent() {

        adapterFunction.setOnItemClickedListener(new AdapterFuncion.OnItemClickedListener() {
            @Override
            public void onItemClick(int position) {

                switch (position) {

                    case 0:
                        Fragment fragment = new InventoryManagementFragment();
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.content_home, fragment).addToBackStack(getTag()).commit();
                        // Add to back stack: back old fragment went press back
                        break;
                    case 1:
                        Fragment fragment1 = new StaftManagerFragment();
                        FragmentTransaction fragmentTransaction1 = getFragmentManager().beginTransaction();
                        fragmentTransaction1.replace(R.id.content_home, fragment1).addToBackStack(getTag()).commit();
                        break;

                    case 2:
                        Fragment fragment2 = new SettingsAppFragment();
                        FragmentTransaction fragmentTransaction2 = getFragmentManager().beginTransaction();
                        fragmentTransaction2.replace(R.id.content_home, fragment2).addToBackStack(getTag()).commit();

                        break;

                    default:
                        break;

                }

            }
        });
    }
}
