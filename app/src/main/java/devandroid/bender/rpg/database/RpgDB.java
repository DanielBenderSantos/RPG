package devandroid.bender.rpg.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;
import devandroid.bender.rpg.model.Habilidade;
import devandroid.bender.rpg.model.Item;
import devandroid.bender.rpg.model.Passiva;

public class RpgDB extends SQLiteOpenHelper {
    private static final String DB_NAME = "Rpg.db";
    private static final int  DB_VERSION = 1;
    Cursor cursor;
    SQLiteDatabase db;
    public RpgDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db=getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //ITENS
        String sqlTabelaItens = "CREATE TABLE Itens (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "nome TEXT, " +
                "quantidade REAL, "+
                "descricao TEXT)";
        db.execSQL(sqlTabelaItens);

        //HABILIDADES
        String sqlTabelaHabilidades = "CREATE TABLE Habilidades (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "nome TEXT, " +
                "tempoDeRecarga REAL, "+
                "disponivelEm REAL, "+
                "descricao TEXT)";
        db.execSQL(sqlTabelaHabilidades);

        //PASSIVAS
        String sqlTabelaPassivas = "CREATE TABLE Passivas (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "nome TEXT, " +
                "descricao TEXT)";
        db.execSQL(sqlTabelaPassivas);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { }
    public void salvarObejeto(String tabela, ContentValues dados ){
        db.insert(tabela, null,dados);
    }
    public void alterarObejeto(String tabela, ContentValues dados ){
        db.insert(tabela, null,dados);
        int id = dados.getAsInteger("id");
        db.update(tabela,dados,"id=?", new String[]{Integer.toString(id)});
    }
    public void deletarObejeto(String tabela, int id ){
        db.delete(tabela,"id=?", new String[]{Integer.toString(id)});
    }

    //LISTAR ITENS
    public List<Item> listarDadosItem(){
        List<Item> lista = new ArrayList<>();
        Item registro;
        String querySQL = "SELECT * FROM Itens";
        cursor = db.rawQuery(querySQL,null);
        if(cursor.moveToFirst()){
            do{
                registro = new Item();
                registro.setId(cursor.getInt(0));
                registro.setNome(cursor.getString(1));
                registro.setQuantidade(cursor.getDouble(2));
                registro.setDescricao(cursor.getString(3));
                lista.add(registro);
            }while(cursor.moveToNext());
        }
        return lista;
    }

    //LISTAR HABILIDADES
    public List<Habilidade> listarDadosHabilidades(){
        List<Habilidade> lista = new ArrayList<>();
        Habilidade registro;
        String querySQL = "SELECT * FROM Habilidades";
        cursor = db.rawQuery(querySQL,null);
        if(cursor.moveToFirst()){
            do{
                registro = new Habilidade();
                registro.setId(cursor.getInt(0));
                registro.setNome(cursor.getString(1));
                registro.setTempoDeRegarga(cursor.getDouble(2));
                registro.setDisponivelEm(cursor.getDouble(3));
                registro.setDescricao(cursor.getString(4));
                lista.add(registro);
            }while(cursor.moveToNext());
        }
        return lista;
    }

    //LISTAR PASSIVAS
    public List<Passiva> listarDadosPassiva(){
        List<Passiva> lista = new ArrayList<>();
        Passiva registro;
        String querySQL = "SELECT * FROM Passivas";
        cursor = db.rawQuery(querySQL,null);
        if(cursor.moveToFirst()){
            do{
                registro = new Passiva();
                registro.setId(cursor.getInt(0));
                registro.setNome(cursor.getString(1));
                registro.setDescricao(cursor.getString(2));
                lista.add(registro);
            }while(cursor.moveToNext());
        }
        return lista;
    }
}
