package com.example.microsoft.service.UI.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.example.microsoft.service.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Microsoft on 18.10.2016.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ImageViewHolder> {

    Context mContext;
    ArrayList mImageData;

    public MyRecyclerAdapter(ArrayList imageData, Context context) {
        mImageData = imageData;
        mContext = context;
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageItem;

        public ImageViewHolder(View itemView) {
            super(itemView);
            this.imageItem = (ImageView) itemView.findViewById(R.id.img_recycler);
        }
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        ImageViewHolder holder = new ImageViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        int image = (int) mImageData.get(position);
        Picasso.with(mContext).load(image).into(holder.imageItem);
    }

    @Override
    public int getItemCount() {
        return mImageData.size();
    }
}
