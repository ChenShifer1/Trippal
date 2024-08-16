package com.access.trippal.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.access.trippal.R;


import com.access.trippal.objects.Place;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyPlacesAdapter extends RecyclerView.Adapter<MyPlacesAdapter.MyViewHolder> {

    private ArrayList<Place> places;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;



    public MyPlacesAdapter(Context context, ArrayList<Place> places) {
        if(context!=null) {
            this.mInflater = LayoutInflater.from(context);
            this.places = places;
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.trip_item_list, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Place place = places.get(position);
        holder.item_list_LBL_area.setText("area: "+place.getArea().toString());
        holder.item_list_LBL_name.setText("name: "+place.getName());
        holder.item_list_LBL_comments.setText("comments : "+place.getNumberOfComments());
        holder.item_list_RTB_rating.setRating(place.getStars());
        if(!place.getPictureUrl().isEmpty()){
            Picasso.get().load(place.getPictureUrl()).into(holder.item_list_IMG_image);
//            Glide.with(holder.context)
//                    .load(place.getPictureUrl())
//                    .into(holder.item_list_IMG_image);
        }
    }

    @Override
    public int getItemCount() {
        return places.size();
    }


    public Place getItem(int id) {
        return places.get(id);
    }


    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }


    public interface ItemClickListener {
        void onItemClick(View view, int position);
        void onItemDelete(View view, int position);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        Context context ;
        ImageView item_list_IMG_image;
        TextView item_list_LBL_area;
        TextView item_list_LBL_name;
        RatingBar item_list_RTB_rating ;
        TextView item_list_LBL_comments;
        ImageView trip_item_list_IMG_trash;

        MyViewHolder(View itemView) {
            super(itemView);
            item_list_LBL_area = itemView.findViewById(R.id.item_list_LBL_area);
            item_list_LBL_name = itemView.findViewById(R.id.item_list_LBL_name);
            item_list_RTB_rating = itemView.findViewById(R.id.item_list_RTB_rating);
            item_list_IMG_image=itemView.findViewById(R.id.item_list_IMG_image);
            item_list_LBL_comments=itemView.findViewById(R.id.item_list_LBL_comments);
            trip_item_list_IMG_trash=itemView.findViewById(R.id.trip_item_list_IMG_trash);
            context=itemView.getContext();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mClickListener != null) {
                        mClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });
            trip_item_list_IMG_trash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mClickListener != null) {
                        mClickListener.onItemDelete(v, getAdapterPosition());
                    }
                }
            });
        }
    }
}