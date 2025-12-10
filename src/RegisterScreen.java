package com.meuprojeto;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterScreen extends Activity {

    private EditText edtNome, edtEmail, edtSenha;
    private Button btnSalvar;

    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);

        edtNome = findViewById(R.id.edtNome);
        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);

        btnSalvar = findViewById(R.id.btnSalvar);

        userDAO = new UserDAO(this);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = edtNome.getText().toString();
                String email = edtEmail.getText().toString();
                String senha = edtSenha.getText().toString();

                User user = new User(nome, email, senha);

                boolean inseriu = userDAO.inserirUsuario(user);

                if (inseriu) {
                    Toast.makeText(RegisterScreen.this,
                            "Usuário cadastrado com sucesso!",
                            Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RegisterScreen.this,
                            "Falha ao cadastrar usuário.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
