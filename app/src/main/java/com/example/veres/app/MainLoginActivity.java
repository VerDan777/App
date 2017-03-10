package com.example.veres.app;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
//
public class MainLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText ETmail;
    private EditText ETpassword;
    private  EditText etpas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                } else {
                    // User is signed out

                }
                // ...
            }
        };
        ETmail = (EditText) findViewById(R.id.editTextEmail);
        ETpassword = (EditText) findViewById(R.id.editTextPassword);
        findViewById(R.id.buttonLogin).setOnClickListener(this);
        findViewById(R.id.buttonRegister).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonLogin)  {
            signing(ETmail.getText().toString(), ETpassword.getText().toString());
        } else if (v.getId() == R.id.buttonRegister) {
            registration(ETmail.getText().toString(), ETpassword.getText().toString());
        }
    }
    public void  signing(String email,String password)
    {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(MainLoginActivity.this, "Авторизация прошла успешна", Toast.LENGTH_SHORT).show();
                } else
                {Toast.makeText(MainLoginActivity.this,"Авторизация провалена",Toast.LENGTH_SHORT).show();}
            }
        });
    }
    public void  registration (String email,String password)
    {
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(MainLoginActivity.this, "Регистрация прошла успешна", Toast.LENGTH_SHORT).show();
                } else
                {Toast.makeText(MainLoginActivity.this,"Регистрация провалена",Toast.LENGTH_SHORT).show();}
            }
        });
    }
}






