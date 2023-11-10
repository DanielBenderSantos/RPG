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
import devandroid.bender.rpg.controller.HabilidadeController;
import devandroid.bender.rpg.model.Habilidade;
public class HabilidadesActivity extends AppCompatActivity {
    LinearLayout listar_habilidades ;
    HabilidadeController controller;
    Button btnAdicionar;
    Button btnVoltar;
    Integer cont = 0;
    Integer id;
    List<Habilidade> dados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habilidades);
        controller = new HabilidadeController(HabilidadesActivity.this);
        dados = controller.getListaDeDados();
        btnAdicionar = findViewById(R.id.btnAdicionar);
        btnVoltar = findViewById(R.id.btnVoltar);
        listar_habilidades = findViewById(R.id.listar_habilidades);
        while (cont < dados.size() ){
            //adiciona um TextView com os dados do Banco de dados.
            TextView tv_dinamico = new TextView(HabilidadesActivity.this);
            tv_dinamico.setText(String.format("%s ( %s / %s ) \n %s \n\n",
                dados.get(cont).getNome(),
                dados.get(cont).getTempoDeRegarga(),
                dados.get(cont).getDisponivelEm(),
                dados.get(cont).getDescricao())
            );
            tv_dinamico.setTextSize(25);
            tv_dinamico.setClickable(true);
            CriarBotaoEdit(cont , tv_dinamico);
            tv_dinamico.setPadding(15,10,0,0);
            listar_habilidades.addView(tv_dinamico);
            listar_habilidades.setOrientation(LinearLayout.VERTICAL);
            cont ++;
        }
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditItem();
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
                Intent telaPrincipal = new Intent(HabilidadesActivity.this,MainActivity.class);
                startActivity(telaPrincipal);
                finish();
            }
        },1);
    }
    private void EditItem() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent telaPrincipal = new Intent(HabilidadesActivity.this,EditHabilidadeActivity.class);
                telaPrincipal.putExtra("id_numero", -1);
                startActivity(telaPrincipal);
                finish();
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
        Intent telaPrincipal = new Intent(HabilidadesActivity.this,EditHabilidadeActivity.class);
        telaPrincipal.putExtra("id_numero",id);
        startActivity(telaPrincipal);
        finish();
    }
}