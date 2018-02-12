package elak.readinghood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import elak.readinghood.backend.api.AppManager;

public class SignUpActivity extends AppCompatActivity {
    String username;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void next(View view) {
        username=((EditText)findViewById(R.id.usernameSignUpEditText)).getText().toString();
        email=((EditText)findViewById(R.id.emailSignUpEditText)).getText().toString();


        Toast toast = null;
        try {
            String message = AppManager.getStartUpManager().registrationSetEMailAndUsername(email,username);
            if (!message.equals("Success")){
                toast = Toast.makeText(this,message,Toast.LENGTH_LONG);
                toast.show();
                return;
            }

        } catch (Exception e) {
            toast = Toast.makeText(this,"Problem with server",Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        startActivity(new Intent(this, ComeFromActivity.class));

    }
}
