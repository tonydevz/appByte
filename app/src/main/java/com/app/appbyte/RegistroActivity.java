package com.app.appbyte;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class RegistroActivity extends AppCompatActivity {

   private EditText usernameEditText, passwordEditText;
   private Button registrarButton;
   private ByteDataBase byteDataBase;
   private TextView RedirectLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        usernameEditText = findViewById(R.id.nameEditTextSign);
        passwordEditText = findViewById(R.id.contraEditSign);
        registrarButton = findViewById(R.id.SignButton);
        RedirectLogin = findViewById(R.id.RedirectLogin);
        byteDataBase = new ByteDataBase(this);

        registrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (username.length() < 8) {
                    Toast.makeText(RegistroActivity.this, "El nombre de usuario debe tener al menos 8 caracteres.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6 || !tieneMayuscula(password)) {
                    Toast.makeText(RegistroActivity.this, "La contraseña debe tener al menos 6 caracteres y contener al menos una letra mayúscula.", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean resultado = byteDataBase.insertData(username, password);
                if (resultado) {
                    Toast.makeText(RegistroActivity.this, "Usuario registrado correctamente.", Toast.LENGTH_SHORT).show();
                    // Aquí puedes redirigir a la actividad de inicio de sesión o hacer cualquier otra acción necesaria.
                } else {
                    Toast.makeText(RegistroActivity.this, "Error al registrar usuario.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        RedirectLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( RegistroActivity.this, LoginActivity.class));
            }
        });

    }

    private boolean tieneMayuscula(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }


}
