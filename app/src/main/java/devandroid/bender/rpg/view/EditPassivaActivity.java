package devandroid.bender.rpg.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import devandroid.bender.rpg.R;
import devandroid.bender.rpg.controller.PassivaController;
import devandroid.bender.rpg.model.Passiva;

public class EditPassivaActivity extends AppCompatActivity {


    PassivaController controller;

    Passiva passiva;
    EditText editNome;

    EditText editQuantidade;
    EditText editDescricao;

    String nomeItem;
    Integer idEdit ;
    Integer idDelete;
    String txEdit;

    Button btnSalvar;
    Button btnDeletar;
    Button btnVoltar;

    List<Passiva> dados;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_passiva);

        Intent it = getIntent();
        idEdit = it.getIntExtra("id_numero",0);
        controller = new PassivaController(EditPassivaActivity.this);
        dados = controller.getListaDeDados();


        editNome = findViewById(R.id.editNome);
        editDescricao = findViewById(R.id.editDescricao);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnDeletar = findViewById(R.id.btnDeletar);
        btnVoltar = findViewById(R.id.btnVoltar);

        if( idEdit != -1) {
            editNome.setText(dados.get(idEdit).getNome());
            editDescricao.setText(dados.get(idEdit).getDescricao());
            btnDeletar.setEnabled(true);
            idDelete = dados.get(idEdit).getId();
        }
        else {
            btnDeletar.setEnabled(false);
        }


//Deletar manual
        /*
        controller.deletar(2);
        */

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO: Desabilitar o botão salvar


                passiva = new Passiva();
                passiva.setNome(editNome.getText().toString());
                passiva.setDescricao(editDescricao.getText().toString());
                    if (passiva.getNome().isEmpty() == false){
                            if (passiva.getDescricao().isEmpty() == false){
                                if (idEdit == -1){
                                    controller.salvar(passiva);
                                    Toast.makeText(EditPassivaActivity.this, "Item adicionado com sucesso", Toast.LENGTH_LONG).show();
                                    TelaPrincipal();
                                }
                                else {
                                    Passiva objAlteracao = dados.get(idEdit);
                                    objAlteracao.setNome(passiva.getNome());
                                    objAlteracao.setDescricao(passiva.getDescricao());
                                    controller.alterar(objAlteracao);
                                    Toast.makeText(EditPassivaActivity.this, "Item editado com sucesso", Toast.LENGTH_LONG).show();
                                    TelaPrincipal();
                                }
                            }
                            else{
                                Toast.makeText(EditPassivaActivity.this, "Digite os dados Obrigatorios (Descrição)", Toast.LENGTH_LONG).show();

                            }
                    }
                    else{
                        Toast.makeText(EditPassivaActivity.this, "Digite os dados Obrigatorios (Nome)", Toast.LENGTH_LONG).show();

                    }
                }



        });


        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelaPrincipal();
            }
        });

        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.deletar(idDelete);
                Toast.makeText(EditPassivaActivity.this, "Item Deletado com sucesso", Toast.LENGTH_LONG).show();
                TelaPrincipal();
            }
        });

    }

    private void TelaPrincipal() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent telaPrincipal = new Intent(EditPassivaActivity.this,PassivasActivity.class);
                startActivity(telaPrincipal);
                finish();
            }
        },1);
    }

}

