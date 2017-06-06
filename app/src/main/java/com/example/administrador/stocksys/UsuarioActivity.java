package com.example.administrador.stocksys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Class.Usuario;
import ClassDAO.UsuarioDAO;

public class UsuarioActivity extends AppCompatActivity {

    private Button btnCadastro;
    private Button btnCancelar;
    private EditText nome;
    private EditText usuario;
    private EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        nome = (EditText) findViewById(R.id.edtNome);
        usuario = (EditText) findViewById(R.id.edtUsuario);
        senha = (EditText) findViewById(R.id.edtSenha);

        final Usuario user = new Usuario();

        btnCadastro = (Button) findViewById(R.id.btnCadastro);
        btnCancelar = (Button) findViewById(R.id.btnCancel);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setNomeUser(nome.getText().toString());
                user.setLoginUser(usuario.getText().toString());
                user.setSenhaUser(senha.getText().toString());

                UsuarioDAO userDao = new UsuarioDAO(UsuarioActivity.this);

                userDao.cadUser(user);
                userDao.close();

                Toast.makeText(UsuarioActivity.this, "Usuário cadastrado com sucesso. ", Toast.LENGTH_LONG).show();

                finish();
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
