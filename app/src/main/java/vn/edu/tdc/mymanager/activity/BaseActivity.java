package vn.edu.tdc.mymanager.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import vn.edu.tdc.mymanager.model.Contants;

// Lop co co cho cac activity

public abstract class BaseActivity  extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(Contants.KEY_ACTIVITY_START, "onActivityStarted: " + getLocalClassName());

        // Show in log: onActivityStarted: Se nhan biet nao activity nao dang duoc khoi chay


    }



    protected void addFragment(int containerViewId, Fragment fragment) {
        if(getSupportFragmentManager().isDestroyed()) {
            return;
        }
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.add(containerViewId, fragment);
        transaction.commit();
    }

    protected void addFragment(int containerViewId, Fragment fragment, String tag) {
        if(getSupportFragmentManager().isDestroyed()) {
            return;
        }
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.add(containerViewId, fragment, tag);
        transaction.commit();
    }


    protected void replaceFragment(int containerViewId, Fragment fragment) {
        if(getSupportFragmentManager().isDestroyed()) {
            return;
        }
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.replace(containerViewId, fragment);
        //transaction.commit();
        try {
            transaction.commitAllowingStateLoss();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected void replaceFragment(int containerViewId, Fragment fragment, String TAG) {
        if(getSupportFragmentManager().isDestroyed()) {
            return;
        }
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.replace(containerViewId, fragment, TAG);
        //transaction.commit();
        try {
            transaction.commitAllowingStateLoss();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


}
