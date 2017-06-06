package com.example.administrador.stocksys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Class.Servidor;
import ClassDAO.ServidorDAO;

public class ServidorActivity extends AppCompatActivity {

    private EditText nome;
    private EditText cargo;
    private EditText matricula;
    private EditText sala;
    private EditText serie;
    private EditText turno;
    private Button botaoSalvar;
    private Button botaoCancelar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servidor);

        nome = (EditText) findViewById(R.id.edtNome);
        cargo = (EditText) findViewById(R.id.edtCargo);
        matricula = (EditText) findViewById(R.id.edtMatricula);
        sala = (EditText) findViewById(R.id.edtSala);
        serie = (EditText) findViewById(R.id.edtSerie);
        turno = (EditText) findViewById(R.id.edtTurno);

        final Servidor servidor = new Servidor();

        botaoSalvar = (Button) findViewById(R.id.btnSalvar);
        botaoCancelar = (Button) findViewById(R.id.btnCalcelar);



        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                servidor.setNome(nome.getText().toString());
                servidor.setCargo(cargo.getText().toString());
                servidor.setMatricula(matricula.getText().toString());
                servidor.setSala(sala.getText().toString());
                servidor.setSerie(serie.getText().toString());
                servidor.setTurno(turno.getText().toString());

                ServidorDAO servDAO = new ServidorDAO(ServidorActivity.this);

                servDAO.cadServidor(servidor);
                servDAO.close();

                Toast.makeText(ServidorActivity.this, "Servidor cadastrado com sucesso. ", Toast.LENGTH_LONG).show();

                finish();

            }
        });

        botaoCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
