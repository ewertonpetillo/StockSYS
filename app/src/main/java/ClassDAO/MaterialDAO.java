package ClassDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import Class.Material;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrador on 19/05/2017.
 */

public class MaterialDAO extends SQLiteOpenHelper {
    private static final int VERSAO = 1;
    private static final String TABELA = "Material";
    private static final String DATABASE = "Stocksys";


    public MaterialDAO(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA+"("
                + "id INTEGER PRIMARY KEY NOT NULL,"
                + "descricao TEXT NOT NULL,"
                + "unMedida TEXT NOT NULL,"
                + "estoqueMin INTEGER NOT NULL,"
                + "estoqueIni INTEGER NOT NULL,"
                + "estoqueAtual INTEGER NOT NULL);";

                db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion <= newVersion){
            onCreate(db);
        }else{
            String sql = "DROP TABLE IF EXISTS " +TABELA;
            db.execSQL(sql);
            onCreate(db);
        }
    }

    public void cadMaterial(Material material){

        ContentValues values = new ContentValues();

        values.put("descricao", material.getDescricao());
        values.put("unMedida", material.getUnMedida());
        values.put("estoqueMin", material.getEstoqueMin());
        values.put("estoqueIni", material.getEstoqueIni());
        values.put("estoqueAtual", material.getEstoqueAtual());

        getWritableDatabase().insert(TABELA, null, values);
    }

    public List<Material> listaMaterial() {

        List<Material> lista = new ArrayList<Material>();

        String sql = "SELECT * FROM Material order by descricao";

        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        try {
            while (cursor.moveToNext()) {
                Material material = new Material();

                material.setId(cursor.getLong(0));
                material.setDescricao(cursor.getString(1));
                material.setUnMedida(cursor.getString(2));
                material.setEstoqueMin(cursor.getInt(3));
                material.setEstoqueIni(cursor.getInt(4));
                material.setEstoqueAtual(cursor.getInt(5));

                lista.add(material);
            }
        } catch (Exception e) {
            Log.e("ERRO", e.getMessage());
        } finally {
            cursor.close();
        }
        return lista;
    }

    public void atualizaEstoque(String nome, Integer vRetirado){
        ContentValues values = new ContentValues();
        values.put("estoqueAtual", vRetirado);

        String[] args = new String[] {nome};

        getWritableDatabase().update(TABELA, values, "descricao=?", args);
    }
}
