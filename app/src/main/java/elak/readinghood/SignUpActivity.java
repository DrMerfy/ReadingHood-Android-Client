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

        final EditText myEdiText1 = (EditText)findViewById(R.id.usernameSignUpEditText);
        myEdiText1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    myEdiText1.setHint("");
                else
                    myEdiText1.setHint("username");
            }
        });

        final EditText myEdiText2 = (EditText)findViewById(R.id.emailSignUpEditText);
        myEdiText2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    myEdiText2.setHint("");
                else
                    myEdiText2.setHint("email");
            }
        });
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
