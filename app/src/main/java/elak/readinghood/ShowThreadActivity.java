package elak.readinghood;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.io.IOException;

import elak.readinghood.backend.api.AppManager;
import elak.readinghood.backend.posts.Posts;
import elak.readinghood.backend.threads.Thread;
import elak.readinghood.backend.threads.Threads;

public class ShowThreadActivity extends AppCompatActivity {

    ListView listView;
    PostsAdapter adapter;
    Thread currentThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.expanded_list);

        Bundle b = getIntent().getExtras();
        int thread_id = -1; // or other values
        if (b != null)
            thread_id = b.getInt("id");
        else {
            return;
        }


        try {
            currentThread = AppManager.getThreadById(thread_id);
            //String title = currentThread.getTitle();
            //Tags tags = currentThread.getTags();

        } catch (IOException e) {
            Toast toast = Toast.makeText(this, "Problem with server", Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        listView = (ListView) findViewById(R.id.posts_list);
        adapter = new PostsAdapter(this, currentThread.getAllPosts(), currentThread);
        listView.setAdapter(adapter);

    }

    public void addAnswer(View view) {
        if (currentThread.canYouAnswerThisThread()){
            Intent intent = new Intent(this, NewAnswerActivity.class);
            intent.putExtra("thread", currentThread);
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(this, "This thread has many downvotes, so you can't answer it", Toast.LENGTH_LONG);
            toast.show();
        }
    }


}
