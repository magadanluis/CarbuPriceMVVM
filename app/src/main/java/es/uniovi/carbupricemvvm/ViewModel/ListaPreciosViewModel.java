package es.uniovi.carbupricemvvm.ViewModel;

import android.app.Application;
import java.util.List;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import es.uniovi.carbupricemvvm.data.repository.Repository;
import es.uniovi.carbupricemvvm.data.utilities.ListaEESSPrecio;

public class ListaPreciosViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<List<ListaEESSPrecio>> listaPreciosLiveData;

    //Método para inicializar el ViewModel
    public ListaPreciosViewModel(Application application){
        super(application);
        this.repository = Repository.getRepository(application);
        this.listaPreciosLiveData = repository.getPreciosList();
    }

    //Método que retorne la lista de precios
    public LiveData<List<ListaEESSPrecio>> getListaPreciosLiveData(){
        return this.listaPreciosLiveData;
    }

    //Método que actualiza dicha lista
    public void updateListaPreciosLiveData(){
        repository.updateListaPreciosData();
    }
}
