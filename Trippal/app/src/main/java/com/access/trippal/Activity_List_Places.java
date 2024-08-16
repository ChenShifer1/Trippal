package com.access.trippal;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.access.trippal.Adapters.SlidePagerAdapter;
import com.access.trippal.Fragments.Fragment_Map;
import com.access.trippal.Fragments.List_Fragment;

import java.util.ArrayList;
import java.util.List;

public class Activity_List_Places extends AppCompatActivity {

    ViewPager list_places_LAY_viewPager;
    PagerAdapter pagerAdapter;
    List_Fragment fragment_list;
    Fragment_Map fragment_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_places);
        initView();
    }

    private void initView() {
        List<Fragment> list = new ArrayList<>();
        fragment_list = new List_Fragment();
        fragment_map = new Fragment_Map();
        fragment_list.setArguments(getIntent().getExtras());
        fragment_map.setArguments(getIntent().getExtras());
        list.add(fragment_list);
        list.add(fragment_map);
        list_places_LAY_viewPager = findViewById(R.id.list_places_LAY_viewPager);
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(), list);
        list_places_LAY_viewPager.setAdapter(pagerAdapter);
    }
}