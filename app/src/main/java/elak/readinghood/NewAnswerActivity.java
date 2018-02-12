package elak.readinghood;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

import elak.readinghood.R;
import elak.readinghood.backend.api.AppManager;
import elak.readinghood.backend.threads.Thread;

/**
 * Created by User on 11/2/2018.
 */

public class NewAnswerActivity extends AppCompatActivity {

    Thread currentThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_answer);

        Intent intent = getIntent();
        currentThread = (Thread) intent.getSerializableExtra("thread");

    }

    public void postAnswer(View view) {

        EditText answer = (EditText) findViewById(R.id.answer_input);

        try {
            String result = currentThread.answerThreadWithAPost(answer.getText().toString());

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

        Intent intent = new Intent(this, ShowThreadActivity.class);
        Bundle b = new Bundle();
        b.putInt("id", currentThread.getId()); //Your id
        intent.putExtras(b);
        startActivity(intent);


    }

}
