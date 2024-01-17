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
import devandroid.bender.rpg.controller.ItemController;
import devandroid.bender.rpg.controller.StatusController;
import devandroid.bender.rpg.model.Item;
import devandroid.bender.rpg.model.Status;

public class EditStatusActivity extends AppCompatActivity {
    StatusController controller;
    Status status;
    EditText editNome;
    EditText editVida;
    EditText editRaca;
    EditText editHistoria;
    Integer idEdit ;
    Integer idDelete;
    String validaVida;
    Button btnSalvar;
    Button btnDeletar;
    Button btnVoltar;
    List<Status> dados;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_status);

        controller = new StatusController(EditStatusActivity.this);
        dados = controller.getListaDeDados();

        editNome = findViewById(R.id.editNome);
        editVida = findViewById(R.id.editVida);
        editRaca = findViewById(R.id.editRaca);
        editHistoria = findViewById(R.id.editHistoria);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnDeletar = findViewById(R.id.btnDeletar);
        btnVoltar = findViewById(R.id.btnVoltar);

        editNome.setText(dados.get(0).getNome());
        editVida.setText("" + dados.get(0).getVida());
        editRaca.setText(dados.get(0).getRaca());
        editHistoria.setText(dados.get(0).getHistoria());
        idDelete = dados.get(0).getId();
        btnDeletar.setEnabled(false);


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((editVida.getText().toString()).isEmpty() ){
                    Toast.makeText(EditStatusActivity.this, "Preencha o campo vida ?", Toast.LENGTH_LONG).show();
                }
                else{
                status = new Status();
                status.setNome(editNome.getText().toString());
                status.setVida(Integer.parseInt(editVida.getText().toString()));
                status.setRaca(editRaca.getText().toString());
                status.setHistoria(editHistoria.getText().toString());
                Status objAlteracao = dados.get(0);
                objAlteracao.setNome(status.getNome());
                objAlteracao.setVida(status.getVida());
                objAlteracao.setRaca(status.getRaca());
                objAlteracao.setHistoria(status.getHistoria());
                controller.alterar(objAlteracao);
                Toast.makeText(EditStatusActivity.this, "Status editado com sucesso", Toast.LENGTH_LONG).show();
                TelaPrincipal();
                }

            }
        });
       /* btnSalvar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validaQuantidade = (editQuantidade.getText().toString());
                if (!validaQuantidade.isEmpty()){
                    item = new Item();
                    item.setNome(editNome.getText().toString());
                    item.setQuantidade(Double.parseDouble(editQuantidade.getText().toString()));
                    item.setDescricao(editDescricao.getText().toString());
                    if (!item.getNome().isEmpty()){
                            if (!item.getDescricao().isEmpty()){
                                if (idEdit == -1){
                                    controller.salvar(item);
                                    Toast.makeText(EditStatusActivity.this, "Item adicionado com sucesso", Toast.LENGTH_LONG).show();
                                    TelaPrincipal();
                                }
                                else {
                                    Item objAlteracao = dados.get(idEdit);
                                    objAlteracao.setNome(item.getNome());
                                    objAlteracao.setQuantidade(Double.parseDouble(editQuantidade.getText().toString()));
                                    objAlteracao.setDescricao(item.getDescricao());
                                    controller.alterar(objAlteracao);
                                    Toast.makeText(EditStatusActivity.this, "Item editado com sucesso", Toast.LENGTH_LONG).show();
                                    TelaPrincipal();
                                }
                            }
                            else{
                                Toast.makeText(EditStatusActivity.this, "Digite os dados Obrigatorios (Descrição)", Toast.LENGTH_LONG).show();
                            }
                    }
                    else{
                        Toast.makeText(EditStatusActivity.this, "Digite os dados Obrigatorios (Nome)", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(EditStatusActivity.this, "Digite os dados Obrigatorios (Quantidade)", Toast.LENGTH_LONG).show();
                }
            }
        });*/
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
                Toast.makeText(EditStatusActivity.this, "Status Deletado com sucesso", Toast.LENGTH_LONG).show();
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
                Intent telaPrincipal = new Intent(EditStatusActivity.this,StatusActivity.class);
                startActivity(telaPrincipal);
                finish();
            }
        },1);
    }
}