package org.kashiyatra.ky20.TeamGroupsAdapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.kashiyatra.ky20.R;

public class OpnLogTeamAdapter extends RecyclerView.Adapter<OpnLogTeamAdapter.ViewHolder> {
    private String[] mNames, mRoles, mEmails, mFbLinks, mLinkedInLinks, mPhotoUrls;
    private Context context;

    public OpnLogTeamAdapter(Context context, String[] names, String[] roles, String[] photoUrls, String[] emails, String[] fbLinks, String[] linkedInLinks) {
        mNames = names;
        mRoles = roles;
        mPhotoUrls = photoUrls;
        mLinkedInLinks = linkedInLinks;
        mEmails = emails;
        mFbLinks = fbLinks;
        this.context = context;
    }

    @Override
    public OpnLogTeamAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_view, parent, false);
        return new OpnLogTeamAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(OpnLogTeamAdapter.ViewHolder holder, final int position) {
        holder.mNameTextView.setText(mNames[position]);
        holder.mRoleTextView.setText(mRoles[position]);

        Glide.with(context)
                .load(mPhotoUrls[position])
                .apply(new RequestOptions()
                        .placeholder(R.drawable.person)
                        .error(R.drawable.person)
                        .centerCrop()
                        .dontAnimate()
                        .dontTransform())
                .into(holder.mPhotoView);
        holder.mEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] email = new String[]{mEmails[position]};
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, email);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Query regarding Kashiyatra");
                context.startActivity(intent);
            }
        });
        holder.mFbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mFbLinks[position]));
                context.startActivity(intent);
            }
        });
        holder.mLinkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mLinkedInLinks[position]));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNames.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public TextView mNameTextView, mRoleTextView;
        public de.hdodenhof.circleimageview.CircleImageView mPhotoView;
        public ImageButton mEmailButton, mFbButton, mLinkedIn;

        public ViewHolder(View v) {
            super(v);
            mView = v;
            mNameTextView = v.findViewById(R.id.name);
            mRoleTextView = v.findViewById(R.id.role);
            mPhotoView = v.findViewById(R.id.person_photo);
            mEmailButton = v.findViewById(R.id.send_email);
            mFbButton = v.findViewById(R.id.visit_fb);
            mLinkedIn = v.findViewById(R.id.visit_linkedin);
        }
    }
}
