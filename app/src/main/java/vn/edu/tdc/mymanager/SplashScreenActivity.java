package vn.edu.tdc.mymanager;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

import vn.edu.tdc.mymanager.activity.HomeActivity;
import vn.edu.tdc.mymanager.database.DatabaseManager;

public class SplashScreenActivity extends AppCompatActivity {

    public static DatabaseManager myDatabase;  // Đối tượng quản lý database
    private SQLiteDatabase mDb;
    Boolean state = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Thực hiện đưa database tạo sẵn vào trong bộ nhớ thiết bị
        new AsyncTask<Void, Void, Void>() {  // Sử dụng asynstask để thực hiện các tiến trình chạy ngầm


            // Những công việc được thực hiện trước khi tiến trình được chạy
            @Override
            protected void onPreExecute() {

                // Khởi tạo database thiết lập file cơ sở dữ liệu vào hệ thống
                myDatabase = new DatabaseManager(SplashScreenActivity.this);
                super.onPreExecute();
            }

            // Kết quả thực hiện sau tiến trình đó hoàn thành (Đối với tác vụ chạy ngầm mọi xử lý giao diện khi ở background đều gây lỗi!)
            @Override
            protected void onPostExecute(Void aVoid) {

                // Tại phương thức này thì nó đã không còn chạy dưới nền nên có thể sử dụng và làm việc với giao diện


                // Thực hiện động bộ dữ liệu tại màn hình này sau khi có dữ liệu sẽ chuyển vào màn hình chính
                Intent intent = HomeActivity.getCallingIntent(SplashScreenActivity.this);
                startActivity(intent);
                finish(); // Hủy activity


                super.onPostExecute(aVoid);
            }

            // Những công việc cần thực hiện ngầm
            @Override
            protected Void doInBackground(Void... voids) {



                try {
                    myDatabase.updateDataBase();
                } catch (IOException mIOException) {


                    // Nếu xuất hiện lỗi chuyển sang màn hình thông báo lỗi
                    state = false;


                    throw new Error("UnableToUpdateDatabase");
                }

                try {
                    mDb = myDatabase.getWritableDatabase();
                } catch (SQLException mSQLException) {
                    throw mSQLException;

                }

                return null;
            }
        }.execute();



    }
}
