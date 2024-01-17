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
import devandroid.bender.rpg.controller.ArmaduraController;
import devandroid.bender.rpg.model.Armadura;

public class EditArmaduraActivity extends AppCompatActivity {
    ArmaduraController controller;
    Armadura armadura;
    EditText editNome;
    EditText editDefesa;
    EditText editDescricao;
    Integer idEdit ;
    Integer idDelete;
    Integer verificaEquipado = 0 ;
    Integer cont =0;
    Button btnSalvar;
    Button btnEquiparDesequipar;
    Button btnDeletar;
    Button btnVoltar;
    List<Armadura> dados;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_armadura);
        // I recebe os dados passado pela tela anterior
        Intent it = getIntent();
        idEdit = it.getIntExtra("id_numero",0);
        // F recebe os dados passado pela tela anterior

        controller = new ArmaduraController(EditArmaduraActivity.this);
        dados = controller.getListaDeDados();
        editNome = findViewById(R.id.editNome);
        editDefesa = findViewById(R.id.editDefesa);
        editDescricao = findViewById(R.id.editDescricao);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnEquiparDesequipar = findViewById(R.id.btnEquiparDesequipar);
        btnDeletar = findViewById(R.id.btnDeletar);
        btnVoltar = findViewById(R.id.btnVoltar);
        if( idEdit != -1) {
            editNome.setText(dados.get(idEdit).getNome());
            editDefesa.setText("" + dados.get(idEdit).getDefesa());
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
                if(editNome.getText().toString().isEmpty() || editDefesa.getText().toString().isEmpty()
                         || editDescricao.getText().toString().isEmpty()){
                    Toast.makeText(EditArmaduraActivity.this, "Preencha todos os campos ", Toast.LENGTH_LONG).show();
                }
                else{
                    armadura = new Armadura();
                    armadura.setNome(editNome.getText().toString());
                    armadura.setDefesa(Integer.parseInt(editDefesa.getText().toString()));
                    armadura.setEquipado(0);
                    armadura.setDescricao(editDescricao.getText().toString());

                    if (idEdit == -1){
                        controller.salvar(armadura);
                        Toast.makeText(EditArmaduraActivity.this, "Armadura adicionada com sucesso", Toast.LENGTH_LONG).show();
                        TelaPrincipal();
                    }
                    else {
                        Armadura objAlteracao = dados.get(idEdit);
                        objAlteracao.setNome(armadura.getNome());
                        objAlteracao.setDefesa(Integer.parseInt(editDefesa.getText().toString()));
                        objAlteracao.setDescricao(armadura.getDescricao());
                        controller.alterar(objAlteracao);
                        Toast.makeText(EditArmaduraActivity.this, "Armadura editada com sucesso", Toast.LENGTH_LONG).show();
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
                Toast.makeText(EditArmaduraActivity.this, "Armadura Deletada com sucesso", Toast.LENGTH_LONG).show();
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
                Intent telaPrincipal = new Intent(EditArmaduraActivity.this,ArmadurasActivity.class);
                startActivity(telaPrincipal);
                finish();
            }
        },1);
    }
    private void VerificaEquipamento(int idEdit){
        if (dados.get(idEdit).getEquipado() == 1){
            armadura = new Armadura();
            armadura.setEquipado(0);
            Armadura objAlteracao = dados.get(idEdit);
            objAlteracao.setEquipado(armadura.getEquipado());
            controller.alterar(objAlteracao);
            Toast.makeText(EditArmaduraActivity.this, "Armadura Desequipada  com sucesso", Toast.LENGTH_SHORT).show();
            TelaPrincipal();

        }
        else{
           while (cont < dados.size() ) {
               if (dados.get(cont).getEquipado() == 1) {
                   verificaEquipado = 1;
               }
               cont++;
           }
               if (verificaEquipado == 0) {
                   armadura = new Armadura();
                   armadura.setEquipado(1);
                   Armadura objAlteracao = dados.get(idEdit);
                   objAlteracao.setEquipado(armadura.getEquipado());
                   controller.alterar(objAlteracao);
                   Toast.makeText(EditArmaduraActivity.this, "Armadura Equipada  com sucesso", Toast.LENGTH_SHORT).show();
                   TelaPrincipal();
               } else {
                   Toast.makeText(EditArmaduraActivity.this, "Slot Insuficiente desequipe a outra armadura", Toast.LENGTH_LONG).show();

               }


        }
    }
}
