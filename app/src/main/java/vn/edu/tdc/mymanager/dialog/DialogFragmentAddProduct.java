package vn.edu.tdc.mymanager.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import vn.edu.tdc.mymanager.BuildConfig;
import vn.edu.tdc.mymanager.R;

import static android.app.Activity.RESULT_OK;

public class DialogFragmentAddProduct extends DialogFragment {




    // Properties
    ImageButton btnClose;
    Button btnSelectPhoto1;
    ImageView imgPhoto1;
    Button btnCamera1;


    static int PhotoToRequest1 = 1;
    static  int CAMERA_REQUEST1 = 2;
    String imagePath;


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

        setControl(view);
        setEvent();

    }

    private void setEvent() {

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        // Sự kiện nút chọn ảnh 1
        btnSelectPhoto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                // Chỉ định kiểu file cần hiển thị
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                // Hiển thị các ứng dụng có thể xử lý ảnh
                startActivityForResult(Intent.createChooser(intent, "Chọn hỉnh ảnh"), PhotoToRequest1);
            }
        });

        // Sự kiện nút camera 1
        btnCamera1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(getActivity(), BuildConfig.APPLICATION_ID + ".provider", createImageFile()));

                    startActivityForResult(intent, CAMERA_REQUEST1);

                } catch (IOException e) {

                    e.printStackTrace();

                    Log.d("vip", e + "Loi la gi ");

                }



            }
        });


    }

    // Set control
    public void setControl(View  view) {
        
        btnClose = (ImageButton) view.findViewById(R.id.btn_fragment_add_product_close);
        btnSelectPhoto1 = (Button) view.findViewById(R.id.btnChonAnhDialog1);
        imgPhoto1 = (ImageView) view.findViewById(R.id.imv_fragment_add_product_photo1);
        btnCamera1 = (Button) view.findViewById(R.id.btn_fragment_add_product_camera1);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        // Nhận kết quả chọn ảnh từ thư viện 1
        if (requestCode == PhotoToRequest1 && resultCode == RESULT_OK) {

            // Lấy địa chỉ hình ảnh
            Uri uri = data.getData();

            // Lưu lại địa chỉ hình ảnh được chọn
            imagePath = getRealPathImage(getContext(), uri);

            // Đưa ảnh lên hiển thị
            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                imgPhoto1.setImageBitmap(bitmap);


            } catch (IOException e) {

                // Thông báo lỗi nếu không hiển thị được hình ảnh
                final AlertDialog.Builder errorDialog = new AlertDialog.Builder(getContext());
                errorDialog.setTitle("Lỗi khi chọn ảnh");
                errorDialog.setMessage("Đã có lỗi khi chúng tôi truy cập ảnh từ thư viện của bạn :( ");
                errorDialog.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        errorDialog.setCancelable(true);
                    }
                });

                errorDialog.show();

                e.printStackTrace();
            }

            // Nhận dữ liệu từ camera 1

            if (requestCode == CAMERA_REQUEST1  && resultCode == RESULT_OK) {
               Log.d("vip", "Đã chụp từ camera");
                imgPhoto1.setImageURI(Uri.parse(imagePath));
            }

        }


    }





    // Phương thức chuyển đổi uri ra đường dẫn hình ảnh
    public static String getRealPathImage(Context context, Uri uri) {
        String filePath = "";
        String wholeID = DocumentsContract.getDocumentId(uri);

        // Split at colon, use second item in the array
        String id = wholeID.split(":")[1];

        String[] column = {MediaStore.Images.Media.DATA};

        // where id is equal to
        String sel = MediaStore.Images.Media._ID + "=?";

        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                column, sel, new String[]{id}, null);

        int columnIndex = cursor.getColumnIndex(column[0]);

        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex);
        }
        cursor.close();
        return filePath;
    }


    private File createImageFile() throws IOException {

        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        //This is the directory in which the file will be created. This is the default location of Camera photos
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM), "Camera");
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for using again
        // cameraFilePath = "file://" + image.getAbsolutePath();
        imagePath = image.getAbsolutePath();
        return image;
    }





}
