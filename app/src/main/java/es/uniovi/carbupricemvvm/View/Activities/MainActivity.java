package es.uniovi.carbupricemvvm.View.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import es.uniovi.carbupricemvvm.View.Fragments.GasolineraDetalleFragment;
import es.uniovi.carbupricemvvm.View.Interfaces.ListItemOnClickInterface;
import es.uniovi.carbupricemvvm.View.Adapter.ListaPreciosViewAdapter;
import es.uniovi.carbupricemvvm.R;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements ListItemOnClickInterface {

    public String SearchQuery;
    private boolean mTwoPanes;
    public Menu m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences prefs = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this);
        //Si la preferencia mododNoche esta activada pongo el modo noche
        boolean modNight = prefs.getBoolean("modoNoche", false);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N_MR1){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            if (modNight) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        }
        super.onCreate(savedInstanceState);
        //Incluya la vista del activity_main
        setContentView(R.layout.main);
        //La primera vez que se ejecute la aplicacion, inicializo las preferencias con su valor por defecto (falla con el ListPreference)
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        //Compruebo si list_preference_busqueda esta inicializado, en caso de no estarlo lo inicializo con el valor 1 correspondiente al rótulo
        String s = PreferenceManager.getDefaultSharedPreferences(this).getString("list_preference_busqueda","NO HABIA NADA");
        if (s.equals("NO HABIA NADA")){
            SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor e = p.edit();
            e.putString("list_preference_busqueda", "1");
            e.commit();
        }
        //En caso de que el layout incluido tenga gasolinera_details_container, nos hayaremos en el caso de TwoPanes, y
        //modifico dicha variable para que sea true
        if (findViewById(R.id.gasolinera_details_container) != null){
            mTwoPanes = true;
        }
        else{
            mTwoPanes = false;
        }
        //Si el Bundle es null es que se trata de la primera vez que se carga, por lo que solo muestra el toast al iniciar la aplicacion
        if(savedInstanceState == null){
            String prefUser = PreferenceManager.getDefaultSharedPreferences(this).getString("usuario","");
            //Muestro un toast con el mensaje "Bienvenido <usuario>"
            Toast toast1 = Toast.makeText(getApplicationContext(), getString(R.string.bienvenido) +" "+ prefUser, Toast.LENGTH_SHORT);
            toast1.show();
            SearchQuery ="";
        }
        else{
            SearchQuery = savedInstanceState.getString("searchQuery");
        }
    }

    //Cada vez que se guarde el estado actual de la actividad, almaceno en el bundle el valor de SearchQuery
    @Override
    public void onSaveInstanceState(Bundle b){
        super.onSaveInstanceState(b);
        b.putString("searchQuery", SearchQuery);
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        super.onCreateOptionsMenu(menu);
        m = menu;
        //Inflo el menu
        getMenuInflater().inflate(R.menu.menu, menu);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String preferencia = pref.getString("list_preference_busqueda", getString(R.string.rotulo));
        switch (preferencia){
            case "1":
                preferencia = getString(R.string.rotulo);
                break;
            case "2":
                preferencia = getString(R.string.localidad);
                break;
            case "3":
                preferencia = getString(R.string.municipio);
                break;
            case "4":
                preferencia = getString(R.string.direccion);
                break;
            case "5":
                preferencia = getString(R.string.codigopostal);
                break;
        }
        MenuItem filtro = menu.findItem(R.id.action_filter);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            filtro.setIcon(R.drawable.ic_filter_list_white_24dp);
        } else {
            filtro.setIcon(R.drawable.ic_filter_list_black_24dp);
        }

        MenuItem m = menu.findItem(R.id.action_search);
        //Hago lo mismo que en el onResume para la primera vez que se cree.
        if (m!= null){
            SearchView s = (SearchView) m.getActionView();
            if (s!= null){
                if(SearchQuery!=null){
                    s.setQuery(SearchQuery, true);
                    RecyclerView r = findViewById(R.id.recycler);
                    ListaPreciosViewAdapter mAdapter = (ListaPreciosViewAdapter)r.getAdapter();
                    mAdapter.getFilter().filter(SearchQuery);
                }
                //Actualizo la queryhint
                s.setQueryHint(getString(R.string.buscarpor)+ " "+ preferencia);
                //Le incluyo un QueryTextListener al SearchView
                s.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    //Al enviar aplico el filtrado sobre dicha cadena
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        RecyclerView r = findViewById(R.id.recycler);
                        ListaPreciosViewAdapter mAdapter = (ListaPreciosViewAdapter) r.getAdapter();
                        mAdapter.getFilter().filter(s);
                        return false;
                    }
                    //Al ir escribiendo o borrando aplico también el filtrado
                    @Override
                    public boolean onQueryTextChange(String s) {
                        RecyclerView r = findViewById(R.id.recycler);
                        ListaPreciosViewAdapter mAdapter = (ListaPreciosViewAdapter) r.getAdapter();
                        mAdapter.getFilter().filter(s);
                        SearchQuery = s;
                        return false;
                    }
                });
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        //En caso de que se pulse el botón de ajustes del menu, lanzo una nueva actividad hacia PrefsActivity
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, PrefsActivity.class);
            startActivity(intent);
            return true;
        }
        //Sin embargo si pulso el botón de filtrado lanzo una nueva actividad hacia FilterActivity
        if (id == R.id.action_filter) {
            Intent intent = new Intent(this, FilterActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume(){
        super.onResume();
        //Cada vez que la actividad pasa por el estado Resume miro las preferencias correspondiente a la preferencia de busqueda
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String preferencia = pref.getString("list_preference_busqueda",getString(R.string.rotulo));
        //Obtengo la preferencia y en función del valor obtenido asigno el String que corresponde
        //Esto lo he hecho así para poder soportar internacionalización sin que surjan problemas
        switch (preferencia){
            case "1":
                preferencia = getString(R.string.rotulo);
                break;
            case "2":
                preferencia = getString(R.string.localidad);
                break;
            case "3":
                preferencia = getString(R.string.municipio);
                break;
            case "4":
                preferencia = getString(R.string.direccion);
                break;
            case "5":
                preferencia = getString(R.string.codigopostal);
                break;
        }
        //Acceso al searchView correspondiente al menu
        SearchView s = findViewById(R.id.action_search);
        if (s!=null){
            //En caso de no ser nulo, le pongo como hint: "buscar por <preferencia seleccionada>
            s.setQueryHint(getString(R.string.buscarpor)+" "+ preferencia);
        }
        RecyclerView r = findViewById(R.id.recycler);
        ListaPreciosViewAdapter mAdapter2 = (ListaPreciosViewAdapter)r.getAdapter();
        mAdapter2.FiltraLista();
        if (SearchQuery != null){
            mAdapter2.getFilter().filter(SearchQuery);
        }
    }

    @Override
    public void onItemClick(String IDEESS) {
        //Compruebo si se esta en el layout con dos fragmentos
        if (mTwoPanes){
            //Si hay dos fragmentos, cuando se clique en un elemento actualizo el fragmento de detalles mediante el metodo setPos
            FragmentManager fragmentManager = getSupportFragmentManager();
            GasolineraDetalleFragment c = (GasolineraDetalleFragment) fragmentManager.findFragmentById(R.id.gasolinera_details_frag);
            c.setIDEESS(IDEESS);
        }
        else{
            //Cuando se clica, se lanza una nueva actividad pasandole en un Bundle la posicion correspondiente
            Intent intent = new Intent(this, GasolineraDetalleActivity.class);
            intent.putExtra("IDEESS", IDEESS);
            startActivity(intent);
        }
    }
}
