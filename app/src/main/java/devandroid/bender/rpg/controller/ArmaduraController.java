package devandroid.bender.rpg.controller;

import android.content.ContentValues;
import android.content.Context;

import java.util.List;

import devandroid.bender.rpg.database.RpgDB;
import devandroid.bender.rpg.model.Armadura;

public class ArmaduraController extends RpgDB{
    public ArmaduraController(Context context) {
        super(context);
    }
    public void salvar(Armadura armadura){
        ContentValues dados = new ContentValues();
        dados.put("nome", armadura.getNome());
        dados.put("defesa", armadura.getDefesa());
        dados.put("equipado", armadura.getEquipado());
        dados.put("descricao", armadura.getDescricao());
        salvarObejeto("Armaduras",dados);
    }
    public List<Armadura> getListaDeDados(){
        return listarDadosArmaduras();
    }
    public void alterar(Armadura armadura){
        ContentValues dados = new ContentValues();
        dados.put("id",armadura.getId());
        dados.put("nome", armadura.getNome());
        dados.put("defesa", armadura.getDefesa());
        dados.put("equipado", armadura.getEquipado());
        dados.put("descricao", armadura.getDescricao());
        alterarObejeto("Armaduras",dados);
    }
    public void deletar(int id){
        deletarObejeto("Armaduras",id);
    }
}
