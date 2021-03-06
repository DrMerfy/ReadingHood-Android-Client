package elak.readinghood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import elak.readinghood.backend.api.AppManager;

public class LoginActivity extends AppCompatActivity {
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText myEdiText1 = (EditText)findViewById(R.id.loginEmailEditText);
        myEdiText1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    myEdiText1.setHint("");
                else
                    myEdiText1.setHint("email");
            }
        });

        final EditText myEdiText2 = (EditText)findViewById(R.id.loginPasswordEditText);
        myEdiText2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    myEdiText2.setHint("");
                else
                    myEdiText2.setHint("password");
            }
        });
    }

    public void login(View view) {
        email = ((EditText) findViewById(R.id.loginEmailEditText)).getText().toString();
        password = ((EditText) findViewById(R.id.loginPasswordEditText)).getText().toString();

        try {
            String message=AppManager.getStartUpManager().login(email, password);
            if (!message.equals("Success")) {
                Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
                toast.show();
                return;
            }
        } catch (IOException e) {
            Toast toast = Toast.makeText(this, e.toString(), Toast.LENGTH_LONG);
            toast.show();
            e.printStackTrace();
            return;
        }

        try {
            AppManager.setUserProfile();
            startActivity(new Intent(this, NewsFeedActivity.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
