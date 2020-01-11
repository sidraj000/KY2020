package org.kashiyatra.ky20.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.kashiyatra.ky20.R;
import org.kashiyatra.ky20.UpdatesModel;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by HemanthSai on 07-Jan-18.
 */

public class UpdateAdapter extends RecyclerView.Adapter<UpdateAdapter.ViewHolder> {

   public ArrayList<UpdatesModel> updatesModel;
   public Context context;

    public UpdateAdapter(ArrayList<UpdatesModel> updatesModel,Context context) {
        this.updatesModel=updatesModel;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.update_view, parent, false);
        return new UpdateAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UpdateAdapter.ViewHolder holder, final int position) {
        holder.mBodyTextView.setText(updatesModel.get(position).update);
        holder.mTimeTextView.setText(updatesModel.get(position).url);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(updatesModel.get(position).url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return updatesModel.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mBodyTextView, mTimeTextView;
        public View mView;
        public ViewHolder(View v) {
            super(v);
            mView = v;
            mBodyTextView = v.findViewById(R.id.update_text);
            mTimeTextView = v.findViewById(R.id.update_url);
        }
    }
}
