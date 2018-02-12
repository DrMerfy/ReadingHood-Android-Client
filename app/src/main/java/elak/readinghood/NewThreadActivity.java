package elak.readinghood;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

import elak.readinghood.backend.api.AppManager;


/**
 * Created by User on 10/2/2018.
 */

public class NewThreadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_post);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void postAction(View view) {

        EditText title = (EditText) findViewById(R.id.title_input);
        TextInputEditText description = (TextInputEditText) findViewById(R.id.desc_input);
        TextInputEditText tags = (TextInputEditText) findViewById(R.id.tags_input);
        String t = tags.getText().toString().replaceAll(" ", "");
        String[] ta = t.split(",");
        HashSet<String> tagsStrings = new HashSet<>();
        tagsStrings.addAll(Arrays.asList(ta));
        try {
            String result = AppManager.createThread(title.getText().toString(), description.getText().toString(), tagsStrings);
            if (!result.equals("Success")) {
                Toast toast = Toast.makeText(this, result, Toast.LENGTH_LONG);
                toast.show();
                return;
            }
        } catch (IOException e) {
            Toast toast = Toast.makeText(this, "Problem with server", Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        Intent intent = new Intent(this, NewsFeedActivity.class);
        startActivity(intent);

    }
}
