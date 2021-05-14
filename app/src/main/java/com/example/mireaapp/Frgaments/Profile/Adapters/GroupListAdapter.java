package com.example.mireaapp.Frgaments.Profile.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mireaapp.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class GroupListAdapter extends RecyclerView.Adapter<GroupListAdapter.MyViewClass> {

    ArrayList<String> groups;
    Context context;

    public GroupListAdapter(ArrayList<String> groups, Context context) {
        this.groups = groups;
        this.context = context;
    }

    @NotNull
    @Override
    public GroupListAdapter.MyViewClass onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group, parent, false);
        MyViewClass myViewClass = new MyViewClass(view);
        return myViewClass;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull GroupListAdapter.MyViewClass holder, int position) {

        holder.group.setText(groups.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Item Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    public class MyViewClass extends RecyclerView.ViewHolder {

        TextView group;

        public MyViewClass(@NonNull View itemView) {
            super(itemView);
            group = itemView.findViewById(R.id.nameGroup);
        }
    }
}
