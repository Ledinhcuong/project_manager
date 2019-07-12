package vn.edu.tdc.mymanager;

import android.app.Application;
import android.widget.Toast;

public class MyApp  extends Application {


    // Show toast
    public void showToast(final String message, final  int duration) {

        Toast.makeText(getApplicationContext(), message, duration).show();
    }




}
