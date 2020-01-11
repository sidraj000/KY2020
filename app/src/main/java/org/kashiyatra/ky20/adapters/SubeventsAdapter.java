package org.kashiyatra.ky20.adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.kashiyatra.ky20.R;

/**
 * Created by HemanthSai on 06-Dec-17.
 */

public class SubeventsAdapter extends RecyclerView.Adapter<SubeventsAdapter.ViewHolder> {
    private String[] mNames, mDescs;
    private Context mContext;

    public SubeventsAdapter(Context context, String[] names, String[] descs) {
        mNames = names;
        mDescs = descs;
        mContext = context;
    }

    @Override
    public SubeventsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.subevent_view, parent, false);
        return new SubeventsAdapter.ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return mNames.length;
    }

    @Override
    public void onBindViewHolder(SubeventsAdapter.ViewHolder holder, int position) {

        holder.titleTextView.setText(mNames[position]);
//        holder.titleTextView.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "OpenSans-Semibold.ttf"));
        holder.descTextView.setText(mDescs[position]);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descTextView;
        View mView;

        private ViewHolder(View v) {
            super(v);
            titleTextView = v.findViewById(R.id.subevent_title);
            descTextView = v.findViewById(R.id.subevent_desc);
            mView = v;
        }
    }
}
