package es.uniovi.carbupricemvvm.View.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import es.uniovi.carbupricemvvm.View.Fragments.GasolineraDetalleFragment;
import es.uniovi.carbupricemvvm.R;

public class GasolineraDetalleActivity extends AppCompatActivity {
    public static final String IDEESS = "IDEESS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Cargo el layout gasolinera_detalles
        setContentView(R.layout.gasolinera_detalles);
        //Obtengo el Bundle pasado a la actividad y saco el ideess
        Intent intent = getIntent();
        String ideess = intent.getStringExtra(IDEESS);
        if (savedInstanceState != null) {
            return;
        }
        else{
            //Cargo el fragmento pasandole la posicion previamente obtenida
            GasolineraDetalleFragment fragmentoDetalles = GasolineraDetalleFragment.newInstance(ideess);
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentDetalles, fragmentoDetalles).commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        //Hago invisible el SearchView del menu
        menu.findItem(R.id.action_search).setVisible(false);
        MenuItem filtro = menu.findItem(R.id.action_filter);
        //Modifico el color del filtro en función de si esta el modo noche activado o no
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            filtro.setIcon(R.drawable.ic_filter_list_white_24dp);
        } else {
            filtro.setIcon(R.drawable.ic_filter_list_black_24dp);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        //Compruebo si se ha seleccionado el boton de retorno, si es así finalizo la actividad
        if (id == android.R.id.home){
            this.finish();
            return true;
        }
        else{
            //Si no compruebo si se ha seleccionado el action_settings y lanzo la actividad de preferencias
            if (id == R.id.action_settings){
                Intent intent = new Intent(this, PrefsActivity.class);
                this.startActivity(intent);
                return true;
            }
            else{
                //En caso de que no haya sido este, compruebo si ha sido el action_filter y lanzo dicha actividad
                if (id == R.id.action_filter){
                    Intent intent = new Intent(this, FilterActivity.class);
                    this.startActivity(intent);
                    return true;
                }
                else{
                    return super.onOptionsItemSelected(item);
                }
            }
        }
    }

}
