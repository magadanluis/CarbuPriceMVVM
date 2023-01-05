package es.uniovi.carbupricemvvm.data.utilities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gasolineras {
    @SerializedName("Fecha")
    @Expose
    private String fecha;
    @SerializedName("ListaEESSPrecio")
    @Expose
    private List<ListaEESSPrecio> listaEESSPrecio = null;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<ListaEESSPrecio> getListaEESSPrecio() {
        return listaEESSPrecio;
    }

    public void setListaEESSPrecio(List<ListaEESSPrecio> listaEESSPrecio) {
        this.listaEESSPrecio = listaEESSPrecio;
    }
}
