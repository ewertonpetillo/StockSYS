package ClassDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Class.Usuario;

/**
 * Created by Administrador on 19/05/2017.
 */

public class UsuarioDAO extends SQLiteOpenHelper{
    private static final int VERSAO = 1;
    private static final String TABELA = "Usuario";
    private static final String DATABASE = "Stocksys";

    public UsuarioDAO(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql1 = "CREATE TABLE Usuario("
                + "id INTEGER PRIMARY KEY NOT NULL,"
                + "nome TEXT NOT NULL,"
                + "login TEXT NOT NULL,"
                + "senha TEXT NOT NULL);";
        db.execSQL(sql1);

        String sql2 = "CREATE TABLE Servidor("
                + "id INTEGER PRIMARY KEY NOT NULL, "
                + "nome TEXT, "
                + "cargo TEXT, "
                + "matricula TEXT, "
                + "sala TEXT, "
                + "serie TEXT, "
                + "turno TEXT);";
        db.execSQL(sql2);

            String sql3 = "CREATE TABLE Material("
                    + "id INTEGER PRIMARY KEY NOT NULL,"
                    + "descricao TEXT NOT NULL,"
                    + "unMedida TEXT NOT NULL,"
                    + "estoqueMin INTEGER NOT NULL,"
                    + "estoqueIni INTEGER NOT NULL,"
                    + "estoqueAtual INTEGER NOT NULL);";

        db.execSQL(sql3);

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
        String sql = "DROP TABLE IF EXISTS " +TABELA;
        db.execSQL(sql);
        onCreate(db);
    }

    public void cadUser(Usuario user){
        ContentValues values = new ContentValues();

        values.put("nome", user.getNomeUser());
        values.put("login", user.getLoginUser());
        values.put("senha", user.getSenhaUser());

        getWritableDatabase().insert(TABELA, null, values);
    }

    public Usuario login(String usuario, String senha){
        Usuario user = null;

        String sql = "SELECT * FROM Usuario WHERE login ='"+usuario+ "' AND senha ='"+senha+"'";

        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        try{
            if(cursor.moveToNext()) {
                user = new Usuario();

                user.setId(cursor.getLong(0));
                user.setNomeUser(cursor.getString(1));
                user.setLoginUser(cursor.getString(2));
                user.setSenhaUser(cursor.getString(3));
            }

        } catch (Exception e){
            Log.e("ERRO", e.getMessage());
        }finally {
            cursor.close();
        }

        return user;
    }

    public List<Usuario> listaUsuario() {

        List<Usuario> lista = new ArrayList<Usuario>();

        String sql = "SELECT * FROM Usuario order by nome";

        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        try {
            while (cursor.moveToNext()) {
                Usuario usuario = new Usuario();

                usuario.setId(cursor.getLong(0));
                usuario.setNomeUser(cursor.getString(1));
                usuario.setLoginUser(cursor.getString(2));
                usuario.setSenhaUser(cursor.getString(3));

                lista.add(usuario);
            }
        } catch (Exception e) {
            Log.e("ERRO", e.getMessage());
        } finally {
            cursor.close();
        }
        return lista;
    }
}
