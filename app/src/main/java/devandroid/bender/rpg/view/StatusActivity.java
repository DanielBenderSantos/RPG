package devandroid.bender.rpg.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import devandroid.bender.rpg.R;
//import devandroid.bender.rpg.controller.ItemController;
//import devandroid.bender.rpg.model.Item;
import devandroid.bender.rpg.controller.HabilidadeController;
import devandroid.bender.rpg.model.Habilidade;
import devandroid.bender.rpg.model.Item;
//import devandroid.bender.rpg.controller.PassivaController;
//import devandroid.bender.rpg.model.Passiva;

public class StatusActivity extends AppCompatActivity {
    //ItemController controllerItem;
    HabilidadeController controllerHabilidade;

    Habilidade habilidade;
    //PassivaController controllerPassiva;

    Button btnVoltar;
    Button btnRound;
    Integer cont = 0;
    //List<Item> dadosItem;
    List<Habilidade> dadosHabilidade;
    //List<Passiva> dadosPassiva;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnRound = findViewById(R.id.btnRound);

        //controllerItem = new ItemController(StatusActivity.this);
        //dadosItem = controllerItem.getListaDeDados();
        controllerHabilidade = new HabilidadeController(StatusActivity.this);
        dadosHabilidade = controllerHabilidade.getListaDeDados();
        //controllerPassiva = new PassivaController(StatusActivity.this);
        //dadosPassiva = controllerPassiva.getListaDeDados();

        btnRound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FimRoud();
            }
        });
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelaPrincipal();
            }
        });
    }
    private void TelaPrincipal() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent telaPrincipal = new Intent(StatusActivity.this,MainActivity.class);
                startActivity(telaPrincipal);
                finish();
            }
        },1);
    }

      private void FimRoud() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                cont = 0;
                while (cont < dadosHabilidade.size() ){
                    //adiciona um TextView com os dados do Banco de dados.
                    if (dadosHabilidade.get(cont).getDisponivelEm() != 0){
                        habilidade = new Habilidade();
                        habilidade.setDisponivelEm(dadosHabilidade.get(cont).getDisponivelEm()-1);
                        Habilidade objAlteracao = dadosHabilidade.get(cont);
                        objAlteracao.setDisponivelEm(habilidade.getDisponivelEm());
                        controllerHabilidade.alterar(objAlteracao);
                    }
                    cont ++;
                }
            }
        },1);
    }

}