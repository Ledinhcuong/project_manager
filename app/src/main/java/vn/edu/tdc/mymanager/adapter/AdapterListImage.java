package vn.edu.tdc.mymanager.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import vn.edu.tdc.mymanager.R;
import vn.edu.tdc.mymanager.model.ImageProduct;

public class AdapterListImage extends  RecyclerView.Adapter<AdapterListImage.ImageViewHolder> {


    // Các thuộc tính
    private ArrayList<ImageProduct> listImages;
    private Context context;
    private LayoutInflater inflater;


    // Phương thức khởi tạo
    public AdapterListImage(Context context, ArrayList<ImageProduct> list) {

        this.context = context;
        this.listImages = list;
        inflater = LayoutInflater.from(context);


    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = inflater.inflate(R.layout.item_list_image, viewGroup,false);
        return new ImageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder imageViewHolder, int i) {

        // Đưa dữ liệu lên hiển thị


    }

    @Override
    public int getItemCount() {
        return listImages.size();
    }

    // View holder
    class  ImageViewHolder extends RecyclerView.ViewHolder {

        private ImageView imvImage;

        public ImageViewHolder (@NonNull View itemView) {

            super(itemView);
            imvImage = (ImageView) itemView.findViewById(R.id.imv_item_list_image_image);

        }

    }

}
