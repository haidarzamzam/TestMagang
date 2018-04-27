package com.example.haidar.testmagang;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Haidar on 27/04/2018.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.Holder> {
    List<CommentModel> commentClassList;

    public CommentAdapter(List<CommentModel> comment) {
        commentClassList = comment;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_comment_data, null);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        CommentModel h = commentClassList.get(position);
        holder.tvName.setText("Name : " + h.getName());
        holder.tvEmail.setText("Email : " + h.getEmail());
        holder.tvBody.setText("Body : " + h.getBody());
    }

    @Override
    public int getItemCount() {
        return commentClassList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvEmail;
        TextView tvBody;

        public Holder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.textViewName);
            tvEmail = itemView.findViewById(R.id.textViewEmail);
            tvBody = itemView.findViewById(R.id.textViewBody);
        }
    }
}