package elak.readinghood;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import elak.readinghood.backend.api.AppManager;
import elak.readinghood.backend.posts.Post;
import elak.readinghood.backend.threads.Thread;


/**
 * Created by user on 26/10/2017.
 */

public class PostsAdapter extends ArrayAdapter<Post> {

    Thread currentThread;

    public PostsAdapter(Activity context, ArrayList<Post> posts, Thread currentThread) {
        super(context, R.layout.expanded_list, posts);
        this.currentThread = currentThread;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Post post = getItem(position);

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.expanded_item, parent, false);
        }

        if (position == 0) { // This is a question

            TextView t1 = (TextView) listItemView.findViewById(R.id.title1);
            t1.setText(currentThread.getTitle());
            t1.setVisibility(View.VISIBLE);

            TextView t4 = (TextView) listItemView.findViewById(R.id.tags1);
            t4.setText(currentThread.getHashTags().toString());
            t4.setVisibility(View.VISIBLE);

        }

        TextView t2 = (TextView) listItemView.findViewById(R.id.username1);
        t2.setText(post.getAuthor().getUsername());

        TextView t3 = (TextView) listItemView.findViewById(R.id.query1);
        t3.setText(post.getText());

        final TextView t5 = (TextView) listItemView.findViewById(R.id.votes1);
        t5.setText(String.valueOf(post.getNumberOfVotes()));


        final Context context = listItemView.getContext();

        final ImageView upvoteView = (ImageView) listItemView.findViewById(R.id.upvote);

        upvoteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (post.canYouUpVote()){
                        post.upVoteThisPost();
                        t5.setText(String.valueOf(Integer.parseInt(t5.getText().toString()) + 1));
                    } else {
                        Toast toast = Toast.makeText(context, "You can't vote for this post", Toast.LENGTH_LONG);
                        toast.show();
                    }
                    //upvoteView.setEnabled(false);
                } catch (IOException e) {
                    //TOST
                }
            }
        });

        final ImageView downvoteView = (ImageView) listItemView.findViewById(R.id.downvote);
        if (!post.canYouDownVote()){
            //downvoteView.setEnabled(false);
        }
        downvoteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (post.canYouDownVote()) {
                        post.downVoteThisPost();
                        t5.setText(String.valueOf(Integer.parseInt(t5.getText().toString()) - 1));
                    } else {
                        Toast toast = Toast.makeText(context, "You can't vote for this post", Toast.LENGTH_LONG);
                        toast.show();
                    }
                    //downvoteView.setEnabled(false);
                } catch (IOException e) {
                    //TOST
                }
            }
        });
        return listItemView;


    }
}
