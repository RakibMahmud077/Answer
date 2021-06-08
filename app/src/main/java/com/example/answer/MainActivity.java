package com.example.answer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText signInEmailEditText, signInPasswordEditText;
    private TextView signUpTextView;
    private Button signInButton;
    private ProgressBar signInProgressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Sign in Activity");

        signInEmailEditText = (EditText) findViewById(R.id.signInEmailId);
        signInPasswordEditText = (EditText) findViewById(R.id.SignInpasswordId);
        signUpTextView = (TextView) findViewById(R.id.textSignUpId);
        signInButton = (Button) findViewById(R.id.SignInButtonId);
        signInProgressBar=(ProgressBar)findViewById(R.id.signInProgressBarId);
        signUpTextView.setOnClickListener(this);
        signInButton.setOnClickListener(this);
        signInProgressBar.setOnClickListener(this);
        mAuth=FirebaseAuth.getInstance();




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.SignInButtonId:
            {
                userLogin();
                break;
            }
            case R.id.textSignUpId: {
                Intent intent = new Intent(getApplicationContext(), sign_up_activity.class);
                startActivity(intent);
                break;
            }

        }
    }

    private void userLogin() {
        String email = signInEmailEditText.getText().toString().trim();
        String password = signInPasswordEditText.getText().toString().trim();
        if (email.isEmpty()) {
            signInEmailEditText.setError("Enter an email address");
            signInEmailEditText.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signInEmailEditText.setError("Enter a valid email address");
            signInEmailEditText.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            signInPasswordEditText.setError("Enter a password");
            signInPasswordEditText.requestFocus();
            return;
        }
        if (password.length() < 6) {
            signInPasswordEditText.setError("Minimum length of a password should be 6");
            signInPasswordEditText.requestFocus();
            return;
        }
        signInProgressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                signInProgressBar.setVisibility(View.GONE);
                if(task.isSuccessful())
                {
                    Intent intent=new Intent(getApplicationContext(),subject_activity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Login Unsuccessful",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}