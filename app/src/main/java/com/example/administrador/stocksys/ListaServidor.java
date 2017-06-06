package com.example.administrador.stocksys;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import Class.Servidor;
import ClassDAO.ServidorDAO;

import java.util.List;

public class ListaServidor extends AppCompatActivity {
    private ListView lvServidor;
    private List<Servidor> listaServidor;
    private ArrayAdapter<Servidor> adaptador;
    private int adapterLayout = android.R.layout.simple_list_item_1;
    private TextView qntServidores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_servidor);

        lvServidor = (ListView) findViewById(R.id.listServ);

        this.carregarLista();

        qntServidores = (TextView) findViewById(R.id.txtQnt);

        qntServidores.setText("Nº servidores: " + listaServidor.size());

        lvServidor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int

                    position, long id) {
                Intent it = new Intent(ListaServidor.this, AltExcServidor.class);
                Bundle params = new Bundle();
                Object c = lvServidor.getItemAtPosition(position);
                params.putString("servidor", c.toString());
                it.putExtras(params);
                startActivity(it);
                finish();
            }
        });
    }


    private void carregarLista() {
        ServidorDAO dao = new ServidorDAO(ListaServidor.this);

        this.listaServidor = dao.listaServidor();
        dao.close();

        this.adaptador = new ArrayAdapter<Servidor>(ListaServidor.this, adapterLayout, listaServidor);

        this.lvServidor.setAdapter(adaptador);
    }
}
