package devandroid.bender.rpg.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import devandroid.bender.rpg.R;
import androidx.appcompat.app.AppCompatActivity;
import devandroid.bender.rpg.database.RpgDB;
public class SplashActivity extends AppCompatActivity {
    public static final int TIME_OUT_SPLASH = 3000; // 3000
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        comutarTelaSplash();
    }
    private void comutarTelaSplash() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                RpgDB db = new RpgDB(SplashActivity.this);
                Intent telaPrincipal = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(telaPrincipal);
                finish();
            }
        },TIME_OUT_SPLASH);
    }
}