package es.uniovi.carbupricemvvm.ViewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import es.uniovi.carbupricemvvm.data.repository.Repository;
import es.uniovi.carbupricemvvm.data.utilities.ListaEESSPrecio;

public class GasolineraDetallesViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<ListaEESSPrecio> gasolinera;
    private MutableLiveData<String> ideess = new MutableLiveData<>();

    //Método para inicializar el ViewModel
    public GasolineraDetallesViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getRepository(application);
        gasolinera = Transformations.switchMap(ideess, new Function<String, LiveData<ListaEESSPrecio>>() {
            @Override
            public LiveData<ListaEESSPrecio> apply(String input) {
                return repository.getGasolinera(input);
            }
        });
    }

    //Método que actualiza el ideess seleccionado
    public void setIDEESS(String ideess){
        this.ideess.setValue(ideess);
    }

    //Método que retorna la gasolinera correspondiente
    public LiveData<ListaEESSPrecio> getGasolinera(){
        return this.gasolinera;
    }
}
