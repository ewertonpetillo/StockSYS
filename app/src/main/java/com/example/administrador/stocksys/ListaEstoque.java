package com.example.administrador.stocksys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import Class.Material;
import ClassDAO.MaterialDAO;


public class ListaEstoque extends AppCompatActivity {
    private ListView lvMaterial;
    private List<Material> listaMaterial;
    private int adapterLayout = android.R.layout.simple_expandable_list_item_1;
    private ArrayAdapter<Material> adaptador;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_estoque);

        lvMaterial = (ListView) findViewById(R.id.listMat);

        this.carregarMaterial();
    }

    private void carregarMaterial() {
        MaterialDAO matDAO = new MaterialDAO(ListaEstoque.this);

        this.listaMaterial = matDAO.listaMaterial();
        matDAO.close();

        this.adaptador = new ArrayAdapter<Material>(ListaEstoque.this, adapterLayout, listaMaterial);

        this.lvMaterial.setAdapter(adaptador);

    }
}
