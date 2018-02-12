package elak.readinghood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.io.IOException;

import elak.readinghood.backend.api.AppManager;
import elak.readinghood.backend.threads.Thread;
import elak.readinghood.backend.threads.Threads;

public class NewsFeedActivity extends AppCompatActivity {
    Threads threads;
    ListView listView;
    ThreadsAdapter adapter;
    SearchView editsearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);


        try {
            threads = AppManager.getRecentThreadsOfNewsFeed();
        } catch (IOException e) {
            Toast toast = Toast.makeText(this, "Problem with server", Toast.LENGTH_LONG);
            toast.show();
            return;
        }


        listView = (ListView) findViewById(R.id.MyListView);
        adapter = new ThreadsAdapter(this, threads.getListOfThreads());
        listView.setAdapter(adapter);
        final NewsFeedActivity a = this;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                try {
                    Thread chosenThread = threads.seeThread(position);


                    Intent intent = new Intent(a, ShowThreadActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("id", chosenThread.getId()); //Your id
                    intent.putExtras(b);
                    startActivity(intent);



                } catch (IOException e) {
                    // toast
                }

                //Thread thread=adapter.getItem(position);

            }

        });
        editsearch = (SearchView) findViewById(R.id.Search);


        editsearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
// do something on text submit
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

    }

    public void fabListenner(View view) {
        Intent intent = new Intent(this, NewThreadActivity.class);
        startActivity(intent);
    }


}
