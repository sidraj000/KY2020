package org.kashiyatra.ky20.adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import org.kashiyatra.ky20.R;
import org.kashiyatra.ky20.services.UploadImage;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private Context mContext;
    private List<UploadImage> mUploads;
    public onImageClicked onImageClicked;


    public ImageAdapter(Context context, List<UploadImage> uploads, onImageClicked onImageClicked) {
        this.onImageClicked =onImageClicked;
        mContext = context;
        mUploads = uploads;

    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.card, parent, false);
        return new ImageViewHolder(v,onImageClicked);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        UploadImage uploadCurrent = mUploads.get(position);

        holder.textViewName.setText(uploadCurrent.getName());

        holder.footer.setText(uploadCurrent.getFooter());
        holder.header.setText(uploadCurrent.getHeader());
        Picasso.get()
                .load(uploadCurrent.getImageUrl())
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public ImageView imageView;
        public TextView header;
        public TextView footer;
        public onImageClicked onImageClicked;

        public ImageViewHolder(View itemView, final onImageClicked onImageClicked) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.text_view_name);
            imageView = itemView.findViewById(R.id.image_view_upload);
            header=itemView.findViewById(R.id.header);
            footer=itemView.findViewById(R.id.footer);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onImageClicked.onImageClicked(getAdapterPosition());

                }
            });
        }
    }

    public interface onImageClicked{
        public void onImageClicked(int pos);

    }
}