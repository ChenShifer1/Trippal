package com.access.trippal;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.access.trippal.Fragments.FragmentAddComments;
import com.access.trippal.Fragments.Fragment_Comment_List;
import com.access.trippal.utils.Constant;

public class Activity_Comment extends AppCompatActivity {
    private FragmentAddComments fragment_add_comment ;
    private Fragment_Comment_List fragment_comments_list;
    private String pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acitivity_comment);
        initView();
    }

    private void initView(){
        pid=getIntent().getStringExtra(Constant.EXTRA_PLACE_KEY);
        Bundle bundle=new Bundle();
        bundle.putString(Constant.EXTRA_PLACE_KEY,pid);
        fragment_add_comment = new FragmentAddComments();
        fragment_add_comment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.comment_LAY_addComment, fragment_add_comment).commit();
        fragment_comments_list = new Fragment_Comment_List();
        fragment_comments_list.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.comment_LAY_list, fragment_comments_list).commit();
    }
}