package com.example.administrador.stocksys;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import Class.Usuario;
import ClassDAO.UsuarioDAO;

public class MainActivity extends AppCompatActivity {
    private EditText usuario;
    private EditText senha;
    private Button btnLogin;
    private TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        texto = (TextView) findViewById(R.id.txtCad);

        texto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UsuarioActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = (EditText) findViewById(R.id.edtUser);
                senha = (EditText) findViewById(R.id.edtPass);

                String login = usuario.getText().toString();
                String password = senha.getText().toString();

                UsuarioDAO userDAO = new UsuarioDAO(MainActivity.this);

                Usuario user = userDAO.login(login,password);

                userDAO.close();

                if(user != null){
                    Intent it = new Intent(MainActivity.this, InicialActivity.class);
                    Bundle params = new Bundle();
                    params.putString("usuario", user.getNomeUser());
                    it.putExtras(params);
                    startActivity(it);
                }else{

                    Toast.makeText(MainActivity.this, "Usuário/Senha Inválidos", Toast.LENGTH_SHORT).show();
                }

            }
        });
        finish();
    }
}
