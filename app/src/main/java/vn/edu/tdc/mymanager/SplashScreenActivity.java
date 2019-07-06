package vn.edu.tdc.mymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vn.edu.tdc.mymanager.activity.HomeActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Thực hiện động bộ dữ liệu tại màn hình này sau khi có dữ liệu sẽ chuyển vào màn hình chính
        Intent intent = HomeActivity.getCallingIntent(SplashScreenActivity.this);
        startActivity(intent);
        finish(); // Hủy activity

    }
}
