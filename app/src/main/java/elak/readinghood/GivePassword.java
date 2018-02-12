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

        final EditText myEdiText1 = (EditText)findViewById(R.id.givePasswordpasswordEditText);
        myEdiText1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    myEdiText1.setHint("");
                else
                    myEdiText1.setHint("password");
            }
        });

        final EditText myEdiText2 = (EditText)findViewById(R.id.givePasswordconfirmEditText);
        myEdiText2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    myEdiText2.setHint("");
                else
                    myEdiText2.setHint("confirm password");
            }
        });
    }

    public void next(View view) {
        password = ((EditText) findViewById(R.id.givePasswordpasswordEditText)).getText().toString();
        rePassword = ((EditText) findViewById(R.id.givePasswordconfirmEditText)).getText().toString();


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
