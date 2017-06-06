package com.example.administrador.stocksys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ClassDAO.ServidorDAO;
import Class.Servidor;

public class AltExcServidor extends AppCompatActivity {
    private EditText nome;
    private EditText cargo;
    private EditText matricula;
    private EditText sala;
    private EditText serie;
    private EditText turno;
    private Button alterar;
    private Button excluir;
    private Button pesquisar;
    private String servidor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alt_exc_servidor);

        nome = (EditText) findViewById(R.id.editNome);
        cargo = (EditText) findViewById(R.id.editCargo);
        matricula = (EditText) findViewById(R.id.editMatricula);
        sala = (EditText) findViewById(R.id.editSala);
        serie = (EditText) findViewById(R.id.editSerie);
        turno = (EditText) findViewById(R.id.editTurno);



        Intent it = getIntent();
        if(it != null){
            Bundle params = it.getExtras();
            if(params != null){
                servidor = params.getString("servidor");
                Servidor server = new Servidor();
                ServidorDAO serv = new ServidorDAO(AltExcServidor.this);
                server = serv.buscarPorNome(servidor);
                serv.close();
                Toast.makeText(AltExcServidor.this, "Buscando " + servidor, Toast.LENGTH_SHORT).show();
                nome.setText(server.getNome());
                cargo.setText(server.getCargo());
                matricula.setText(server.getMatricula());
                sala.setText(server.getSala());
                serie.setText(server.getSerie());
                turno.setText(server.getTurno());
            }
        }
        alterar = (Button) findViewById(R.id.btAltera);
        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServidorDAO dao = new ServidorDAO(AltExcServidor.this);
                //dao.buscarPorNome(servidor);
                dao.alterarServidor(servidor, nome.getText().toString(), cargo.getText().toString(),
                                    matricula.getText().toString(), sala.getText().toString(),
                                    serie.getText().toString(), turno.getText().toString());
                dao.close();
                Toast.makeText(AltExcServidor.this, "Servidor alterado com sucesso.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        excluir = (Button) findViewById(R.id.btExclui);
        excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServidorDAO dao = new ServidorDAO(AltExcServidor.this);
                dao.excluirServidor(nome.getText().toString());
                dao.close();
                Toast.makeText(AltExcServidor.this, "Servidor excluido com sucesso.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
