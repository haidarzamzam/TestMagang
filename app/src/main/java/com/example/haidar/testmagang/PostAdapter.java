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

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.Holder> {
    List<PostModel> postClassList;
    IPostAdapter mIPostAdapter;

    public PostAdapter(List<PostModel> post, IPostAdapter listener) {
        postClassList = post;
        this.mIPostAdapter = listener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_post_data, null);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        PostModel h = postClassList.get(position);
        holder.tvTitle.setText("Title : " + h.getTitle());
        holder.tvBody.setText("Body : " + h.getBody());
    }

    @Override
    public int getItemCount() {
        return postClassList.size();
    }

    public interface IPostAdapter {
        void doClick(int pos);
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvBody;

        public Holder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.textViewTitle);
            tvBody = itemView.findViewById(R.id.textViewBody);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mIPostAdapter.doClick(getAdapterPosition());
                }
            });
        }
    }
}