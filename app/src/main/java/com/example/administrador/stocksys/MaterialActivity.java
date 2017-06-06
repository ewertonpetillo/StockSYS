package com.example.administrador.stocksys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Class.Material;
import ClassDAO.MaterialDAO;

public class MaterialActivity extends AppCompatActivity {
    private EditText descricao;
    private EditText unMedida;
    private EditText estoqueMin;
    private EditText estoqueIni;
    private EditText estoqueAtual;
    private Button salvar;
    private Button cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);

        descricao = (EditText) findViewById(R.id.edtDesc);
        unMedida = (EditText) findViewById(R.id.edtUnMed);
        estoqueMin = (EditText) findViewById(R.id.edtMin);
        estoqueIni = (EditText) findViewById(R.id.edtIni);
        estoqueAtual = (EditText) findViewById(R.id.edtAtual);

        salvar = (Button) findViewById(R.id.btSave);
        cancelar = (Button) findViewById(R.id.btnCancel);

        final Material material = new Material();

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                material.setDescricao(descricao.getText().toString());
                material.setUnMedida(unMedida.getText().toString());
                material.setEstoqueMin(Integer.parseInt(estoqueMin.getText().toString()));
                material.setEstoqueIni(Integer.parseInt(estoqueIni.getText().toString()));
                material.setEstoqueAtual(Integer.parseInt(estoqueAtual.getText().toString()));

                MaterialDAO matDAO = new MaterialDAO(MaterialActivity.this);

                matDAO.cadMaterial(material);
                matDAO.close();

                Toast.makeText(MaterialActivity.this, "Material cadastrado com sucesso. ", Toast.LENGTH_LONG).show();

                finish();

            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
