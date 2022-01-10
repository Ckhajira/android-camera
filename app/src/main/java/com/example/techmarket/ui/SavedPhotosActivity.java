package com.example.techmarket.ui;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.techmarket.Constants;
import com.example.techmarket.R;
import com.example.techmarket.adapters.FirebasePhotoViewHolder;
import com.example.techmarket.models.InterestingPhoto;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedPhotosActivity extends AppCompatActivity {
    private DatabaseReference mPhotoReference;
    private FirebaseRecyclerAdapter<InterestingPhoto, FirebasePhotoViewHolder> mFirebaseAdapter;
    //    @BindView(R.id.firebaseProgressBar) ProgressBar interestingPhotoProgressBar;
    @BindView(R.id.recycler2)
    RecyclerView mRecyclerView;
    //    @BindView(R.id.errorTextView)
//    TextView mErrorTextView;
//    @BindView(R.id.progressBar)
//    ProgressBar mProgressBar;
    @BindView(R.id.savedPhotosProgressBar) ProgressBar savedPhotosProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler2);
        ButterKnife.bind(this);

        showProgressBar();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mPhotoReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PHOTOS).child(uid);
        setUpFirebaseAdapter();

        showRestaurants();
    }

    private void setUpFirebaseAdapter(){

        FirebaseRecyclerOptions<InterestingPhoto> options =
                new FirebaseRecyclerOptions.Builder<InterestingPhoto>()
                        .setQuery(mPhotoReference, InterestingPhoto.class)
                        .build();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<InterestingPhoto, FirebasePhotoViewHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull FirebasePhotoViewHolder firebaseRestaurantViewHolder, int position, @NonNull InterestingPhoto photo) {
                firebaseRestaurantViewHolder.bindPhoto(photo);
                hideProgressBar();
            }

            @NonNull
            @Override
            public FirebasePhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_saved_photos, parent, false);
                return new FirebasePhotoViewHolder(view);
            }

        };

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mFirebaseAdapter!= null) {
            mFirebaseAdapter.stopListening();
        }
    }


    private void showRestaurants() {

        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        savedPhotosProgressBar.setVisibility(View.GONE);
    }
    private void showProgressBar() {
        savedPhotosProgressBar.setVisibility(View.VISIBLE);
    }
}