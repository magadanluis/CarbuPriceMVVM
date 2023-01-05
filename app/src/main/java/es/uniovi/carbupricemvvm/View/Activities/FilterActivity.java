package es.uniovi.carbupricemvvm.View.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import es.uniovi.carbupricemvvm.R;

public class FilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Cargo el layout gasolinera_filtros
        setContentView(R.layout.gasolinera_filter);
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        //Hago invisible el SearchView del menu y la opción pa acceder al filtrado
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_filter).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        //Compruebo si se ha seleccionado el boton de retorno, si es así finalizo la actividad
        if (id == android.R.id.home) {
            this.finish();
            return true;
        }
        if (id == R.id.action_settings){
            Intent intent = new Intent(this, PrefsActivity.class);
            startActivity(intent);
            return true;
        }
        return true;
    }

}
