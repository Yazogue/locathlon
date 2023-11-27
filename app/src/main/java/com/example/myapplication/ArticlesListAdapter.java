package com.example.myapplication;

import static android.webkit.URLUtil.isValidUrl;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;

import java.io.File;
import java.util.ArrayList;

public class ArticlesListAdapter extends RecyclerView.Adapter<ArticlesListAdapter.ViewHolder> {
    ArrayList<ArticlesDomain> items;
    Context context;

    public ArticlesListAdapter(ArrayList<ArticlesDomain> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ArticlesListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_art_list,parent,false);
        context=parent.getContext();
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesListAdapter.ViewHolder holder, int position) {
    holder.titleTxt.setText(items.get(position).getTitle());
    holder.feeTxt.setText(items.get(position).getPrice()+"€");
    holder.scoreTxt.setText(""+items.get(position).getScore());

    int drawableResourceId=holder.itemView.getResources().getIdentifier(items.get(position).getPicUrl(),
            "drawable", holder.itemView.getContext().getPackageName());

        String imageUrl = items.get(position).getPicUrl();
        if (imageUrl != null && isValidUrl(imageUrl)) {
            // Load the image using Glide if the URL is valid
            Glide.with(holder.itemView.getContext())
                    .load(imageUrl)
                    .transform(new GranularRoundedCorners(30, 30, 0, 0))
                    .into(holder.pic);
        } else {
            // Use a default image from your drawable resources
            holder.pic.setImageResource(R.drawable.emptyimage);
        }
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(),DetailActivity.class);
            intent.putExtra("object",items.get(position));
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt,feeTxt,scoreTxt;
        ImageView pic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTxt=itemView.findViewById(R.id.titleTxt);
            feeTxt=itemView.findViewById(R.id.feeTxt);
            scoreTxt=itemView.findViewById(R.id.scoreTxt);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}
