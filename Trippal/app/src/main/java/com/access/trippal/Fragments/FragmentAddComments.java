package com.access.trippal.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import com.access.trippal.Activity_Description;
import com.access.trippal.R;
import com.access.trippal.enums.Area;
import com.access.trippal.enums.Type;
import com.access.trippal.objects.Comment;
import com.access.trippal.objects.Place;
import com.access.trippal.utils.Constant;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


public class FragmentAddComments extends Fragment {

    private TextInputLayout add_comment_EDT_name;
    private TextInputLayout add_comment_EDT_comment;
    private CardView add_comment_BTN_addComment;
    private RatingBar add_comment_RTB_rating;
    private float numberOfStar=0f;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_comments, container, false);
        findViews(view);
        initViews();
        return view;
    }

    private void initViews() {
        add_comment_BTN_addComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addComment();
            }
        });

        add_comment_RTB_rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                numberOfStar=rating;
            }
        });
    }

    private void  addComment(){
        Comment comment=new Comment()
                .setCid(UUID.randomUUID().toString())
                .setUserName(add_comment_EDT_name.getEditText().getText().toString())
                .setComment(add_comment_EDT_comment.getEditText().getText().toString())
                .setDate(System.currentTimeMillis())
                .setStars(numberOfStar);
        saveCommentToDB(comment);
    }

    private void saveCommentToDB(Comment comment) {
        String pid=getArguments().getString(Constant.EXTRA_PLACE_KEY);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(Constant.COMMENTS_DB);
        myRef.child(pid).push().setValue(comment);
    }

    private void findViews(View view) {
        add_comment_EDT_name=view.findViewById(R.id.add_comment_EDT_name);
        add_comment_EDT_comment=view.findViewById(R.id.add_comment_EDT_comment);
        add_comment_BTN_addComment=view.findViewById(R.id.add_comment_BTN_addComment);
        add_comment_RTB_rating=view.findViewById(R.id.add_comment_RTB_rating);
    }
}