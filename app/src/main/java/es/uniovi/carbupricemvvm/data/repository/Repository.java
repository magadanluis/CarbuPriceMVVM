package es.uniovi.carbupricemvvm.data.repository;

import android.app.Application;
import java.util.List;
import androidx.lifecycle.LiveData;
import es.uniovi.carbupricemvvm.data.remoteapi.WebService;
import es.uniovi.carbupricemvvm.data.utilities.ListaEESSPrecio;

public class Repository {

    private WebService webService;
    private LiveData<List<ListaEESSPrecio>> listaPrecios;
    private LiveData<ListaEESSPrecio> gasolinera;
    private static Repository repository;

    //Hago un singleton, si este existe lo retorno, sino lo creo
    public static Repository getRepository(Application app){
        if (repository ==null){
            repository = new Repository(app);
        }
        return repository;
    }

    //Constructor del repositorio
    public Repository(Application application){
        this.webService = new WebService(application);
        updateListaPreciosData();
    }

    //Método que actualiza la lista de precios
    public void updateListaPreciosData(){
        this.listaPrecios = webService.getListaPreecios();
    }

    //Método que retorna la lista de precios, llamando inicialmente a una actualización del mismo
    public LiveData<List<ListaEESSPrecio>> getPreciosList(){
        updateListaPreciosData();
        return this.listaPrecios;
    }

    //Método que actualiza la gasolinera seleccionada
    public void updateGasolinera(String ideess){
        this.gasolinera = webService.getGasolinera(ideess);
    }

    //Método que retorna la gasolinera, previamente actualizando la misma
    public LiveData<ListaEESSPrecio> getGasolinera(String ideess){
        updateGasolinera(ideess);
        return this.gasolinera;
    }
}
