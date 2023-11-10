package devandroid.bender.rpg.controller;

import android.content.ContentValues;
import android.content.Context;
import java.util.List;
import devandroid.bender.rpg.database.RpgDB;
import devandroid.bender.rpg.model.Habilidade;

public class HabilidadeController extends RpgDB {
    public HabilidadeController(Context context) {
        super(context);
    }
    public void salvar(Habilidade habilidade){
        ContentValues dados = new ContentValues();
        dados.put("nome", habilidade.getNome());
        dados.put("tempoDeRecarga", habilidade.getTempoDeRegarga());
        dados.put("disponivelEm", 0);
        dados.put("descricao", habilidade.getDescricao());
        salvarObejeto("Habilidades",dados);
    }
    public List<Habilidade> getListaDeDados(){
        return listarDadosHabilidades();
    }
    public void alterar(Habilidade Habilidade){
        ContentValues dados = new ContentValues();
        dados.put("id",Habilidade.getId());
        dados.put("nome", Habilidade.getNome());
        dados.put("tempoDeRecarga", Habilidade.getTempoDeRegarga());
        dados.put("disponivelEm", Habilidade.getDisponivelEm());
        dados.put("descricao", Habilidade.getDescricao());
        alterarObejeto("Habilidades",dados);
    }
    public void deletar(int id){
        deletarObejeto("Habilidades",id);
    }
}
