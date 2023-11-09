package devandroid.bender.rpg.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.List;

import devandroid.bender.rpg.R;
import devandroid.bender.rpg.controller.HabilidadeController;
import devandroid.bender.rpg.model.Habilidade;

public class HabilidadesActivity extends AppCompatActivity {
    LinearLayout listar_habilidades ;
    Button btnEditar;
    HabilidadeController controller;
    Button btnAdicionar;
    Button btnVoltar;

    TextView txtNome;
    Integer cont ;

    Integer id;

    Integer id2;

    List<Habilidade> dados;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habilidades);


        controller = new HabilidadeController(HabilidadesActivity.this);
        dados = controller.getListaDeDados();
        //txtNome = findViewById(R.id.txtNome);
        btnAdicionar = findViewById(R.id.btnAdicionar);
        btnVoltar = findViewById(R.id.btnVoltar);
        listar_habilidades = findViewById(R.id.listar_habilidades);
        cont = 0;


        while (cont < dados.size() ){
            //adiciona um TextView com os dados do Banco de dados.
            TextView tv_dinamico = new TextView(HabilidadesActivity.this);
            tv_dinamico.setText(String.format("%s ( %s ) \n %s \n\n",
                dados.get(cont).getNome(),
                dados.get(cont).getTempoDeRegarga(),
                dados.get(cont).getDescricao())
            );

            tv_dinamico.setTextSize(25);
            tv_dinamico.setClickable(true);
            id =  cont;
            CriarBotaoEdit(id , tv_dinamico);


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
    private void RedirecionarEdit(double id) {
        id2 = (int)id ;
        //Toast.makeText(ItensActivity.this, "Digite os dados Obrigatorios (Nome)"+id , Toast.LENGTH_LONG).show();

        Intent telaPrincipal = new Intent(HabilidadesActivity.this,EditHabilidadeActivity.class);
        //telaPrincipal.putExtra("id",id);
        telaPrincipal.putExtra("id_numero",id2);
        startActivity(telaPrincipal);
        finish();

    }

}

