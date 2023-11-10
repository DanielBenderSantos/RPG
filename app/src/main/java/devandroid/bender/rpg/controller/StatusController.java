package devandroid.bender.rpg.controller;

import android.content.ContentValues;
import android.content.Context;
import java.util.List;
import devandroid.bender.rpg.database.RpgDB;
import devandroid.bender.rpg.model.Status;

public class StatusController extends RpgDB{
    public StatusController(Context context) {
        super(context);
    }
    public void salvar(Status status){
        ContentValues dados = new ContentValues();
        dados.put("nome", status.getNome());
        dados.put("vida", status.getVida());
        dados.put("raca", status.getRaca());
        dados.put("historia", status.getHistoria());
        salvarObejeto("Status",dados);
    }
    public List<Status> getListaDeDados(){
        return listarDadosStatus();
    }
    public void alterar(Status status){
        ContentValues dados = new ContentValues();
        dados.put("id",status.getId());
        dados.put("nome", status.getNome());
        dados.put("vida", status.getVida());
        dados.put("raca", status.getRaca());
        dados.put("historia", status.getHistoria());
        alterarObejeto("Status",dados);
    }
    public void deletar(int id){
        deletarObejeto("Status",id);
    }
}
