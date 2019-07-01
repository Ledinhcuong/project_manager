package vn.edu.tdc.mymanager.adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.tdc.mymanager.R;
import vn.edu.tdc.mymanager.model.Function;
import vn.edu.tdc.mymanager.model.Product;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ProductViewHolder> {



    // Các thuộc tính
    private ArrayList<Product> listProducts;  // Danh sách
    private Context context;   // Màn hình hiện tại
    private LayoutInflater inflater;


    // Phương thức khởi tạo
    public AdapterProduct(Context context, ArrayList<Product> list) {
        this.context = context;
        this.listProducts = list;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        // Liên kết với giao diện item
        View itemView = inflater.inflate(R.layout.item_list_product, viewGroup, false);
        return new ProductViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {

        Product item = listProducts.get(i);

        // Đưa dữ liệu lên hiển thị
        productViewHolder.tvNameProduct.setText(item.getNameProduct());


    }

    @Override
    public int getItemCount() {
        return listProducts.size();
    }


    // Tạo ra một view holder
    class ProductViewHolder  extends RecyclerView.ViewHolder {

        TextView tvNameProduct;
        ImageButton btnRemove;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameProduct  = (TextView) itemView.findViewById(R.id.item_list_product_name);
            btnRemove = (ImageButton) itemView.findViewById(R.id.btn_item_list_product_remove);
        }
    }
}


