package ClassDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import Class.*;
/**
 * Created by ewerton on 31/05/17.
 */

public class ItemMaterialDAO extends SQLiteOpenHelper {
    private static final int VERSAO = 1;
    private static final String TABELA = "ItemMaterial";
    private static final String DATABASE = "Stocksys";


    public ItemMaterialDAO(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql4 = "CREATE TABLE ItemMaterial("
                + "id INTEGER PRIMARY KEY NOT NULL,"
                + "data TEXT NOT NULL,"
                + "quantidade INTEGER NOT NULL,"
                + "codServidor INTEGER,"
                + "codMaterial INTEGER,"
                + "CONSTRAINT fk_servidor FOREIGN KEY (codServidor) REFERENCES Servidor (id),"
                + "CONSTRAINT fk_material FOREIGN KEY (codMaterial) REFERENCES Material (id));";

        db.execSQL(sql4);
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

    public void cadSaidaMaterial(ItemMaterial material){

        ContentValues values = new ContentValues();

        values.put("data", material.getData());
        values.put("quantidade", material.getQuantidade());
        values.put("codServidor", material.getServidor().getId());
        values.put("codMaterial", material.getMateriais().getId());

        getWritableDatabase().insert(TABELA, null, values);
    }

    public void atualizaEstoque(String nome, Integer vRetirado){
        ContentValues values = new ContentValues();

        values.put("estoqueAtual", vRetirado);

        String[] args = new String[] {nome};

        getWritableDatabase().update("Material", values, "descricao=?", args);
    }

}
