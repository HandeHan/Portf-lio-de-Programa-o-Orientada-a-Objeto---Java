package com.meuprojeto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreen extends Activity {

    private EditText edtEmail, edtSenha;
    private Button btnLogin, btnCadastro;

    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        btnLogin = findViewById(R.id.btnLogin);
        btnCadastro = findViewById(R.id.btnCadastro);

        userDAO = new UserDAO(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String senha = edtSenha.getText().toString();

                if (userDAO.autenticar(email, senha)) {
                    Toast.makeText(LoginScreen.this,
                            "Login realizado com sucesso!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginScreen.this,
                            "Email ou senha incorretos",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCadastro.setOnClickListener(v ->
                startActivity(new Intent(LoginScreen.this, RegisterScreen.class))
        );
    }
}
