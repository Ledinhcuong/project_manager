package vn.edu.tdc.mymanager.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import vn.edu.tdc.mymanager.fragment.HomeFragment;
import vn.edu.tdc.mymanager.fragment.InventoryManagementFragment;
import vn.edu.tdc.mymanager.R;
import vn.edu.tdc.mymanager.fragment.SettingsAppFragment;
import vn.edu.tdc.mymanager.fragment.StaftManagerFragment;

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    FragmentTransaction ft;

    static int READ_EXTERNAL_STORE = 1;  // Mã cấp bộ nhớ
    static int CAMERA_PERMISSION = 2;
    static int WRITE_EXTERNAL_STORE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init tool bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);




        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        setFragment(); // Set giao diện home


        // Navigation Drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        requirePermision();
    }

    // Phương thức xin cấp quyền trong ứng dụng
    private void requirePermision() {

        // Xin cấp quyền để đọc bộ nhớ (Để lấy ảnh)
        if (ContextCompat.checkSelfPermission(HomeActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(HomeActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                // Yêu cầu cấp quyền nếu đã bị từ chối một lần

                // Hiển thị dialog giải thích (Dialog viết trực tiếp)
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomeActivity.this)
                        .setTitle("Lưu ý")
                        .setMessage("Ứng dụng sẽ không thể hoạt động nếu không được cấp quyền !");

                alertDialog.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {  // Sự kiện nút đồng ý
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {

                        // Yêu cầu cấp quyền một lần nữa
                        ActivityCompat.requestPermissions(HomeActivity.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORE);
                    }
                });

                alertDialog.show(); // Hiển thị dialog


            } else {  // Yêu cầu cấp quyền lần đầu

                ActivityCompat.requestPermissions(HomeActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORE);

            }
        }


    }

    // Require permission write external store
    public  void requirePermissionWriteExternalStore() {

        // Xin cấp ghi de luu anh
        if (ContextCompat.checkSelfPermission(HomeActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(HomeActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Yêu cầu cấp quyền nếu đã bị từ chối một lần

                // Hiển thị dialog giải thích (Dialog viết trực tiếp)
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomeActivity.this)
                        .setTitle("Lưu ý")
                        .setMessage("Ứng dụng sẽ không thể hoạt động nếu không được cấp quyền !");

                alertDialog.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {  // Sự kiện nút đồng ý
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {

                        // Yêu cầu cấp quyền một lần nữa
                        ActivityCompat.requestPermissions(HomeActivity.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORE);
                    }
                });

                alertDialog.show(); // Hiển thị dialog


            } else {  // Yêu cầu cấp quyền lần đầu

                ActivityCompat.requestPermissions(HomeActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORE);

            }
        }
    }

    // Require permission camera
    public void requirePermissionCamera() {

        // Xin cấp quyền để đọc bộ nhớ (Để lấy ảnh)
        if (ContextCompat.checkSelfPermission(HomeActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(HomeActivity.this, Manifest.permission.CAMERA)) {

                // Yêu cầu cấp quyền nếu đã bị từ chối một lần

                // Hiển thị dialog giải thích (Dialog viết trực tiếp)
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomeActivity.this)
                        .setTitle("Lưu ý")
                        .setMessage("Ứng dụng sẽ không thể hoạt động nếu không được cấp quyền !");

                alertDialog.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {  // Sự kiện nút đồng ý
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {

                        // Yêu cầu cấp quyền một lần nữa
                        ActivityCompat.requestPermissions(HomeActivity.this,
                                new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION);
                    }
                });

                alertDialog.show(); // Hiển thị dialog


            } else {  // Yêu cầu cấp quyền lần đầu

                ActivityCompat.requestPermissions(HomeActivity.this,
                        new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION);

            }
        }

    }


    // Hành động khi nhấn nút back
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        Fragment fragment = null;

        switch (id) {

            case R.id.nav_home: // Màn hình home

                fragment = new HomeFragment();
                break;

            case R.id.nav_inventory:
                fragment = new InventoryManagementFragment();
                break;

            case R.id.nav_staft_manager:
                fragment = new StaftManagerFragment();
                break;

            case R.id.nav_settings:
                fragment = new SettingsAppFragment();
                break;

                default:
                    fragment = new HomeFragment();
        }

        // Thực hiện chuyển đổi giữa các fragment
        if (fragment != null) {

            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_home, fragment).addToBackStack("").commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Set fragment
    public void setFragment() {

        Fragment fragment = null;
        fragment = new HomeFragment();  // Khởi tạo fragment màn hình chủ

        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_home, fragment);
        ft.commit();

    }

    // Get caing intent
    public static  Intent getCallingIntent(Context context) {

        Intent intent = new Intent(context, HomeActivity.class);
        return intent;
    }


    // Xử lý kết quả cấp quyền có được cấp hay là không
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        // Xử lý kết quả của việc cấp quyền đọc bộ nhớ
        if (requestCode == READ_EXTERNAL_STORE) {

            // Kiểm tra kết quả của việc cấp quyền
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                // Xin tiếp quyền camera
                requirePermissionCamera();

            } else { // Không được cấp phép

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomeActivity.this)
                        .setTitle("Thông báo")
                        .setMessage("Ứng dụng sẽ đóng khi bị từ chối cấp quyền !")
                        .setPositiveButton("Đồng ý", null);

                alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        finish();
                    }
                });

                alertDialog.show();
            }

        }

        // Xu ly ket qua quyen su dung camera
        if (requestCode == CAMERA_PERMISSION) {

            // Kiểm tra kết quả của việc cấp quyền
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                // Xin quyen ghi bo nho
                requirePermissionWriteExternalStore();

            } else { // Không được cấp phép

                finish(); // Dong ung dung
            }

        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
