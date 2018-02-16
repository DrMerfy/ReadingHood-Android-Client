package elak.readinghood;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import elak.readinghood.backend.api.AppManager;
import elak.readinghood.backend.profiles.Profile;

public class SettingsActivity extends AppCompatActivity {
    private String changedName="";
    private String changedSurname="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
    public void editName(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Name");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        //Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                changedName = input.getText().toString();
                try {
                    String message= AppManager.getUserProfile().editName(changedName);
                    if (!message.equals("Success")) {
                        Toast toast = Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG);
                        toast.show();
                        return;
                    }
                    Toast toast = Toast.makeText(getApplicationContext(),"Name Changed Successfully", Toast.LENGTH_LONG);
                    toast.show();
                } catch (IOException e) {
                    Toast toast = Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG);
                    toast.show();
                    e.printStackTrace();
                    return;
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public void editSurname(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Surname");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        //Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                changedSurname = input.getText().toString();
                try {
                    String message= AppManager.getUserProfile().editSurname(changedSurname);
                    if (!message.equals("Success")) {
                        Toast toast = Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG);
                        toast.show();
                        return;
                    }
                    Toast toast = Toast.makeText(getApplicationContext(),"Surname Changed Successfully", Toast.LENGTH_LONG);
                    toast.show();
                } catch (IOException e) {
                    Toast toast = Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG);
                    toast.show();
                    e.printStackTrace();
                    return;
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public void logOut(View view){
        String message=AppManager.logOut();
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        toast.show();

        Intent intent = new Intent(this, StartActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void navigate1(View view){

        startActivity(new Intent(this, ProfileActivity.class));
    }

    public void navigate2(View view){

        startActivity(new Intent(this, NewsFeedActivity.class));
    }



}
