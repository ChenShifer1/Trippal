package com.access.trippal;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.access.trippal.enums.Area;
import com.access.trippal.enums.Type;
import com.access.trippal.utils.Constant;

public class Activity_Base extends AppCompatActivity {
    protected ImageView clickImageViewArea;
    protected ImageView clickImageViewType;
    protected Area area;
    protected Type type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    protected  void updateViewArea(ImageView imageView){
        if(clickImageViewArea!=null) {
            clickImageViewArea.setBackgroundColor(Color.WHITE);
        }
        imageView.setBackgroundColor(Color.rgb(Constant.COLOR_NUMBER,Constant.COLOR_NUMBER,Constant.COLOR_NUMBER));
        clickImageViewArea=imageView;
    }
    protected void updateViewType(ImageView imageView){
        if(clickImageViewType!=null) {
            clickImageViewType.setBackgroundColor(Color.WHITE);
        }
        imageView.setBackgroundColor(Color.rgb(Constant.COLOR_NUMBER,Constant.COLOR_NUMBER,Constant.COLOR_NUMBER));
        clickImageViewType=imageView;
    }
    protected void makeToast(String msg) {
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
    }


}