package devandroid.bender.rpg.controller;

import android.content.ContentValues;
import android.content.Context;

import java.util.List;

import devandroid.bender.rpg.database.RpgDB;
import devandroid.bender.rpg.model.Arma;

public class ArmaController extends RpgDB{
    public ArmaController(Context context) {
        super(context);
    }
    public void salvar(Arma arma){
        ContentValues dados = new ContentValues();
        dados.put("nome", arma.getNome());
        dados.put("dano", arma.getDano());
        dados.put("quantidadeDeMaos", arma.getQuantidadeDeMaos());
        dados.put("equipado", arma.getEquipado());
        dados.put("descricao", arma.getDescricao());
        salvarObejeto("Armas",dados);
    }
    public List<Arma> getListaDeDados(){
        return listarDadosArmas();
    }
    public void alterar(Arma arma){
        ContentValues dados = new ContentValues();
        dados.put("id",arma.getId());
        dados.put("nome", arma.getNome());
        dados.put("dano", arma.getDano());
        dados.put("quantidadeDeMaos", arma.getQuantidadeDeMaos());
        dados.put("equipado", arma.getEquipado());
        dados.put("descricao", arma.getDescricao());
        alterarObejeto("Armas",dados);
    }
    public void deletar(int id){
        deletarObejeto("Armas",id);
    }
}
