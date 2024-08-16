package com.access.trippal;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.Manifest;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.access.trippal.enums.Area;
import com.access.trippal.enums.Type;
import com.access.trippal.objects.Place;
import com.access.trippal.utils.Constant;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class Activity_Add_Place extends Activity_Base {

    private TextInputLayout add_place_EDT_description;
    private TextInputLayout add_place_EDT_cost;
    private TextInputLayout add_place_EDT_address;
    private CardView add_place_BTN_addPlace;
    private ImageView add_place_IMG_park;
    private ImageView add_place_IMG_beaches;
    private ImageView add_place_IMG_trip;
    private ImageView add_place_IMG_sorth;
    private ImageView add_place_IMG_north;
    private ImageView add_place_IMG_center;
    private ImageView add_place_IMG_jerusalem;
    private TextInputLayout add_place_EDT_name;
    private Area area;
    ProgressDialog dialog;
    public static final int PICK_IMAGE = 1000;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private Type type;
    private Uri pictureUrl;
    private ImageView add_place_IMG_picture;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);
        findViews();
        dialog= new ProgressDialog(this);
        add_place_BTN_addPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(area!=null&&type!=null) {
                    setPlace();
                }else{
                    makeToast("you need to choose area and type");
                }
            }
        });
        add_place_IMG_park.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViewType(add_place_IMG_park);
                type=Type.park;
            }
        });
        add_place_IMG_beaches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViewType(add_place_IMG_beaches);
                type=Type.beaches;
            }
        });
        add_place_IMG_trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViewType(add_place_IMG_trip);
                type=Type.trip;
            }
        });
        add_place_IMG_sorth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViewArea(add_place_IMG_sorth);
                area=Area.south;
            }
        });
        add_place_IMG_north.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViewArea(add_place_IMG_north);
                area=Area.north;
            }
        });
        add_place_IMG_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViewArea(add_place_IMG_center);
                area=Area.center;
            }
        });
        add_place_IMG_jerusalem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViewArea(add_place_IMG_jerusalem);
                area=Area.jerusalem;
            }
        });
        add_place_IMG_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Activity_Add_Place", "Picture ImageView clicked");
                checkPermissions();
            }
        });
    }

    private void findViews() {
        add_place_EDT_description=findViewById(R.id.add_place_EDT_description);
        add_place_EDT_cost=findViewById(R.id.add_place_EDT_cost);
        add_place_EDT_address=findViewById(R.id.add_place_EDT_address);
        add_place_BTN_addPlace=findViewById(R.id.add_place_BTN_addPlace);
        add_place_IMG_park=findViewById(R.id.add_place_IMG_park);
        add_place_IMG_beaches=findViewById(R.id.add_place_IMG_beaches);
        add_place_IMG_trip=findViewById(R.id.add_place_IMG_trip);
        add_place_IMG_sorth=findViewById(R.id.add_place_IMG_sorth);
        add_place_IMG_north=findViewById(R.id.add_place_IMG_north);
        add_place_IMG_center=findViewById(R.id.add_place_IMG_center);
        add_place_IMG_jerusalem=findViewById(R.id.add_place_IMG_jerusalem);
        add_place_EDT_name=findViewById(R.id.add_place_EDT_name);
        add_place_IMG_picture=findViewById(R.id.add_place_IMG_picture);
    }


    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // For Android 13 and above
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_MEDIA_IMAGES},
                        PERMISSION_REQUEST_CODE);
            } else {
                openGallery();
            }
        } else {
            // For Android 12 and below
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PERMISSION_REQUEST_CODE);
            } else {
                openGallery();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
                // Permission denied
                Toast.makeText(this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            pictureUrl = data.getData();
            Glide.with(this)
                    .load(pictureUrl)
                    .into(add_place_IMG_picture);
        }
    }



    private void setPlace() {
        double cost=0.0;
        try {
            cost = Double.parseDouble(add_place_EDT_cost.getEditText().getText().toString());
        }catch (Exception e){
        }
        Place place = new Place()
                .setPid(UUID.randomUUID().toString())
                .setArea(area)
                .setType(type)
                .setName(add_place_EDT_name.getEditText().getText().toString())
                .setDescription(add_place_EDT_description.getEditText().getText().toString())
                .setCost(cost)
                .setAddress(add_place_EDT_address.getEditText().getText().toString());
        setAddress(place);
        saveImageToDB(place);
    }

    private void openSearchPlaces() {
        Intent intent=new Intent(this, Activity_Search_Places.class);
        startActivity(intent);
        finish();
    }
    private void setAddress(Place place) {
        Geocoder coder = new Geocoder(this);
        List<Address> address;
        try {
            address = coder.getFromLocationName(place.getAddress(), 5);
            if (address != null && !address.isEmpty()) {
                Address location = address.get(0);
                place.setLatitude(location.getLatitude());
                place.setLongitude(location.getLongitude());
            } else {
                Log.e("Activity_Add_Place", "No address found for the given location name.");
                makeToast("No address found for the given location name.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Activity_Add_Place", "Geocoder failed", e);
            makeToast("Geocoder service is not available.");
        }
    }


    private void openGallery() {
        Log.d("Activity_Add_Place", "Opening gallery");
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }
    private void saveImageToDB(Place place){
        if(pictureUrl==null) {
            savePlaceToDB(place);
            return;
        }
        dialog.setMessage("Please wait...");
        dialog.setTitle("Processing");
        dialog.setCancelable(false);
        dialog.show();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference();
        StorageReference ref = storageReference.child("IMAGES/" + place.getPid());

        ref.putFile(pictureUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                readPictureUrl(place);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                makeToast("failed to save");
                openSearchPlaces();
            }
        });
    }
    private void readPictureUrl(Place place){
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference();
        StorageReference ref = storageReference.child("IMAGES/" + place.getPid());
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                place.setPictureUrl(uri.toString());
                savePlaceToDB(place);
            }
        });
    }
    private void savePlaceToDB(Place place){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(Constant.PLACES_DB);
        myRef.child(area.toString()).child(type.toString()).child(place.getPid()).setValue(place).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                openSearchPlaces();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Activity_Add_Place.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}