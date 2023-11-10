package devandroid.bender.rpg.controller;

import android.content.ContentValues;
import android.content.Context;
import java.util.List;
import devandroid.bender.rpg.database.RpgDB;
import devandroid.bender.rpg.model.Item;

public class ItemController extends RpgDB{
    public ItemController(Context context) {
        super(context);
    }
    public void salvar(Item item){
        ContentValues dados = new ContentValues();
        dados.put("nome", item.getNome());
        dados.put("quantidade", item.getQuantidade());
        dados.put("descricao", item.getDescricao());
        salvarObejeto("Itens",dados);
    }
    public List<Item> getListaDeDados(){
        return listarDadosItem();
    }
    public void alterar(Item item){
        ContentValues dados = new ContentValues();
        dados.put("id",item.getId());
        dados.put("nome", item.getNome());
        dados.put("quantidade", item.getQuantidade());
        dados.put("descricao", item.getDescricao());
        alterarObejeto("Itens",dados);
    }
    public void deletar(int id){
        deletarObejeto("Itens",id);
    }
}
