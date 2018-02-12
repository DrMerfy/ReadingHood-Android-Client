package elak.readinghood;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import elak.readinghood.backend.api.AppManager;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);
        TextView wordView = new TextView(this);

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
            repsect.setText(respectString);


            AppManager.getUserProfile().setActivity();


            System.out.println(AppManager.getUserProfile().getActivity().getLatestUpVotedPostText());
            System.out.println(AppManager.getUserProfile().getActivity().getLatestDownVotedPostText());
            System.out.println(AppManager.getUserProfile().getActivity().getLatestCreatedThreadTitle());

        } catch (IOException e) {
            Toast toast = Toast.makeText(this, "Problem with server", Toast.LENGTH_LONG);
            toast.show();
            return;
        }


    }
}
