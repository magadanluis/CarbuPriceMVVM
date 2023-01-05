package es.uniovi.carbupricemvvm.data.remoteapi;

import androidx.lifecycle.MutableLiveData;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import es.uniovi.carbupricemvvm.data.Interfaces.RestService;
import es.uniovi.carbupricemvvm.data.utilities.Gasolineras;
import es.uniovi.carbupricemvvm.data.utilities.ListaEESSPrecio;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebService{
    //Variables de la clase WebService
    private RestService restService;
    private MutableLiveData<List<ListaEESSPrecio>> listaPrecios = new MutableLiveData<>();
    private MutableLiveData<ListaEESSPrecio> gasolinera = new MutableLiveData<>();
    private Application application;

    public WebService(Application app){
        //Guardo la referencia de la aplicación
        application = app;
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
        //Creo el builder del retrofit para posteriormente crear el elemento retrofit
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://sedeaplicaciones.minetur.gob.es/")
                .addConverterFactory(GsonConverterFactory.create(gson));
        //Inicio la variable retrofit
        Retrofit retrofit = builder.build();
        //Tras ello incializo el restService
        this.restService = retrofit.create(RestService.class);
        updateListaPrecios();
    }

    public void updateListaPrecios(){
        //Compruebo si hay conexión, en caso de haberla descargo los datos
        if (isOnline()){
            restService.getInfo().enqueue(new Callback<Gasolineras>() {
                @Override
                public void onResponse(Call<Gasolineras> call, Response<Gasolineras> response) {
                    Gasolineras gasolineras = response.body();
                    //Actualizo listaPrecios
                    listaPrecios.setValue(gasolineras.getListaEESSPrecio());
                }
                @Override
                public void onFailure(Call<Gasolineras> call, Throwable t) {
                    //Si se produce algún error lo advierto en el debug
                    listaPrecios.setValue(null);
                    Log.e("Error webService update", t.toString());
                }
            });
        }
    }

    public void updateGasolinera(final String ideess){
        //Recorro todos los elementos del listaPrecios hasta encontrar al que corresponda con el id pasado como parametro
        for (ListaEESSPrecio g: listaPrecios.getValue()) {
            if (g.getIDEESS().equals(ideess)){
                gasolinera.setValue(g);
                break;
            }
        }
    }

    //Método que retorna la lista de gasolineras, para ello primero llama al metodo update para mantenerse actualizado
    public MutableLiveData<List<ListaEESSPrecio>> getListaPreecios(){
        updateListaPrecios();
        return this.listaPrecios;
    }

    public MutableLiveData<ListaEESSPrecio> getGasolinera(String ideess){
        updateGasolinera(ideess);
        return this.gasolinera;
    }

    private boolean isOnline() {
        //Método que comprueba la conexión a Internet
        ConnectivityManager connMgr = (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE) ;
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}
