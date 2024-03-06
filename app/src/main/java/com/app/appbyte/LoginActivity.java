package com.app.appbyte;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

   private EditText usernameEditText, passwordEditText;
    Button loginButton;
    ByteDataBase byteDataBase;

    TextView RedirectSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.nameEditText);
        passwordEditText = findViewById(R.id.contraEdit);
        loginButton = findViewById(R.id.loginButton);
        RedirectSign = findViewById(R.id.RedirectSign);
        byteDataBase = new ByteDataBase(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (byteDataBase.checkLogin(username, password)) {
                    // Credenciales válidas, iniciar MainActivity
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Nombre de usuario o contraseña incorrectos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        RedirectSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistroActivity.class));
            }
        });
    }
}
