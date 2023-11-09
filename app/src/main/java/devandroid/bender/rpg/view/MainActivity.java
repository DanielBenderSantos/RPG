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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnItens = findViewById(R.id.btnItens);
        btnFinalizar = findViewById(R.id.btnFinalizar);

        btnItens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelaItens();
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
}
