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
import devandroid.bender.rpg.model.Habilidade;
public class EditHabilidadeActivity extends AppCompatActivity {
    HabilidadeController controller;
    Habilidade habilidade;
    EditText editNome;
    EditText editTempoDeRecarga;
    EditText editDescricao;
    Integer idEdit ;
    Integer idDelete;
    String validaTempoDeRecarga;
    Button btnSalvar;
    Button btnUsar;
    Button btnDeletar;
    Button btnVoltar;
    List<Habilidade> dados;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_habilidade);
        // I recebe os dados passado pela tela anterior
        Intent it = getIntent();
        idEdit = it.getIntExtra("id_numero",0);
        // F recebe os dados passado pela tela anterior
        controller = new HabilidadeController(EditHabilidadeActivity.this);
        dados = controller.getListaDeDados();
        editNome = findViewById(R.id.editNome);
        editTempoDeRecarga = findViewById(R.id.editTempoDeRecarga);
        editDescricao = findViewById(R.id.editDescricao);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnUsar = findViewById(R.id.btnUsar);
        btnDeletar = findViewById(R.id.btnDeletar);
        btnVoltar = findViewById(R.id.btnVoltar);
        if( idEdit != -1) {
            editNome.setText(dados.get(idEdit).getNome());
            editTempoDeRecarga.setText("" + dados.get(idEdit).getTempoDeRegarga());
            editDescricao.setText(dados.get(idEdit).getDescricao());
            btnDeletar.setEnabled(true);
            idDelete = dados.get(idEdit).getId();
            if (dados.get(idEdit).getDisponivelEm() == 0) {
                btnUsar.setEnabled(true);
            }
        } else {
            btnDeletar.setEnabled(false);
        }
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validaTempoDeRecarga = (editTempoDeRecarga.getText().toString());
                if (!validaTempoDeRecarga.isEmpty()){
                    habilidade = new Habilidade();
                    habilidade.setNome(editNome.getText().toString());
                    habilidade.setTempoDeRegarga(Double.parseDouble(editTempoDeRecarga.getText().toString()));
                    habilidade.setDescricao(editDescricao.getText().toString());
                    if (!habilidade.getNome().isEmpty()){
                            if (!habilidade.getDescricao().isEmpty()){
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
        btnUsar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                habilidade = new Habilidade();
                habilidade.setDisponivelEm(dados.get(idEdit).getTempoDeRegarga());
                Habilidade objAlteracao = dados.get(idEdit);
                objAlteracao.setDisponivelEm(habilidade.getDisponivelEm());
                controller.alterar(objAlteracao);
                Toast.makeText(EditHabilidadeActivity.this, "Habilidade Usada com sucesso", Toast.LENGTH_LONG).show();
                TelaPrincipal();
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