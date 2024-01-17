package devandroid.bender.rpg.view;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import devandroid.bender.rpg.R;
public class MainActivity extends AppCompatActivity {
    Button btnFinalizar;
    Button btnItens;
    Button btnHabilidades;
    Button btnPassivas;
    Button btnStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnItens = findViewById(R.id.btnItens);
        btnHabilidades = findViewById(R.id.btnHabilidades);
        btnPassivas = findViewById(R.id.btnPassivas);
        btnStatus = findViewById(R.id.btnStatus);
        btnFinalizar = findViewById(R.id.btnFinalizar);
        btnItens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelaItens();
            }
        });
        btnHabilidades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelaHabilidades();
            }
        });
        btnPassivas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelaPassivas();
            }
        });
        btnStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelaStatus();
            }
        });
        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Volte Sempre", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    private void TelaItens() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent telaPrincipal = new Intent(MainActivity.this,ItensActivity.class);
                startActivity(telaPrincipal);
                finish();
            }
        },1);
    }
    private void TelaHabilidades() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent telaPrincipal = new Intent(MainActivity.this,HabilidadesActivity.class);
                startActivity(telaPrincipal);
                finish();
            }
        },1);
    }
    private void TelaPassivas() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent telaPrincipal = new Intent(MainActivity.this,PassivasActivity.class);
                startActivity(telaPrincipal);
                finish();
            }
        },1);
    }   private void TelaStatus() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent telaPrincipal = new Intent(MainActivity.this,StatusActivity.class);
                startActivity(telaPrincipal);
                finish();
            }
        },1);
    }

}