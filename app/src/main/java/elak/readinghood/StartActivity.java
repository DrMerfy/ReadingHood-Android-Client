package elak.readinghood;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import elak.readinghood.backend.api.AppManager;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }

    public void startLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void startSignup(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            AppManager.logOut(); // when back is pressed from the newsfeed activity
        }
    }
}
