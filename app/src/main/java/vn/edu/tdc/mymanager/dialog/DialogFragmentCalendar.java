package vn.edu.tdc.mymanager.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import vn.edu.tdc.mymanager.R;

public class DialogFragmentCalendar  extends DialogFragment {


    Button btnSelect;
    Button btnClose;
    CalendarView clvCalendar;
    String dateSelect = "";

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

        btnSelect = (Button) view.findViewById(R.id.btn_diloag_fragment_select_calendar_select);
        btnClose = (Button) view.findViewById(R.id.btn_dilog_fragment_select_calendar_close);
        clvCalendar = (CalendarView) view.findViewById(R.id.clv_dialog_fragment_select_calendar);

        // Lấy ngày hiện tại
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        dateSelect = dateFormat.format(date);

        setEvent();
    }

    private OnButtonClickListenner onButtonClickListenner;   // Tạo một biến interface

    // Tạo inter face
    public interface OnButtonClickListenner {
        public void OnButtonSlectClick(String calendar);
        public void OnButtonCancelClick();

    }

    public void OnButtonClick(OnButtonClickListenner mlistener) {
        this.onButtonClickListenner = mlistener;

    }

    private void setEvent() {

        clvCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {

                dateSelect = i2 + "/" + i1 + "/" + i;

                /*
                Date date2 = new Date();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    date2 = format.parse(charDate);
                    clvCalendar.setDate(date2.getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                */

            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();

                onButtonClickListenner.OnButtonSlectClick(dateSelect);

            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }


}
