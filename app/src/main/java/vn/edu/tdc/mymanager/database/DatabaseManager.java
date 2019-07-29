package vn.edu.tdc.mymanager.database;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import vn.edu.tdc.mymanager.R;
import vn.edu.tdc.mymanager.Staft;
import vn.edu.tdc.mymanager.model.Inventory;

public class DatabaseManager extends SQLiteOpenHelper {

    private static String DbName = "EslManager.sqlite";   // Tên database

    private static String DbPath = "";  // Đường dẫn chứa file database

    private static final int DbVersion = 1;   // Phiên bản database

    private SQLiteDatabase myDatabase;
    private Context context;           // Lấy màn hình hiện tại
    private boolean update = false;   // Biến thông báo cập nhật database


    /*
    Phương thức khởi tạo database
    Chức năng: Kiểm tra sự tồn tại của database trong bộ nhớ và sao chép database vào bộ nhớ nếu chưa có
     */
    public DatabaseManager(Context context) {

        super(context, DbName, null, DbVersion);

        // Đường dẫn lưu database
        if (Build.VERSION.SDK_INT >= 17) {

            DbPath = context.getApplicationInfo().dataDir + "/databases/";

        } else {

            DbPath = "/data/data/" + context.getPackageName() + "/databases/";
        }


        this.context = context;

        // Di chuyển file database vào thư mục chứa database
        copyDataBase();
        this.getReadableDatabase();


    }


    /*
    Phương thức onCreate
    Chức năng: Tạo bảng dữ liệu
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Các bảng đã được tạo sẵn

    }

    // Phương thức cạp nhật phiên bản database khi ta thay file
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        if (newVersion > oldVersion) {
            update = true;
        }


    }

    // Phương thức cập nhât database nếu ta thảy đổi file database
    public void updateDataBase() throws IOException {

        if (update) {
            File dbFile = new File(DbPath + DbName);

            // Kiểm tra file database đã tồn tại chưa nếu tồn tại thì xóa nó
            if (dbFile.exists()) {
                dbFile.delete();
            }

            // Tiến hành copy file database vào thư mục lưu trữ
            copyDataBase();

            update = false;
        }
    }

    // Phương thức kiểm tra sự tồn tại của file database
    public boolean checkDataBase() {
        File dbFile = new File(DbPath + DbName);
        return dbFile.exists();
    }


    // Phương thức xử lý copy database
    private void copyDataBase() {

        // Nếu chưa có thì tiến hành copy file database
        if (!checkDataBase()) {

            this.getReadableDatabase();
            this.close();

            try {

                // Thực hiện copy file
                copyDBFile();

            } catch (IOException mIOException) {

                throw new Error("ErrorCopyingDataBase");
            }
        }
        Log.i("test", "Copy database");
    }

    // Phương thức tiến hành copy file database
    private void copyDBFile() throws IOException {
        InputStream mInput =  context.getAssets().open(DbName);

        OutputStream mOutput = new FileOutputStream(DbPath + DbName);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0) {
            mOutput.write(mBuffer, 0, mLength);
        }

        mOutput.flush();
        mOutput.close();
        mInput.close();

        Log.i("test", "Copy file");
    }

    // Phương thức mở database để đọc
    public boolean openDataBase() throws SQLException {
        myDatabase = SQLiteDatabase.openDatabase(DbPath + DbName, null, SQLiteDatabase.CREATE_IF_NECESSARY);

        Log.i("test", "Open database");
        return myDatabase != null;

    }


    /*
    Các phương thức thao tác với datatabase
     */


    /*---------------------- Table Khu Vực ---------------------------*/

