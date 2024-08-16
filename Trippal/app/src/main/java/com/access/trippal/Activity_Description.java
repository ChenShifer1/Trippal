package com.access.trippal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.access.trippal.objects.Place;
import com.access.trippal.utils.Constant;
import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class Activity_Description extends AppCompatActivity {

    private ImageView description_IMG_image;
    private RatingBar description_RTB_rating;
    private CardView description_BTN_toComment;
    private TextView description_LBL_name;
    private TextView description_LBL_area;
    private TextView description_LBL_type;
    TextView description_LBL_address;
    private TextView description_LBL_description;
    private TextView description_LBL_cost;
    private Place place;
    private Toolbar description_TLB_toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        findViews();
        initPlace();
        setData();
        initViews();
    }
    private void initPlace() {
        String placeString=getIntent().getStringExtra(Constant.EXTRA_PLACE_DATA);
        place=new Gson().fromJson(placeString, Place.class);
    }
    private void initViews() {

        description_BTN_toComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCommentsActivity();
            }
        });
    }

    private void openCommentsActivity() {
        Intent intent=new Intent(this,Activity_Comment.class);
        intent.putExtra(Constant.EXTRA_PLACE_KEY,place.getPid());
        startActivity(intent);
    }

    private void findViews() {
        description_IMG_image=findViewById(R.id.description_IMG_image);
        description_RTB_rating=findViewById(R.id.description_RTB_rating);
        description_BTN_toComment=findViewById(R.id.description_BTN_toComment);
        description_LBL_name=findViewById(R.id.description_LBL_name);
        description_LBL_area=findViewById(R.id.description_LBL_area);
        description_LBL_type=findViewById(R.id.description_LBL_type);
        description_LBL_address=findViewById(R.id.description_LBL_address);
        description_LBL_description=findViewById(R.id.description_LBL_description);
        description_LBL_cost=findViewById(R.id.description_LBL_cost);
        description_TLB_toolbar=findViewById(R.id.description_TLB_toolbar);
    }
    private void setData(){
        description_TLB_toolbar.setTitle(place.getName());
        description_RTB_rating.setRating(place.getStars());
        description_LBL_cost.setText("cost: "+place.getCost());
        description_LBL_area.setText("area: "+place.getArea());
        description_LBL_type.setText("type: "+place.getType());
        description_LBL_address.setText("address: "+place.getAddress());
        description_LBL_name.setText("name: "+place.getName());
        description_LBL_description.setText("description: "+place.getDescription());
        if(!place.getPictureUrl().isEmpty()) {
            Picasso.get()
                    .load(place.getPictureUrl())
                    .into(description_IMG_image);
        }
    }
}