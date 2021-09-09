package com.example.sengineeringhw02restapi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sengineeringhw02restapi.R;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();

    EditText usernameEt;
    EditText passwordEt;
    Button loginBtn;
    String name;
    String password;
    public static String[] loginNames;
    boolean userDoesNotExists;
    public static int userLocation;
    static String pass = "password";

    public static String getPass() {
        return pass;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginNames = generateUsers();

        usernameEt = findViewById(R.id.usernameEt);
        passwordEt = findViewById(R.id.passwordEt);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDoesNotExists = true;
                name = usernameEt.getText().toString();
                password = passwordEt.getText().toString();

                for(int i = 0;i<loginNames.length;i++){
                    if(name.equals(loginNames[i])){
                        userDoesNotExists = false;
                        if(password.equals(pass)){
                            Log.i("LOGIN:","LG");
                            userLocation = i +1;
                            Intent j = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(j);
                        }else {
                            Toast.makeText(getApplicationContext(), "WRONG PASSWORD", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                if (userDoesNotExists){
                    Toast.makeText(getApplicationContext(), "USER DOES NOT EXIST", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    public static String[] generateUsers(){
        return new String[]{
                "Bret", "Antonette", "Samantha", "Karianne", "Kamren",
                "Leopoldo_Corkery", "Elwyn.Skiles", "Maxime_Nienow", "Delphine", "Moriah.Stanton"};
    }

}