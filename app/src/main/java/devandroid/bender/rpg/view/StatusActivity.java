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
import devandroid.bender.rpg.controller.ArmaController;
import devandroid.bender.rpg.controller.HabilidadeController;
import devandroid.bender.rpg.controller.StatusController;
import devandroid.bender.rpg.model.Habilidade;
import devandroid.bender.rpg.model.Arma;
import devandroid.bender.rpg.model.Status;


public class StatusActivity extends AppCompatActivity {
    HabilidadeController controllerHabilidade;
    Habilidade habilidade;
    ArmaController controllerArma;
    Arma arma;
    StatusController controller;
    TextView editNome;
    TextView editVida;
    TextView editDano;
    TextView editDefesa;
    Button btnEditar;
    Button btnArmas;
    Button btnVoltar;
    Button btnRound;
    Integer contH = 0;
    Integer contA = 0;
    Integer quantidadeDano = 0;
    Integer cont = 0;
    List<Habilidade> dadosHabilidade;
    List<Arma> dadosArma;
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
        btnVoltar = findViewById(R.id.btnVoltar);
        btnRound = findViewById(R.id.btnRound);

        controllerHabilidade = new HabilidadeController(StatusActivity.this);
        dadosHabilidade = controllerHabilidade.getListaDeDados();

        controllerArma = new ArmaController(StatusActivity.this);
        dadosArma = controllerArma.getListaDeDados();

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
        editDano.setText(String.format("Dano = " + quantidadeDano, 0.00));
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

      private void FimRoud() {
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
    }

}