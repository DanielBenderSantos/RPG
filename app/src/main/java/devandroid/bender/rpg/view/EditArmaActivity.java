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
import devandroid.bender.rpg.controller.ArmaController;
import devandroid.bender.rpg.model.Arma;

public class EditArmaActivity extends AppCompatActivity {
    ArmaController controller;
    Arma arma;
    EditText editNome;
    EditText editDano;
    EditText editSlot;
    EditText editDescricao;
    Integer idEdit ;
    Integer idDelete;
    Integer slots =0;
    Integer cont =0;
    String validaQuantidade;
    Button btnSalvar;
    Button btnEquiparDesequipar;
    Button btnDeletar;
    Button btnVoltar;
    List<Arma> dados;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_arma);
        // I recebe os dados passado pela tela anterior
        Intent it = getIntent();
        idEdit = it.getIntExtra("id_numero",0);
        // F recebe os dados passado pela tela anterior

        controller = new ArmaController(EditArmaActivity.this);
        dados = controller.getListaDeDados();
        editNome = findViewById(R.id.editNome);
        editDano = findViewById(R.id.editDano);
        editSlot = findViewById(R.id.editSlot);
        editDescricao = findViewById(R.id.editDescricao);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnEquiparDesequipar = findViewById(R.id.btnEquiparDesequipar);
        btnDeletar = findViewById(R.id.btnDeletar);
        btnVoltar = findViewById(R.id.btnVoltar);
        if( idEdit != -1) {
            editNome.setText(dados.get(idEdit).getNome());
            editDano.setText("" + dados.get(idEdit).getDano());
            editSlot.setText("" + dados.get(idEdit).getQuantidadeDeMaos());
            editDescricao.setText(dados.get(idEdit).getDescricao());
            btnDeletar.setEnabled(true);
            btnEquiparDesequipar.setEnabled(true);
            idDelete = dados.get(idEdit).getId();
        }
        else {
            btnDeletar.setEnabled(false);
            btnEquiparDesequipar.setEnabled(false);
        }
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validaQuantidade = (editSlot.getText().toString());
                if(editNome.getText().toString().isEmpty() || editDano.getText().toString().isEmpty()
                        || editSlot.getText().toString().isEmpty() || editDescricao.getText().toString().isEmpty()){
                    Toast.makeText(EditArmaActivity.this, "Preencha todos os campos ", Toast.LENGTH_LONG).show();
                }
                else{
                    arma = new Arma();
                    arma.setNome(editNome.getText().toString());
                    arma.setDano(Integer.parseInt(editDano.getText().toString()));
                    arma.setQuantidadeDeMaos(Integer.parseInt(editSlot.getText().toString()));
                    arma.setEquipado(0);
                    arma.setDescricao(editDescricao.getText().toString());

                    if (idEdit == -1){
                        controller.salvar(arma);
                        Toast.makeText(EditArmaActivity.this, "Arma adicionada com sucesso", Toast.LENGTH_LONG).show();
                        TelaPrincipal();
                    }
                    else {
                        Arma objAlteracao = dados.get(idEdit);
                        objAlteracao.setNome(arma.getNome());
                        objAlteracao.setDano(Integer.parseInt(editDano.getText().toString()));
                        objAlteracao.setQuantidadeDeMaos(Integer.parseInt(editSlot.getText().toString()));
                        objAlteracao.setDescricao(arma.getDescricao());
                        controller.alterar(objAlteracao);
                        Toast.makeText(EditArmaActivity.this, "Arma editada com sucesso", Toast.LENGTH_LONG).show();
                        TelaPrincipal();
                    }
                }
            }
        });
        btnEquiparDesequipar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerificaEquipamento(idEdit);
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
                Toast.makeText(EditArmaActivity.this, "Arma Deletada com sucesso", Toast.LENGTH_LONG).show();
                TelaPrincipal();
            }
        });
    }
    private void TelaPrincipal() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent telaPrincipal = new Intent(EditArmaActivity.this,ArmasActivity.class);
                startActivity(telaPrincipal);
                finish();
            }
        },1);
    }
    private void VerificaEquipamento(int idEdit){
        if (dados.get(idEdit).getEquipado() == 1){
            arma = new Arma();
            arma.setEquipado(0);
            Arma objAlteracao = dados.get(idEdit);
            objAlteracao.setEquipado(arma.getEquipado());
            controller.alterar(objAlteracao);
            Toast.makeText(EditArmaActivity.this, "Arma Desequipada  com sucesso", Toast.LENGTH_SHORT).show();
            TelaPrincipal();

        }
        else{
           while (cont < dados.size() ){
                if(dados.get(cont).getEquipado() == 1){
                    slots = slots + dados.get(cont).getQuantidadeDeMaos() ;
                }
                cont ++;
            }

                if (slots + dados.get(idEdit).getQuantidadeDeMaos() <= 2){
                    arma = new Arma();
                    arma.setEquipado(1);
                    Arma objAlteracao = dados.get(idEdit);;
                    objAlteracao.setEquipado(arma.getEquipado());
                    controller.alterar(objAlteracao);
                    Toast.makeText(EditArmaActivity.this, "Arma Equipada  com sucesso", Toast.LENGTH_SHORT).show();
                    TelaPrincipal();

                }
                else{
                    Toast.makeText(EditArmaActivity.this, "Slot Insuficiente desequipe a outra arma", Toast.LENGTH_LONG).show();

                }
        }
    }
}
