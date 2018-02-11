package elak.readinghood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void startLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void startSignup(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
    }
}
