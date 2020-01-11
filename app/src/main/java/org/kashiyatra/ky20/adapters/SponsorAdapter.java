package org.kashiyatra.ky20.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import org.kashiyatra.ky20.Objects.Sponsors;
import org.kashiyatra.ky20.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HemanthSai on 31-Dec-17.
 */

public class SponsorAdapter extends RecyclerView.Adapter<SponsorAdapter.ViewHolder> {
    private Context context;
    private List<Sponsors>mSponsors=new ArrayList();
    private List<String>mId=new ArrayList<>();
    public SponsorAdapter(Context context) {
        this.context = context;
        getData();
    }

    @Override
    public SponsorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sponsor_view, parent, false);
        return new SponsorAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SponsorAdapter.ViewHolder holder, final int i) {
        holder.mTypeTextView.setText(mSponsors.get(i).type);

        Glide.with(context)
                .load(mSponsors.get(i).url)
                .apply(new RequestOptions()
                        .fitCenter()
                        .dontAnimate()
                        .dontTransform())
                .into(holder.mLogoView);
    }

    @Override
    public int getItemCount() {

        return mSponsors.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public TextView mTypeTextView;
        public ImageView mLogoView;

        public ViewHolder(View v) {
            super(v);
            mView = v;
            mTypeTextView = v.findViewById(R.id.sponsor_type);
            mLogoView = v.findViewById(R.id.sponsor_logo);
        }
    }
    public void getData()
    {
        FirebaseDatabase.getInstance().getReference().child("sponsors").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Sponsors sponsors=dataSnapshot.getValue(Sponsors.class);
                String id=dataSnapshot.getKey();
                mSponsors.add(sponsors);
                mId.add(id);
                notifyItemInserted(mSponsors.size()-1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Sponsors sponsors=dataSnapshot.getValue(Sponsors.class);
                String id=dataSnapshot.getKey();
                int index=mId.indexOf(id);
                mSponsors.set(index,sponsors);
                notifyItemChanged(index);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Sponsors sponsors=dataSnapshot.getValue(Sponsors.class);
                String id=dataSnapshot.getKey();
                int index=mId.indexOf(id);
                mSponsors.remove(index);
                notifyItemRemoved(index);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}