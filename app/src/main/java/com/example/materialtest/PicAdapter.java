package com.example.materialtest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by xiecy on 2018/01/24.
 */

public class PicAdapter extends RecyclerView.Adapter<PicAdapter.ViewHolder> {

    private List<Pic> pics;

    private Context context;

    public PicAdapter(List<Pic> pics) {
        this.pics = pics;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context==null){
            context=parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        final ViewHolder holder=new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=holder.getAdapterPosition();//!!!!!!!!
                Pic pic=pics.get(position);
                Intent intent=new Intent(context,CarActivity.class);
                intent.putExtra("carName",pic.getName());
                intent.putExtra("carView",pic.getPicId());
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.picName.setText(pics.get(position).getName());
        Glide.with(context).load(pics.get(position).getPicId()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return pics.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView picName;
        ImageView image;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            picName = view.findViewById(R.id.picName);
            image = view.findViewById(R.id.pic);
        }
    }
}
