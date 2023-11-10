package devandroid.bender.rpg.controller;

import android.content.ContentValues;
import android.content.Context;
import java.util.List;
import devandroid.bender.rpg.database.RpgDB;
import devandroid.bender.rpg.model.Passiva;

public class PassivaController extends RpgDB{
    public PassivaController(Context context) {
        super(context);
    }
    public void salvar(Passiva passiva){
        ContentValues dados = new ContentValues();
        dados.put("nome", passiva.getNome());
        dados.put("descricao", passiva.getDescricao());
        salvarObejeto("Passivas",dados);
    }
    public List<Passiva> getListaDeDados(){
        return listarDadosPassiva();
    }

    public void alterar(Passiva passiva){
        ContentValues dados = new ContentValues();
        dados.put("id",passiva.getId());
        dados.put("nome", passiva.getNome());
        dados.put("descricao", passiva.getDescricao());
        alterarObejeto("Passivas",dados);
    }
    public void deletar(int id){
        deletarObejeto("Passivas",id);
    }
}
