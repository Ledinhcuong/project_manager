package vn.edu.tdc.mymanager.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.edu.tdc.mymanager.R;

public class DialogFragmentCalendar  extends DialogFragment {


    //Được dùng khi khởi tạo dialog mục đích nhận giá trị
    public static DialogFragmentCalendar newInstance() {

        DialogFragmentCalendar dialog = new DialogFragmentCalendar();

        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_fragment_select_calendar, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
