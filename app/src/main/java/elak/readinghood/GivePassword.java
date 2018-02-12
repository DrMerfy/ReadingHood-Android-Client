package elak.readinghood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import elak.readinghood.backend.api.AppManager;

public class GivePassword extends AppCompatActivity {
    String password;
    String rePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_givepassword);
    }

    public void next(View view) {
        password = ((EditText) findViewById(R.id.passwordSignUpEditText)).getText().toString();
        rePassword = ((EditText) findViewById(R.id.rePasswordSignUpEditText)).getText().toString();


        Toast toast = null;
        String message = AppManager.getStartUpManager().registrationSetPasswordAndRePassword(password, rePassword);
        if (!message.equals("Success")) {
            toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        try {
            AppManager.getStartUpManager().createUserProfile();
            AppManager.setUserProfile();
            startActivity(new Intent(this, NewsFeedActivity.class));
        } catch (IOException e) {
            toast = Toast.makeText(this, "Problem with server", Toast.LENGTH_LONG);
            toast.show();
            return;
        }


    }
}