    /*
    // Function Get All Area
    // Lấy danh sách các khu vực
     */
    public ArrayList<Inventory> getAllArea() {

        ArrayList<Inventory> listAreas = new ArrayList<>();

        // Viết câu truy vấn
        String query = "SELECT * FROM Area";
        myDatabase = this.getReadableDatabase();

        // Tạo biến con trỏ để truy vấn và lấy dữ liệu
        Cursor cursor = myDatabase.rawQuery(query, null);
        cursor.moveToFirst();

        // Kiểm tra có lấy được dữ liệu không
        if (cursor.getCount() == 0) {

            // Hiển thị dialog thông báo lỗi truy xuất dữ liệu
            final Dialog dialogError = new Dialog(context);
            dialogError.setContentView(R.layout.dialog_error);  // Set giao diện cho dialog

            Button btnAccept = (Button) dialogError.findViewById(R.id.btnAcceptError);

            // Xử lý sự kiện cho nó
            btnAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialogError.cancel();
                }
            });


            dialogError.show();  // Hiển thị dialog
        }


        // Đổ dữ liệu vào vào mảng
        while (cursor.isAfterLast() == false) {

            Inventory inventory = new Inventory(cursor.getInt(0), cursor.getString(1), cursor.getString(2));

            listAreas.add(inventory);

            cursor.moveToNext();  // Di chuyển đến dòng tiếp theo

        }

        Log.d("vip", "list area " + listAreas.size());
        // Trả về danh sách dữ liêu
        return listAreas;

    }

    /*
    // Function Get List Area To Date
    // Lấy danh sách khu vực theo ngày
     */
    public ArrayList<Inventory> getListAreaToDate(String date) {

        // Khởi tạo danh sách
        ArrayList<Inventory> listAreas = new ArrayList<>();

        date = "%" + date + "%";

        myDatabase = this.getReadableDatabase();


        // Viết câu truy vấn
        Cursor cursor = myDatabase.query("Area", null, "Ngay Like ?", new String[]{date}, null, null, null);


        cursor.moveToFirst();

        // Đổ dữ liệu vào vào mảng
        while (cursor.isAfterLast() == false) {

            Inventory inventory = new Inventory(cursor.getInt(0), cursor.getString(1), cursor.getString(2));

            listAreas.add(inventory);

            cursor.moveToNext();  // Di chuyển đến dòng tiếp theo
        }

        // Trả về danh sách kết quả
        return listAreas;
    }


    /*
    // Function Add New Area
    // Thêm khu vực vào danh sách
     */
    public void addNewArea(String nameArea, String dateCreate) {


        myDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("TenKV", nameArea);
        values.put("Ngay", dateCreate);

        myDatabase.insert("Area", null, values);

        // Đóng cơ sỏ dữ liệu
        myDatabase.close();

    }

    /*
    // Funtion Edit Area
    // Chỉnh sửa thông tin khu vực
     */
    public void editArea(int maKV, String nameArea) {

        myDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("TenKV", nameArea);
        myDatabase.update("Area", values, "MaKV = ?", new String[]{String.valueOf(maKV)});

        // Đóng cơ sở dữ liệu
        myDatabase.close();
    }


    /*
    //
    // Lấy id cho khu vực vừa tạo
     */
    public int getIdArea(String nameNewArea) {

        int value;
        myDatabase = this.getReadableDatabase();

        Cursor cursor = myDatabase.query("Area", null, "TenKV = ?", new String[]{nameNewArea}, null, null, null);

        if (cursor == null) {

            return -1;

        }

        cursor.moveToFirst();

        value = cursor.getInt(0);

        return value;

    }

    // ----------------------- Bảng nhân viên -----------------------------//

    /*
    //
    // Lấy danh sách nhân viên
     */
    public ArrayList<Staft>  getAllListStaft() {

        ArrayList<Staft> listStafts = new ArrayList<>();

        // Viết câu truy vấn
        String query = "SELECT * FROM NhanVien";
        myDatabase = this.getReadableDatabase();

        // Tạo biến con trỏ để truy vấn và lấy dữ liệu
        Cursor cursor = myDatabase.rawQuery(query, null);
        cursor.moveToFirst();

        // Kiểm tra có lấy được dữ liệu không
        if (cursor.getCount() == 0) {

            // Hiển thị dialog thông báo lỗi truy xuất dữ liệu
            final Dialog dialogError = new Dialog(context);
            dialogError.setContentView(R.layout.dialog_error);  // Set giao diện cho dialog

            Button btnAccept = (Button) dialogError.findViewById(R.id.btnAcceptError);

            // Xử lý sự kiện cho nó
            btnAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialogError.cancel();
                }
            });


            dialogError.show();  // Hiển thị dialog
        }


        // Đổ dữ liệu vào vào mảng
        while (cursor.isAfterLast() == false) {

            Staft staft = new Staft(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

            listStafts.add(staft);

            cursor.moveToNext();  // Di chuyển đến dòng tiếp theo

        }

        // Trả về danh sách dữ liêu
        return listStafts;

    }




}
