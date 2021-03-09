package com.example.mireaapp.Frgaments.Explorer.stories;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mireaapp.R;

import java.util.ArrayList;
import java.util.List;

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.ViewHolder>{

    private Context mContext;
    private List<Story> mStory;

    public StoriesAdapter(Context mContext, ArrayList<Story> mStory) {
        this.mContext = mContext;
        this.mStory = mStory;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.story_item, parent, false);
        return new StoriesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.story_photo.setImageDrawable(mContext.getDrawable(R.drawable.logo));
        holder.story_username.setText(mStory.get(position).getUsername());

    }

    @Override
    public int getItemCount() {
        return mStory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView story_photo, story_photo_seen;
        public TextView story_username;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            story_photo = itemView.findViewById(R.id.story_photo);
            story_photo_seen = itemView.findViewById(R.id.story_photo_seen);
            story_username = itemView.findViewById(R.id.username_story);
        }
    }

    private void storyWatch() {

    }

    private void storySeen() {

    }

}
