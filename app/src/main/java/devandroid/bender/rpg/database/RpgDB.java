package devandroid.bender.rpg.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

import devandroid.bender.rpg.model.Arma;
import devandroid.bender.rpg.model.Habilidade;
import devandroid.bender.rpg.model.Item;
import devandroid.bender.rpg.model.Passiva;
import devandroid.bender.rpg.model.Status;

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

        //Status
        String sqlTabelaStatus = "CREATE TABLE Status (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "nome TEXT, " +
                "vida REAL, "+
                "raca TEXT, "+
                "historia TEXT)";
        db.execSQL(sqlTabelaStatus);

        //StatusPadrao
        String sqlTabelaStatusPadrao = "INSERT INTO Status (id, nome , vida , raca , historia ) values" +
                "(1,'Nome',100,'Raça','Historia')";
        db.execSQL(sqlTabelaStatusPadrao);

        //Armas
        String sqlTabelaArmas = "CREATE TABLE Armas (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "nome TEXT, " +
                "dano REAL, "+
                "quantidadeDeMaos REAL, "+
                "equipado REAL, "+
                "descricao TEXT)";
        db.execSQL(sqlTabelaArmas);
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

    //LISTAR Status
    public List<Status> listarDadosStatus(){
        List<Status> lista = new ArrayList<>();
        Status registro;
        String querySQL = "SELECT * FROM Status";
        cursor = db.rawQuery(querySQL,null);
        if(cursor.moveToFirst()){
            do{
                registro = new Status();
                registro.setId(cursor.getInt(0));
                registro.setNome(cursor.getString(1));
                registro.setVida(cursor.getInt(2));
                registro.setRaca(cursor.getString(3));
                registro.setHistoria(cursor.getString(4));
                lista.add(registro);
            }while(cursor.moveToNext());
        }
        return lista;
    }
    //LISTAR Armas
    public List<Arma> listarDadosArmas(){
        List<Arma> lista = new ArrayList<>();
        Arma registro;
        String querySQL = "SELECT * FROM Armas";
        cursor = db.rawQuery(querySQL,null);
        if(cursor.moveToFirst()){
            do{
                registro = new Arma();
                registro.setId(cursor.getInt(0));
                registro.setNome(cursor.getString(1));
                registro.setDano(cursor.getInt(2));
                registro.setQuantidadeDeMaos(cursor.getInt(3));
                registro.setEquipado(cursor.getInt(4));
                registro.setDescricao(cursor.getString(5));
                lista.add(registro);
            }while(cursor.moveToNext());
        }
        return lista;
    }
}
