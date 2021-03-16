package com.example.mireaapp.Frgaments.Profile.teacher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mireaapp.R;

import java.util.ArrayList;

public class TeacherAdapter extends  RecyclerView.Adapter<TeacherAdapter.ViewHolder>{

    ArrayList<Teacher> models = new ArrayList<>();
    Context context;

    public TeacherAdapter(ArrayList<Teacher> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_schedule_teacher_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.itemName.setText(models.get(position).getNameItem());
        holder.teacherName.setText(models.get(position).getTeacherName());
        holder.teacherPost.setText(models.get(position).getTeacherPosition());
        holder.timeStart.setText(models.get(position).getTimeStart());
        holder.timeEnd.setText(models.get(position).getTimeEnd());
        holder.typeItem.setText(models.get(position).getTypeItem());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, models.get(position).getTeacherName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView teacherName, teacherPost, itemName, timeStart, timeEnd, typeItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            teacherName = itemView.findViewById(R.id.teacherName);
            teacherPost = itemView.findViewById(R.id.teacherPost);
            itemName = itemView.findViewById(R.id.nameItem);
            timeStart = itemView.findViewById(R.id.tv_teacher_item_time_start);
            timeEnd = itemView.findViewById(R.id.tv_teacher_item_time_end);
            typeItem = itemView.findViewById(R.id.typeItem);
        }
    }
}
