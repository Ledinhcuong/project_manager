package vn.edu.tdc.mymanager.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.tdc.mymanager.R;
import vn.edu.tdc.mymanager.model.Function;

public class AdapterFuncion extends RecyclerView.Adapter<AdapterFuncion.FunctionViewHolder> {


    // Các thuộc tính
    private ArrayList<Function> listFunction;  // Danh sách
    private Context context;   // Màn hình hiện tại
    private LayoutInflater inflater;


    // Phương thức khởi tạo
    public AdapterFuncion(Context context, ArrayList<Function> list) {
        this.context = context;
        this.listFunction = list;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public FunctionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        // Liên kết với giao diện item
        View itemView = inflater.inflate(R.layout.item_list_function, viewGroup, false);
        return new FunctionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FunctionViewHolder functionViewHolder, final int i) {

        Function item = listFunction.get(i);

        functionViewHolder.tvNameFunction.setText(item.getNameFuction());  // Tiêu đề
        functionViewHolder.tvExplaintion.setText(item.getExplainFunction());  // Giải thích cho chức năng
        functionViewHolder.imgIcon.setImageResource(item.getIcon());

        // Bắt sự kiện khi click vào một item
        functionViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onItemClickedListener.onItemClick(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listFunction.size();
    }


    // Tạo ra một view holder
    class FunctionViewHolder  extends RecyclerView.ViewHolder {


        private ImageView imgIcon;
        private TextView tvNameFunction;
        private  TextView tvExplaintion;


        public FunctionViewHolder(@NonNull View itemView) {
            super(itemView);

            imgIcon = (ImageView) itemView.findViewById(R.id.imgFunction);
            tvNameFunction = (TextView) itemView.findViewById(R.id.tvNameFunction);
            tvExplaintion = (TextView) itemView.findViewById(R.id.tvFunctionExplation);


        }
    }

    // Xử lý interface để xử lý các sự kiện

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
