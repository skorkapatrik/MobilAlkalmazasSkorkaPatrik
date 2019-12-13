package hu.patrik.mobilalkalmazas.webszolgaltatas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hu.patrik.mobilalkalmazas.R;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder>{

    private List<Post> postList;

    public static class PostViewHolder extends RecyclerView.ViewHolder {


        public TextView userId;
        public TextView id;
        public TextView title;


        public PostViewHolder(View itemView) {
            super(itemView);

            userId=itemView.findViewById(R.id.tvUserId);
            id=itemView.findViewById(R.id.tvId);
            title=itemView.findViewById(R.id.tvtitle);
        }
    }

    public PostAdapter(List<Post> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        PostViewHolder pvh = new PostViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post currentPost = postList.get(position);
        holder.userId.setText("UserID: "+String.valueOf(currentPost.getUserId()));
        holder.id.setText("ID: "+String.valueOf(currentPost.getId()));
        holder.title.setText("Title: "+currentPost.getTitle());
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }
}
