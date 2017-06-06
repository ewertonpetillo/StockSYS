package com.example.administrador.stocksys;

import android.icu.text.DateFormat;
import android.icu.text.RelativeDateTimeFormatter;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Date;
import java.util.List;
import Class.*;
import ClassDAO.*;

public class SaidaMaterial extends AppCompatActivity {
    private Spinner spServidor;
    private Spinner spMaterial;
    private EditText edtData;
    private EditText quantidade;
    private Button buttonSalvar;
    private Button buttonCancel;
    private ArrayAdapter adaptadorServ;
    private ArrayAdapter adaptadorMat;
    private List<Servidor> listaServidor;
    private List<Material> listaMaterial;
    private Integer valorRetirado;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saida_material);

        spMaterial = (Spinner) findViewById(R.id.spMaterial);
        spServidor = (Spinner) findViewById(R.id.spServer);
        edtData = (EditText) findViewById(R.id.edtDataSaida);

        quantidade = (EditText) findViewById(R.id.edtQntSaida);


        final ItemMaterial item = new ItemMaterial();
        this.carregarServidor();

        this.carregarMaterial();

        spServidor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Servidor serv = (Servidor) parent.getSelectedItem();
                item.setServidor(serv);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spMaterial.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Material mat = (Material) parent.getSelectedItem();
                item.setMateriais(mat);
                valorRetirado = mat.getEstoqueAtual();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonSalvar = (Button) findViewById(R.id.btnSalvarSaida);
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setData(edtData.getText().toString());
                item.setQuantidade(Integer.parseInt(quantidade.getText().toString()));

                ItemMaterialDAO itMatDAO = new ItemMaterialDAO(SaidaMaterial.this);

                itMatDAO.cadSaidaMaterial(item);
                itMatDAO.atualizaEstoque(item.getMateriais().getDescricao(), (valorRetirado - item.getQuantidade()));
                itMatDAO.close();

                Toast.makeText(SaidaMaterial.this, "Saída de material cadastrada com sucesso. ", Toast.LENGTH_LONG).show();

                finish();
                }
        });
        buttonCancel = (Button) findViewById(R.id.button2);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void carregarServidor() {
        ServidorDAO dao = new ServidorDAO(SaidaMaterial.this);

        this.listaServidor = dao.listaServidor();
        dao.close();

        this.adaptadorServ = new ArrayAdapter<Servidor>(SaidaMaterial.this, android.R.layout.simple_spinner_item, listaServidor);

        this.spServidor.setAdapter(adaptadorServ);
    }

    private void carregarMaterial() {
        MaterialDAO matDAO = new MaterialDAO(SaidaMaterial.this);

        this.listaMaterial = matDAO.listaMaterial();
        matDAO.close();

        this.adaptadorMat = new ArrayAdapter<Material>(SaidaMaterial.this, android.R.layout.simple_spinner_item, listaMaterial);

        this.spMaterial.setAdapter(adaptadorMat);
    }
}
