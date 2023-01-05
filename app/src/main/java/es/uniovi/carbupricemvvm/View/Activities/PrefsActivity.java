package es.uniovi.carbupricemvvm.View.Activities;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import androidx.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import es.uniovi.carbupricemvvm.R;

public class PrefsActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean modNight = prefs.getBoolean("modoNoche", false);
        System.out.println("Modo noche "+ modNight);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            if (modNight) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferenceactivity);
    }
}
