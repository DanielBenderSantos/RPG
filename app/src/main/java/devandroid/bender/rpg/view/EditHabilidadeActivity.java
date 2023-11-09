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
import devandroid.bender.rpg.controller.HabilidadeController;
import devandroid.bender.rpg.controller.ItemController;
import devandroid.bender.rpg.model.Habilidade;
import devandroid.bender.rpg.model.Item;

public class EditHabilidadeActivity extends AppCompatActivity {


    HabilidadeController controller;
    Habilidade habilidade;
    EditText editNome;
    EditText editTempoDeRecarga;
    EditText editDescricao;
    String nomeItem;
    Integer idEdit ;
    Integer idDelete;
    String txEdit;
    String validaQuantidade;
    Button btnSalvar;
    Button btnDeletar;
    Button btnVoltar;
    List<Habilidade> dados;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_habilidade);

        Intent it = getIntent();
        idEdit = it.getIntExtra("id_numero",0);
        controller = new HabilidadeController(EditHabilidadeActivity.this);
        dados = controller.getListaDeDados();


        editNome = findViewById(R.id.editNome);
        editTempoDeRecarga = findViewById(R.id.editTempoDeRecarga);
        editDescricao = findViewById(R.id.editDescricao);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnDeletar = findViewById(R.id.btnDeletar);
        btnVoltar = findViewById(R.id.btnVoltar);

        if( idEdit != -1) {
            editNome.setText(dados.get(idEdit).getNome());
            editTempoDeRecarga.setText("" + dados.get(idEdit).getTempoDeRegarga());
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

                validaQuantidade = (editTempoDeRecarga.getText().toString());

                if ( validaQuantidade.isEmpty() == false){
                    habilidade = new Habilidade();

                    habilidade.setNome(editNome.getText().toString());
                    habilidade.setTempoDeRegarga(Double.parseDouble(editTempoDeRecarga.getText().toString()));
                    habilidade.setDescricao(editDescricao.getText().toString());
                    if (habilidade.getNome().isEmpty() == false){
                            if (habilidade.getDescricao().isEmpty() == false){
                                if (idEdit == -1){
                                    controller.salvar(habilidade);
                                    Toast.makeText(EditHabilidadeActivity.this, "Habilidade adicionada com sucesso", Toast.LENGTH_LONG).show();
                                    TelaPrincipal();
                                }
                                else {
                                    Habilidade objAlteracao = dados.get(idEdit);
                                    objAlteracao.setNome(habilidade.getNome());
                                    objAlteracao.setTempoDeRegarga(Double.parseDouble(editTempoDeRecarga.getText().toString()));
                                    objAlteracao.setDescricao(habilidade.getDescricao());
                                    controller.alterar(objAlteracao);
                                    Toast.makeText(EditHabilidadeActivity.this, "Habilidade editada com sucesso", Toast.LENGTH_LONG).show();
                                    TelaPrincipal();
                                }
                            }
                            else{
                                Toast.makeText(EditHabilidadeActivity.this, "Digite os dados Obrigatorios (Descrição)", Toast.LENGTH_LONG).show();

                            }
                    }
                    else{
                        Toast.makeText(EditHabilidadeActivity.this, "Digite os dados Obrigatorios (Nome)", Toast.LENGTH_LONG).show();

                    }
                }

                else {
                    Toast.makeText(EditHabilidadeActivity.this, "Digite os dados Obrigatorios (Tempo De Recarga)", Toast.LENGTH_LONG).show();
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
                Toast.makeText(EditHabilidadeActivity.this, "Item Deletado com sucesso", Toast.LENGTH_LONG).show();
                TelaPrincipal();
            }
        });

    }

    private void TelaPrincipal() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent telaPrincipal = new Intent(EditHabilidadeActivity.this, HabilidadesActivity.class);
                startActivity(telaPrincipal);
                finish();
            }
        },1);
    }

}

