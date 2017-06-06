package ClassDAO;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import Class.Servidor;

public class ServidorDAO extends SQLiteOpenHelper{
    private static final int VERSAO = 1;
    private static final String TABELA = "Servidor";
    private static final String DATABASE = "Stocksys";


    public ServidorDAO(Context context) {

        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE "+TABELA+"("
                + "id INTEGER PRIMARY KEY NOT NULL, "
                + "nome TEXT, "
                + "cargo TEXT, "
                + "matricula TEXT, "
                + "sala TEXT, "
                + "serie TEXT, "
                + "turno TEXT);";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            String sql = "DROP TABLE IF EXISTS " +TABELA;
            db.execSQL(sql);
            onCreate(db);
    }

    public void cadServidor(Servidor serv){

        ContentValues values = new ContentValues();

        values.put("nome", serv.getNome());
        values.put("cargo", serv.getCargo());
        values.put("matricula", serv.getMatricula());
        values.put("sala", serv.getSala());
        values.put("serie", serv.getSerie());
        values.put("turno", serv.getTurno());

        getWritableDatabase().insert(TABELA, null, values);
    }

    public List<Servidor> listaServidor() {

        List<Servidor> lista = new ArrayList<Servidor>();

        String sql = "SELECT * FROM Servidor order by nome";

        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        try {
            while (cursor.moveToNext()) {
                Servidor servidor = new Servidor();

                servidor.setId(cursor.getLong(0));
                servidor.setNome(cursor.getString(1));
                servidor.setCargo(cursor.getString(2));
                servidor.setMatricula(cursor.getString(3));
                servidor.setSala(cursor.getString(4));
                servidor.setSerie(cursor.getString(5));
                servidor.setTurno(cursor.getString(6));

                lista.add(servidor);
            }
        } catch (Exception e) {
            Log.e("ERRO", e.getMessage());
        } finally {
            cursor.close();
        }
        return lista;
    }

    public Servidor buscarPorNome(String nome){

        Servidor servidor = null;
        String sql = "Select * from "+TABELA+" where nome = '"+nome+"'";
        //Objeto que recebe registros do banco da dados
        Cursor cursor = getReadableDatabase().rawQuery(sql,null);

        try {
            if (cursor.moveToNext()) {

                servidor = new Servidor();

                servidor.setId(cursor.getLong(0));
                servidor.setNome(cursor.getString(1));
                servidor.setCargo(cursor.getString(2));
                servidor.setMatricula(cursor.getString(3));
                servidor.setSala(cursor.getString(4));
                servidor.setSerie(cursor.getString(5));
                servidor.setTurno(cursor.getString(6));
            }
        } catch (Exception e) {
            Log.e("ERRO ", e.getMessage());
        } finally {
            cursor.close();
        }
        return servidor;
    }

    public void alterarServidor (String oldNome, String nome, String cargo, String matricula, String sala,
                                 String serie, String turno){

        ContentValues values = new ContentValues();

        values.put("nome", nome);
        values.put("cargo", cargo);
        values.put("matricula", matricula);
        values.put("sala", sala);
        values.put("serie", serie);
        values.put("turno", turno);

        String[] args = new String[] {oldNome};

        getWritableDatabase().update(TABELA, values, "nome=?", args);
    }

    public void excluirServidor(String nome){

        String[] args = new String[] {nome};

        getWritableDatabase().delete(TABELA, "nome=?", args);
    }
}
