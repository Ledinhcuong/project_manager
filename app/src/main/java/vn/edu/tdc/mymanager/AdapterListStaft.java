package vn.edu.tdc.mymanager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterListStaft  extends  RecyclerView.Adapter<AdapterListStaft.StaftViewHolder>{

    Context context;
    private LayoutInflater inflater;
    ArrayList<Staft> listStaft;

    public AdapterListStaft(Context context, ArrayList<Staft> stafts) {

        this.context = context;
        this.listStaft = stafts;
        inflater = inflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public StaftViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_list_staft, viewGroup, false);
        return new StaftViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StaftViewHolder staftViewHolder, int i) {

        // Đưa dữ liệu lên hiển thị
        staftViewHolder.tvStaftName.setText(listStaft.get(i).getStaftName() + "");
    }

    @Override
    public int getItemCount() {

        return listStaft.size();
    }

    class StaftViewHolder extends RecyclerView.ViewHolder {

        TextView tvStaftName;
        ImageButton btnRemove;

        public StaftViewHolder(@NonNull View itemView) {
            super(itemView);

            tvStaftName = (TextView) itemView.findViewById(R.id.tv_item_list_staft_name);
            btnRemove = (ImageButton) itemView.findViewById(R.id.btn_item_list_product_remove);
        }
    }

}
