package vn.edu.tdc.mymanager.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.tdc.mymanager.R;
import vn.edu.tdc.mymanager.model.Task;

public class AdapterTask extends RecyclerView.Adapter<AdapterTask.TaskViewHolder> {

    // Các thuộc tính
    ArrayList<Task> listTasks;
    Context context;
    LayoutInflater inflater;

    public AdapterTask(Context context, ArrayList<Task> list) {

        this.context = context;
        this.listTasks = list;
        inflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = inflater.inflate(R.layout.item_list_task, viewGroup, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder taskViewHolder, int i) {

        // Lấy giá trị hiện tại
        Task task = listTasks.get(i);

        // Đưa nội dung vào hiển thị
        taskViewHolder.tvContentTask.setText(task.getContentTask());


    }

    @Override
    public int getItemCount() {
        return listTasks.size();
    }

    // View holder
    class TaskViewHolder extends RecyclerView.ViewHolder{

        TextView tvContentTask;
        CheckBox chkCheck;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContentTask = (TextView) itemView.findViewById(R.id.tv_item_list_task_content_task);
            chkCheck = (CheckBox) itemView.findViewById(R.id.chk_item_list_task_check_task);

        }
    }

}
