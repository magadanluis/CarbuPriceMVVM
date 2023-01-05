package es.uniovi.carbupricemvvm.data.Interfaces;

import es.uniovi.carbupricemvvm.data.utilities.Gasolineras;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RestService {
    @GET("/ServiciosRESTCarburantes/PreciosCarburantes/EstacionesTerrestres/FiltroCCAA/03")
    Call<Gasolineras> getInfo();
}
