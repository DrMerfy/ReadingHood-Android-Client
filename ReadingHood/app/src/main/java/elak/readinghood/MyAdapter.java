package elak.readinghood;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import elak.readinghood.backend.threads.Thread;


/**
 * Created by user on 26/10/2017.
 */

public class MyAdapter extends ArrayAdapter<Thread> {

    public MyAdapter(Activity context, ArrayList<Thread> threads) {
        super(context, R.layout.list_item, threads);


    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Thread thread = getItem(position);

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }



        TextView t1 = (TextView) listItemView.findViewById(R.id.votes);
        t1.setText(thread.getQuestionPost().getNumberOfVotes());

        TextView t2 = (TextView) listItemView.findViewById(R.id.query);
        t2.setText(thread.getQuestionPost().getText());

        TextView t3 = (TextView) listItemView.findViewById(R.id.username);
        t3.setText(thread.getQuestionPost().getAuthor().getUsername());

        TextView t4 = (TextView) listItemView.findViewById(R.id.tags);
        t4.setText(thread.getTags().toString());

        return listItemView;


    }
}
