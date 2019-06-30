package vn.edu.tdc.mymanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter;
import vn.edu.tdc.mymanager.adapter.AdapterFuncion;
import vn.edu.tdc.mymanager.model.Function;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    // Các thuộc tính
    RecyclerView rvListFunction;
    ArrayList<Function> listFunction;
    AdapterFuncion adapterFunction;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init tool bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        // Ánh xạ các thành phần
        setControl();

        // Import settings
        importSettings();

        setEvent();

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        // Navigation Drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setEvent() {

        adapterFunction.setOnItemClickedListener(new AdapterFuncion.OnItemClickedListener() {
            @Override
            public void onItemClick(int position) {

                switch (position) {

                    case 0:
                        Toast.makeText(getApplicationContext(), "Đã click", Toast.LENGTH_SHORT).show();

                        Intent intent = InventoryManagementAcitivity.getCallingIntent(MainActivity.this);
                        startActivity(intent);

                        break;
                    case 1:
                        break;
                        default:

                            break;

                }

            }
        });
    }

    private void importSettings() {

        listFunction.add(new Function("Quản lý kho", "Quản lý các kho hàng", R.drawable.tracauicon));
        listFunction.add(new Function("Quản lý kho", "Quản lý các kho hàng", R.drawable.tracauicon));
        listFunction.add(new Function("Quản lý kho", "Quản lý các kho hàng", R.drawable.tracauicon));

        adapterFunction.notifyDataSetChanged(); // Thông báo cập nhật lại dữ liệu

    }

    private void setControl() {

        rvListFunction = (RecyclerView) findViewById(R.id.rv_list_function);
        listFunction = new ArrayList<>();
        adapterFunction = new AdapterFuncion(this, listFunction);

        LinearLayoutManager layoutManagerFuntion = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        rvListFunction.setLayoutManager(layoutManagerFuntion);
        rvListFunction.setAdapter(new ScaleInAnimationAdapter(adapterFunction));

    }

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
