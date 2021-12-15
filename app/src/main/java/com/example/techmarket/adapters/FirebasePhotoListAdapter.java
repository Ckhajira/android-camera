package com.example.techmarket.adapters;

import android.content.Context;

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
}
