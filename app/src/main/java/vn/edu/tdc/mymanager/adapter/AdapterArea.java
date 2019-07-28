package vn.edu.tdc.mymanager.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.edu.tdc.mymanager.model.Inventory;
import vn.edu.tdc.mymanager.R;

public class AdapterArea  extends RecyclerView.Adapter<AdapterArea.AreaViewHolder>{

    // Các thuộc tính
    private ArrayList<Inventory> listArea;
    private Context context;
    private LayoutInflater inflater;

    // Phương thức khởi tạo
    public AdapterArea(Context context, ArrayList<Inventory> list) {

        this.context = context;
        this.listArea = list;
        inflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public AreaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = inflater.inflate(R.layout.list_item_area, viewGroup, false);
        return new AreaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AreaViewHolder areaViewHolder, final int i) {

        // Lấy item hiện tại
        Inventory item = listArea.get(i);

        // Đưa giá trị lên hiển thị
        areaViewHolder.tvIdArea.setText("Mã: " + item.getIdArea() + "");
        areaViewHolder.tvNameArea.setText(item.getNameArea());

        // Bắt sự kiện nút arrow down
        areaViewHolder.btnShowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Đã click", Toast.LENGTH_SHORT).show();
            }
        });


        // Sự kiện khi click vào một item
        areaViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             onItemClickedListener.onItemClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listArea.size();
    }


    // Tạo view holder
    class AreaViewHolder extends RecyclerView.ViewHolder {

        private TextView tvIdArea;
        private  TextView tvNameArea;
        private ImageButton btnShowMore;

        public AreaViewHolder(@NonNull View itemView) {
            super(itemView);

            tvIdArea = (TextView) itemView.findViewById(R.id.tv_list_item_area_id);

            tvNameArea = (TextView) itemView.findViewById(R.id.tv_list_item_area_name);

            btnShowMore = (ImageButton) itemView.findViewById(R.id.btn_list_item_area_show_more);





        }
    }

    // Khai báo một phương thức  để ta có thể gọi nó bên ngoài để lấy được dữ liệu
    public interface OnItemClickedListener {
        void onItemClick(int position);

    }

    // Khai báo một biến interface
    private OnItemClickedListener onItemClickedListener;

    // Tạo setter cho biến interface ta vừa tạo
    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {

        this.onItemClickedListener = onItemClickedListener;
    }

}
