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
import devandroid.bender.rpg.model.Item;

public class EditItemActivity extends AppCompatActivity {


    ItemController controller;

    Item item;
    EditText editNome;

    EditText editQuantidade;
    EditText editDescricao;

    String nomeItem;
    Integer idEdit ;
    Integer idDelete;
    String txEdit;
    String validaQuantidade;
    
    Button btnSalvar;
    Button btnDeletar;
    Button btnVoltar;

    List<Item> dados;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        Intent it = getIntent();
        idEdit = it.getIntExtra("id_numero",0);
        controller = new ItemController(EditItemActivity.this);
        dados = controller.getListaDeDados();


        editNome = findViewById(R.id.editNome);
        editQuantidade = findViewById(R.id.editQuantidade);
        editDescricao = findViewById(R.id.editDescricao);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnDeletar = findViewById(R.id.btnDeletar);
        btnVoltar = findViewById(R.id.btnVoltar);

        if( idEdit != -1) {
            editNome.setText(dados.get(idEdit).getNome());
            editQuantidade.setText("" + dados.get(idEdit).getQuantidade());
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

                validaQuantidade = (editQuantidade.getText().toString());

                if ( validaQuantidade.isEmpty() == false){
                    item = new Item();

                    item.setNome(editNome.getText().toString());
                    item.setQuantidade(Double.parseDouble(editQuantidade.getText().toString()));
                    item.setDescricao(editDescricao.getText().toString());
                    if (item.getNome().isEmpty() == false){
                            if (item.getDescricao().isEmpty() == false){
                                if (idEdit == -1){
                                    controller.salvar(item);
                                    Toast.makeText(EditItemActivity.this, "Item adicionado com sucesso", Toast.LENGTH_LONG).show();
                                    TelaPrincipal();
                                }
                                else {
                                    Item objAlteracao = dados.get(idEdit);
                                    objAlteracao.setNome(item.getNome());
                                    objAlteracao.setQuantidade(Double.parseDouble(editQuantidade.getText().toString()));
                                    objAlteracao.setDescricao(item.getDescricao());
                                    controller.alterar(objAlteracao);
                                    Toast.makeText(EditItemActivity.this, "Item editado com sucesso", Toast.LENGTH_LONG).show();
                                    TelaPrincipal();
                                }
                            }
                            else{
                                Toast.makeText(EditItemActivity.this, "Digite os dados Obrigatorios (Descrição)", Toast.LENGTH_LONG).show();

                            }
                    }
                    else{
                        Toast.makeText(EditItemActivity.this, "Digite os dados Obrigatorios (Nome)", Toast.LENGTH_LONG).show();

                    }
                }

                else {
                    Toast.makeText(EditItemActivity.this, "Digite os dados Obrigatorios (Quantidade)", Toast.LENGTH_LONG).show();
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
                Toast.makeText(EditItemActivity.this, "Item Deletado com sucesso", Toast.LENGTH_LONG).show();
                TelaPrincipal();
            }
        });

    }

    private void TelaPrincipal() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent telaPrincipal = new Intent(EditItemActivity.this,ItensActivity.class);
                startActivity(telaPrincipal);
                finish();
            }
        },1);
    }

}

