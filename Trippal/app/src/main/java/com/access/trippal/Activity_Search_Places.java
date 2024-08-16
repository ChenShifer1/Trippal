package com.access.trippal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.cardview.widget.CardView;

import com.access.trippal.enums.Area;
import com.access.trippal.enums.Type;
import com.access.trippal.utils.Constant;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class Activity_Search_Places extends Activity_Base {

     CardView search_places_BTN_search;
     ImageView search_places_IMG_park;
     ImageView search_places_IMG_trip;
     ImageView search_places_IMG_beaches;
    CardView logout;
    FirebaseAuth auth;
    private ImageView search_places_IMG_north;
    private ImageView search_places_IMG_sorth;
    private ImageView search_places_IMG_jerusalem;
    private ImageView search_places_IMG_center;
    private CardView search_places_BTN_addPlace;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_places);
        logout = findViewById(R.id.logout);
        search_places_BTN_search = findViewById(R.id.search_places_BTN_search);
        search_places_IMG_park = findViewById(R.id.search_places_IMG_park);
        search_places_IMG_trip = findViewById(R.id.search_places_IMG_trip);
        search_places_IMG_beaches = findViewById(R.id.search_places_IMG_beaches);
        search_places_IMG_north = findViewById(R.id.search_places_IMG_north);
        search_places_IMG_sorth = findViewById(R.id.search_places_IMG_sorth); // Corrected typo
        search_places_IMG_jerusalem = findViewById(R.id.search_places_IMG_jerusalem);
        search_places_IMG_center = findViewById(R.id.search_places_IMG_center);
        search_places_BTN_addPlace = findViewById(R.id.search_places_BTN_addPlace);
        auth =FirebaseAuth.getInstance();
        initViews();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(logout.getContext());
                builder.setTitle("Logout");
                builder.setMessage("Are you sure you want to Logout?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (auth.getCurrentUser()!=null){
                            auth.signOut();
                            startActivity(new Intent(Activity_Search_Places.this, Activity_Login.class));
                            Toast.makeText(Activity_Search_Places.this, "Logged Out", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();

            }
        });
    }


    private void initViews() {
        search_places_BTN_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(area!=null&&type!=null) {
                    searchPlace();
                }else{
                    Toast.makeText(Activity_Search_Places.this, "you need to choose area and type", Toast.LENGTH_SHORT).show();
                }

            }
        });
        search_places_IMG_park.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViewType(search_places_IMG_park);
                type=Type.park;

            }
        });
        search_places_IMG_trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViewType(search_places_IMG_trip);
                type=Type.trip;
            }
        });
        search_places_IMG_beaches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViewType(search_places_IMG_beaches);
                type= Type.beaches;
            }
        });
        search_places_IMG_north.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViewArea(search_places_IMG_north);
                area=Area.north;
            }
        });
        search_places_IMG_sorth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViewArea(search_places_IMG_sorth);
                area=Area.south;
            }
        });
        search_places_IMG_jerusalem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViewArea(search_places_IMG_jerusalem);
                area=Area.jerusalem;
            }
        });
        search_places_IMG_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViewArea(search_places_IMG_center);
                area= Area.center;
            }
        });
        search_places_BTN_addPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddPlace();
            }
        });
    }

    private void searchPlace() {
        Intent intent=new Intent(this,Activity_List_Places.class);
        if(area!=null&&type!=null) {
            intent.putExtra(Constant.EXTRA_AREA, area.toString());
            intent.putExtra(Constant.EXTRA_TYPE, type.toString());
        }
        startActivity(intent);
    }


    private void openAddPlace() {
        Intent intent=new Intent(this, Activity_Add_Place.class);
        startActivity(intent);
    }

}