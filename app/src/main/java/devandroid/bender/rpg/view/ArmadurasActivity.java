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
import devandroid.bender.rpg.controller.ArmaduraController;
import devandroid.bender.rpg.model.Arma;
import devandroid.bender.rpg.model.Armadura;

public class ArmadurasActivity extends AppCompatActivity {
    LinearLayout listar_armaduras;

    ArmaduraController controller;
    Button btnAdicionar;
    Button btnVoltar;
    Integer cont = 0 ;
    Integer id;

    String equipado;
    List<Armadura> dados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armaduras);
        controller = new ArmaduraController(ArmadurasActivity.this);
        dados = controller.getListaDeDados();
        btnAdicionar = findViewById(R.id.btnAdicionar);
        btnVoltar = findViewById(R.id.btnVoltar);
        listar_armaduras = findViewById(R.id.listar_armaduras);
        while (cont < dados.size() ){
            TextView tv_dinamico = new TextView(ArmadurasActivity.this);
            if (dados.get(cont).getEquipado() ==  0){
                equipado = "Não" ;
            }
            else {
                equipado = "Sim" ;
            }
            tv_dinamico.setText(String.format(" Nome: %s \n Defesa: %s \n Equipado: %s  \n Descrição: %s \n\n",
                dados.get(cont).getNome(),
                dados.get(cont).getDefesa(),
                equipado,
                dados.get(cont).getDescricao())
            );
            tv_dinamico.setTextSize(25);
            tv_dinamico.setClickable(true);
            CriarBotaoEdit(cont , tv_dinamico);
            tv_dinamico.setPadding(15,10,0,0);
            listar_armaduras.addView(tv_dinamico);
            listar_armaduras.setOrientation(LinearLayout.VERTICAL);
            cont ++;
        }
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditArmadura();
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
    private void TelaPrincipal() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent telaPrincipal = new Intent(ArmadurasActivity.this,StatusActivity.class);
                startActivity(telaPrincipal);
                finish();
            }
        },1);
    }
    private void EditArmadura() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent telaPrincipal = new Intent(ArmadurasActivity.this,EditArmaduraActivity.class);
                telaPrincipal.putExtra("id_numero", -1);
                startActivity(telaPrincipal);

            }
        },1);
    }
    private void CriarBotaoEdit(double id, View tv_dinamico) {
        tv_dinamico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View tv_dinamico) {
                RedirecionarEdit(id);
            }
        });
    }
    private void RedirecionarEdit(double idEdit) {
        id = (int)idEdit ;
        Intent telaPrincipal = new Intent(ArmadurasActivity.this,EditArmaduraActivity.class);
        //telaPrincipal.putExtra("id",id);
        telaPrincipal.putExtra("id_numero",id);
        startActivity(telaPrincipal);
        finish();
    }
}