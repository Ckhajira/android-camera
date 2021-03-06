package com.example.techmarket.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.techmarket.R;
import com.example.techmarket.models.InterestingPhoto;
import com.example.techmarket.utils.ItemTouchHelperAdapter;
import com.example.techmarket.utils.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;

public class FirebasePhotoListAdapter extends FirebaseRecyclerAdapter<InterestingPhoto, FirebasePhotoViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    public FirebasePhotoListAdapter(FirebaseRecyclerOptions<InterestingPhoto> options,
                                    DatabaseReference ref,
                                    OnStartDragListener onStartDragListener,
                                    Context context){
        super(options);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
    }
    @Override
    protected void onBindViewHolder(@NonNull FirebasePhotoViewHolder firebasePhotoViewHolder, int position, @NonNull InterestingPhoto photo) {
        firebasePhotoViewHolder.bindPhoto(photo);
        firebasePhotoViewHolder.photoImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(firebasePhotoViewHolder);
                }
                return false;
            }
        });
    }

    @NonNull
    @Override
    public FirebasePhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_saved_photos,parent,false);
        return new FirebasePhotoViewHolder(view);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition){
        return false;
    }

    @Override
    public void onItemDismiss(int position){

    }




}
