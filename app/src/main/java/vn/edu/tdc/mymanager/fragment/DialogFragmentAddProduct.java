package vn.edu.tdc.mymanager.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.edu.tdc.mymanager.R;

public class DialogFragmentAddProduct extends DialogFragment {


    //Được dùng khi khởi tạo dialog mục đích nhận giá trị
    public static DialogFragmentAddProduct newInstance() {
        DialogFragmentAddProduct dialog = new DialogFragmentAddProduct();

        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_add_product, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
