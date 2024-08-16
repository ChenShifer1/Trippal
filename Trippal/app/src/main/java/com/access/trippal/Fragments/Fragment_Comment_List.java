package com.access.trippal.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import com.access.trippal.Adapters.MyCommentsAdapter;
import com.access.trippal.R;
import com.access.trippal.objects.Comment;
import com.access.trippal.utils.Constant;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;


public class Fragment_Comment_List extends Fragment {

    private RecyclerView comment_LST_comments;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_comment_list, container, false);
        findViews(view);
        readComments();
        return view;
    }

    private void readComments(){
        String pid=getArguments().getString(Constant.EXTRA_PLACE_KEY);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(Constant.COMMENTS_DB);
        myRef.child(pid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Comment> comments =new ArrayList<>();
                for (DataSnapshot commentSnapshot:dataSnapshot.getChildren()) {
                    Comment comment =commentSnapshot.getValue(Comment.class);
                    comments.add(comment);
                }
                displayComments(comments);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(getContext(),"error reading comments from data base",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayComments(ArrayList<Comment> comments){
        comment_LST_comments.setLayoutManager(new LinearLayoutManager(getContext()));
        MyCommentsAdapter adapter_comment = new MyCommentsAdapter( getContext(), comments);
        comment_LST_comments.setAdapter(adapter_comment);
    }


    private void findViews(View view){
        comment_LST_comments = view.findViewById(R.id.comment_LST_comments);
    }
}