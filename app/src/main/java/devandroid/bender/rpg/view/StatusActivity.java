package devandroid.bender.rpg.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import devandroid.bender.rpg.R;
import devandroid.bender.rpg.controller.ArmaController;
import devandroid.bender.rpg.controller.ArmaduraController;
import devandroid.bender.rpg.controller.HabilidadeController;
import devandroid.bender.rpg.controller.StatusController;
import devandroid.bender.rpg.model.Habilidade;
import devandroid.bender.rpg.model.Arma;
import devandroid.bender.rpg.model.Armadura;
import devandroid.bender.rpg.model.Status;


public class StatusActivity extends AppCompatActivity {
    HabilidadeController controllerHabilidade;
    Habilidade habilidade;
    ArmaController controllerArma;
    ArmaduraController controllerArmadura;
    Arma arma;
    StatusController controller;
    TextView editNome;
    TextView editVida;
    TextView editDano;
    TextView editDefesa;
    Button btnEditar;
    Button btnArmas;
    Button btnArmaduras;
    Button btnVoltar;
    Button btnRound;
    Integer contH = 0;
    Integer contA = 0;
    Integer contArmadura = 0;
    Integer quantidadeDano = 0;
    Integer quantidadeDefesa = 0;
    Integer cont = 0;
    List<Habilidade> dadosHabilidade;
    List<Arma> dadosArma;
    List<Armadura> dadosArmadura;
    List<Status> dados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        editNome = findViewById(R.id.editNome);
        editVida = findViewById(R.id.editVida);
        editDano = findViewById(R.id.editDano);
        editDefesa = findViewById(R.id.editDefesa);
        btnEditar = findViewById(R.id.btnEditar);
        btnArmas = findViewById(R.id.btnArmas);
        btnArmaduras = findViewById(R.id.btnArmaduras);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnRound = findViewById(R.id.btnRound);

        controllerHabilidade = new HabilidadeController(StatusActivity.this);
        dadosHabilidade = controllerHabilidade.getListaDeDados();

        controllerArma = new ArmaController(StatusActivity.this);
        dadosArma = controllerArma.getListaDeDados();

        controllerArmadura = new ArmaduraController(StatusActivity.this);
        dadosArmadura = controllerArmadura.getListaDeDados();

        controller = new StatusController(StatusActivity.this);
        dados = controller.getListaDeDados();

        if (dados.size() == 1){
            editNome.setText(dados.get(0).getNome());
            editVida.setText("Vida = " + dados.get(0).getVida());
        }


        while (contA < dadosArma.size() ){
            if(dadosArma.get(contA).getEquipado() == 1){
                quantidadeDano = quantidadeDano + dadosArma.get(contA).getDano();
            }
            contA ++;
        }
        while (contArmadura < dadosArmadura.size() ){
            if(dadosArmadura.get(contArmadura).getEquipado() == 1){
                quantidadeDefesa =  dadosArmadura.get(contArmadura).getDefesa();
            }
            contArmadura ++;
        }
        editDano.setText(String.format("Dano = " + quantidadeDano, 0.00));
        editDefesa.setText(String.format("Defesa = " + quantidadeDefesa, 0.00));
        btnEditar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditarStatus();
            }
        });
        btnArmas.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Armas();
            }
        });
        btnArmaduras.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Armaduras();
            }
        });
        btnRound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FimRound();
            }
        });
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelaPrincipal();
            }
        });
    }
    @Override
    public void onBackPressed() {
        TelaPrincipal();
    }
    private void EditarStatus() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent telaPrincipal = new Intent(StatusActivity.this,EditStatusActivity.class);
                startActivity(telaPrincipal);
                finish();
            }
        },1);
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
    private void Armas() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent telaPrincipal = new Intent(StatusActivity.this,ArmasActivity.class);
                startActivity(telaPrincipal);
                finish();
            }
        },1);
    }
    private void Armaduras() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent telaPrincipal = new Intent(StatusActivity.this,ArmadurasActivity.class);
                startActivity(telaPrincipal);
                finish();
            }
        },1);
    }

      private void FimRound() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                contH = 0;
                while (contH < dadosHabilidade.size() ){
                    //adiciona um TextView com os dados do Banco de dados.
                    if (dadosHabilidade.get(contH).getDisponivelEm() != 0){
                        habilidade = new Habilidade();
                        habilidade.setDisponivelEm(dadosHabilidade.get(contH).getDisponivelEm()-1);
                        Habilidade objAlteracao = dadosHabilidade.get(contH);
                        objAlteracao.setDisponivelEm(habilidade.getDisponivelEm());
                        controllerHabilidade.alterar(objAlteracao);
                    }
                    contH++;
                }
            }
        },1);
          Toast.makeText(StatusActivity.this, "Round Finalizado !", Toast.LENGTH_LONG).show();

    }

}