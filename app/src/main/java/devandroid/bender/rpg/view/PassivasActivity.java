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
import devandroid.bender.rpg.controller.PassivaController;
import devandroid.bender.rpg.model.Passiva;

public class PassivasActivity extends AppCompatActivity {
    LinearLayout listar_itens ;
    Button btnEditar;
    PassivaController controller;
    Button btnAdicionar;
    Button btnVoltar;

    TextView txtNome;
    Integer cont ;

    Integer id;

    Integer id2;

    List<Passiva> dados;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passivas);





        controller = new PassivaController(PassivasActivity.this);
        dados = controller.getListaDeDados();
        //txtNome = findViewById(R.id.txtNome);
        btnAdicionar = findViewById(R.id.btnAdicionar);
        btnVoltar = findViewById(R.id.btnVoltar);
        listar_itens = findViewById(R.id.listar_itens);
        cont = 0;



        while (cont < dados.size() ){
            //adiciona um TextView com os dados do Banco de dados.
            TextView tv_dinamico = new TextView(PassivasActivity.this);
            tv_dinamico.setText(String.format("%s \n %s \n\n",
                dados.get(cont).getNome(),
                dados.get(cont).getDescricao())
            );

            tv_dinamico.setTextSize(25);
            tv_dinamico.setClickable(true);
            id =  cont;
            CriarBotaoEdit(id , tv_dinamico);


            tv_dinamico.setPadding(15,10,0,0);
            listar_itens.addView(tv_dinamico);
            listar_itens.setOrientation(LinearLayout.VERTICAL);



            //txtNome.setText(dados.get(cont).getNome());



            cont ++;

        }



        //Toast.makeText(ItensActivity.this, "Dado numero " +cont, Toast.LENGTH_SHORT).show();




        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditPassiva();
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
                Intent telaPrincipal = new Intent(PassivasActivity.this,MainActivity.class);
                startActivity(telaPrincipal);
                finish();
            }
        },1);
    }
    private void EditPassiva() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent telaPrincipal = new Intent(PassivasActivity.this,EditPassivaActivity.class);
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

        Intent telaPrincipal = new Intent(PassivasActivity.this,EditPassivaActivity.class);
        //telaPrincipal.putExtra("id",id);
        telaPrincipal.putExtra("id_numero",id2);
        startActivity(telaPrincipal);
        finish();

    }

}

