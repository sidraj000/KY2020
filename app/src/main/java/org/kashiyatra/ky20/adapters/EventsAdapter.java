package org.kashiyatra.ky20.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.kashiyatra.ky20.R;


public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {
    private String[] mNames, mDescriptions;
    private TypedArray mIcons;
    private TypedArray mBackgrounds;
    private Context context;

    public EventsAdapter(Context context, String[] names, String[] descriptions, TypedArray backgrounds,TypedArray icons) {
        mNames = names;
        mDescriptions = descriptions;
        mBackgrounds = backgrounds;
        mIcons = icons;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return mNames.length;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.titleTextView.setText(mNames[position]);
//        holder.titleTextView.setTypeface(Typeface.createFromAsset(context.getAssets(), "HelveticaNeue-MediumCond.otf"));
        holder.descriptionTextView.setText(mDescriptions[position]);
        holder.iconImageView.setImageResource(mIcons.getResourceId(position, -1));
        holder.backgroundImageView.setImageResource(mBackgrounds.getResourceId(position, -1));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView titleTextView, descriptionTextView;
        ImageView backgroundImageView;
        ImageView iconImageView;

        public ViewHolder(View v) {
            super(v);
            titleTextView = v.findViewById(R.id.event_title);
            titleTextView.setVisibility(View.VISIBLE);
            descriptionTextView = v.findViewById(R.id.event_desc);
            descriptionTextView.setVisibility(View.VISIBLE);
            iconImageView = v.findViewById(R.id.event_icon);
            backgroundImageView = v.findViewById(R.id.event_background);
            mView = v;
        }
    }


}

