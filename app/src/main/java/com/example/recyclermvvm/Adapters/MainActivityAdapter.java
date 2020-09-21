package com.example.recyclermvvm.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.recyclermvvm.Model.NicePlaces;
import com.example.recyclermvvm.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.MyViewHolder> {
    private Context mContext;
    private List<NicePlaces> mNicePlaces;

    public MainActivityAdapter(Context mContext, List<NicePlaces> places) {
        this.mContext = mContext;
        this.mNicePlaces = places;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.recycler_design, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Set the name of the 'NicePlace'
        holder.mName.setText(mNicePlaces.get(position).getPlaceName());

        // Set the image
        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);
        Glide.with(mContext)
                .setDefaultRequestOptions(defaultOptions)
                .load(mNicePlaces.get(position).getPlaceImage())
                .into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        return mNicePlaces.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView mImage;
        private TextView mName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image);
            mName = itemView.findViewById(R.id.image_name);
        }
    }
}
