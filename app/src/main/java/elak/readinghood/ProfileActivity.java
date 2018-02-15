package elak.readinghood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import elak.readinghood.backend.api.AppManager;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        try {
            String nameSurnameString = AppManager.getUserProfile().getName() + " " +
                    AppManager.getUserProfile().getSurname();
            TextView nameSurname = (TextView) findViewById(R.id.nameSurnameTextView);
            nameSurname.setText(nameSurnameString);

            String usernameString = AppManager.getUserProfile().getUsername();
            TextView username = (TextView) findViewById(R.id.usernameTextView);
            username.setText(usernameString);

            String studiesString = AppManager.getUserProfile().getDepartment();
            TextView studies = (TextView) findViewById(R.id.studiesTextView);
            studies.setText(studiesString);

            int respectString = AppManager.getUserProfile().getRespect();
            TextView repsect = (TextView) findViewById(R.id.respectTextView);
            repsect.setText(String.valueOf(respectString));


            AppManager.getUserProfile().setActivity();

            String latestCreatedThread = AppManager.getUserProfile().getActivity().getLatestCreatedThreadTitle();
            TextView created = (TextView) findViewById(R.id.createdPostTextView);
            created.setText(latestCreatedThread);

            String latestUpVotedPost = AppManager.getUserProfile().getActivity().getLatestUpVotedPostText();
            TextView votedUp = (TextView) findViewById(R.id.votedUpTextView);
            votedUp.setText(latestUpVotedPost);

            String latestDownVotedPost = AppManager.getUserProfile().getActivity().getLatestDownVotedPostText();
            TextView votedDown = (TextView) findViewById(R.id.votedDownTextView);
            votedDown.setText(latestDownVotedPost);

        } catch (IOException e) {
            Toast toast = Toast.makeText(this, "Problem with server", Toast.LENGTH_LONG);
            toast.show();
            return;
        }


    }

    public void navigate(View view){

        startActivity(new Intent(this, NewsFeedActivity.class));
    }
}