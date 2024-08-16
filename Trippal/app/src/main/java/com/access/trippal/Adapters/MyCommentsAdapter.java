package com.access.trippal.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.access.trippal.R;
import com.access.trippal.objects.Comment;


import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MyCommentsAdapter extends RecyclerView.Adapter<MyCommentsAdapter.MyViewHolder> {

    private ArrayList<Comment> comments;
    private LayoutInflater mInflater;

    public MyCommentsAdapter(Context context, ArrayList<Comment> comments) {
        if(context!=null) {
            this.mInflater = LayoutInflater.from(context);
            this.comments = comments;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.comment_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        com.access.trippal.objects.Comment comment = (com.access.trippal.objects.Comment) comments.get(position);
        holder.comment_list_LBL_name.setText("name: "+comment.getUserName());
        holder.comment_list_LBL_date.setText("date: "+new SimpleDateFormat("dd-MM-yyyy    HH:mm:ss").format(comment.getDate()));
        holder.comment_list_RTB_rating.setRating(comment.getStars());
        holder.comment_list_LBL_comment.setText("comment: "+comment.getComment());

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }


    public  Comment getItem(int id) {
        return comments.get(id);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView comment_list_LBL_name ;
        TextView comment_list_LBL_date ;
        RatingBar comment_list_RTB_rating ;
        TextView  comment_list_LBL_comment;


        MyViewHolder(View itemView) {
            super(itemView);
            comment_list_LBL_name = itemView.findViewById(R.id.comment_list_LBL_name);
            comment_list_LBL_date = itemView.findViewById(R.id.comment_list_LBL_date);
            comment_list_RTB_rating = itemView.findViewById(R.id.comment_list_RTB_rating);
            comment_list_LBL_comment = itemView.findViewById(R.id.comment_list_LBL_comment);

        }
    }
}