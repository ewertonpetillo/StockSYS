package com.example.administrador.stocksys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import Class.Usuario;
import ClassDAO.UsuarioDAO;

import java.util.List;

public class ListaUsuarios extends AppCompatActivity {
    private ListView lvUser;
    private List<Usuario> listaUser;
    private ArrayAdapter<Usuario> adaptador;
    private int adapterLayout = android.R.layout.simple_list_item_1;
    private TextView qntUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);

        lvUser = (ListView) findViewById(R.id.listUser);

        qntUsuarios = (TextView) findViewById(R.id.txtQntUser);

        this.carregarLista();

        qntUsuarios.setText("Nº de usuários: " + listaUser.size());

    }

    private void carregarLista() {
        UsuarioDAO userDAO = new UsuarioDAO(ListaUsuarios.this);

        this.listaUser = userDAO.listaUsuario();
        userDAO.close();

        this.adaptador = new ArrayAdapter<Usuario>(ListaUsuarios.this, adapterLayout, listaUser);

        this.lvUser.setAdapter(adaptador);

    }
}
