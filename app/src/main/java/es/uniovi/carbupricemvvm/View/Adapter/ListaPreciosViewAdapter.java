package es.uniovi.carbupricemvvm.View.Adapter;

import android.content.Context;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.RecyclerView;
import es.uniovi.carbupricemvvm.View.Activities.MainActivity;
import es.uniovi.carbupricemvvm.View.Interfaces.ListItemOnClickInterface;
import es.uniovi.carbupricemvvm.Controller.logicFunctions;
import es.uniovi.carbupricemvvm.data.utilities.ListaEESSPrecio;
import es.uniovi.carbupricemvvm.R;
import okhttp3.internal.Version;

public class ListaPreciosViewAdapter extends RecyclerView.Adapter<ListaPreciosViewAdapter.ListaPreciosHolder> implements Filterable {

    //Variables globales
    public static List<ListaEESSPrecio> listaPrecios;
    public static List<ListaEESSPrecio> listaPreciosFiltrada;
    public static List<ListaEESSPrecio> listaPreciosBusqueda;
    public ListItemOnClickInterface listItemOnClickInterface;
    private Context context;

    @NonNull
    @Override
    public ListaPreciosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.listgasolineras, parent, false);
        context = parent.getContext();
        return new ListaPreciosHolder(itemview);
    }

    public void setmListaPrecios(List<ListaEESSPrecio> v){
        //Cuando la lista precios se modifica envio un notifyDataSetChanged
        this.listaPrecios = v;
        this.listaPreciosFiltrada = v;
        this.listaPreciosBusqueda = v;
        notifyDataSetChanged();
    }

    public ListaPreciosViewAdapter(Context context){
        //Referencia al listItemOnnClickInterface del main activity
        this.context = context;
        if (context instanceof ListItemOnClickInterface){
            listItemOnClickInterface = (ListItemOnClickInterface) context;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ListaPreciosHolder holder, int position) {
        logicFunctions l = new logicFunctions();
        //Si no hay conexion pongo un elemento que lo indique
        if (!isOnline() && listaPrecios == null) {
            holder.textViewLocalidad.setText(this.context.getResources().getText(R.string.errorCargar));
            holder.textViewDireccion.setText(this.context.getResources().getText(R.string.falloConexion));
            holder.textViewHorario.setText(this.context.getResources().getText(R.string.comprobacion));
            holder.imageViewGasolinera.setImageResource(l.creaImagen("NoDato"));
        }
        else {
            if (listaPrecios != null){
            //Según voy almacenando datos en el recyclerView, pongo de fondo colores que se van alternando en funcion de si se usa el modo noche o no
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N_MR1){
                if (position % 2 == 1) {
                    holder.itemView.setBackgroundColor(Color.parseColor("#305194"));
                } else {
                    holder.itemView.setBackgroundColor(Color.parseColor("#5e77ac"));
                }
            }
            else {
                if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                    if (position % 2 == 1) {
                        holder.itemView.setBackgroundColor(Color.parseColor("#305194"));
                    } else {
                        holder.itemView.setBackgroundColor(Color.parseColor("#5e77ac"));
                    }
                } else {
                    if (position % 2 == 1) {
                        holder.itemView.setBackgroundColor(Color.parseColor("#c8e6c9"));
                    } else {
                        holder.itemView.setBackgroundColor(Color.parseColor("#b2dfdb"));
                    }
                }
            }
            int recurso = l.creaImagen(listaPreciosBusqueda.get(position).getRTulo());
            if (recurso != -1) {
                //En función del rótulo de la gasolinera, la imagen empleada será asociada a dicho rotulo
                holder.imageViewGasolinera.setImageResource(l.creaImagen(listaPreciosBusqueda.get(position).getRTulo()));
                //Actualizo los datos mostrados sobre Horario, Localidad y Direccion de la gasolinera
                holder.textViewHorario.setText(listaPreciosBusqueda.get(position).getHorario());
                holder.textViewLocalidad.setText(listaPreciosBusqueda.get(position).getLocalidad());
                holder.textViewDireccion.setText(listaPreciosBusqueda.get(position).getDirecciN()); }
            }
            if (listaPreciosBusqueda != null) {
                if (getItemCount() == 0) {
                    holder.textViewLocalidad.setText("NINGÚN DATO CORRESPONDE CON LOS CRITERIOS ESPECIFICADOS");
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        if (listaPreciosBusqueda == null){
            return 1;
        }
        else {
            return listaPreciosBusqueda.size();
        }
    }

    //Método para aplicar el filtrado sobre la lista del recycler view
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                List<ListaEESSPrecio> filteredList = new ArrayList<>();
                //Si no es null procedo a aplicar el filtrado
                if (listaPrecios!=null){
                    //Si la cadena esta vacía la lista filtrada no se modifica
                    if (charString.isEmpty()) {
                        filteredList = listaPreciosFiltrada;
                    }
                    //Sino
                    else {
                        //Para cada elemento de la lista voy filtrando
                        for (ListaEESSPrecio g : listaPreciosFiltrada) {
                            //En funcion de la preferencia activada filtro por rotulo, localidad, municipio, etc
                            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
                            String preferencia = pref.getString("list_preference_busqueda", "Rótulo");
                            if (preferencia.equals("1")) {
                                if (g.getRTulo().toLowerCase().contains(charString.toLowerCase())) {
                                    filteredList.add(g);
                                }
                            } else {
                                if (preferencia.equals("2")) {
                                    if (g.getLocalidad().toLowerCase().contains(charString.toLowerCase())) {
                                        filteredList.add(g);
                                    }
                                } else {
                                    if (preferencia.equals("3")) {
                                        if (g.getMunicipio().toLowerCase().contains(charString.toLowerCase())) {
                                            filteredList.add(g);
                                        }
                                    } else {
                                        if (preferencia.equals("4")) {
                                            if (g.getDirecciN().toLowerCase().contains(charString.toLowerCase())) {
                                                filteredList.add(g);
                                            }
                                        } else {
                                            if (g.getCP().toLowerCase().contains(charString.toLowerCase())) {
                                                filteredList.add(g);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = filteredList;
                    return filterResults;
                }
                return null;
            }
            //Una vez se aplica el filtrado, notifico el cambio
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results !=null){
                    listaPreciosBusqueda = (ArrayList<ListaEESSPrecio>) results.values;
                    notifyDataSetChanged();
                }
            }
        };
    }

    public class ListaPreciosHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textViewLocalidad;
        public TextView textViewDireccion;
        public TextView textViewHorario;
        public ImageView imageViewGasolinera;

        public ListaPreciosHolder(@NonNull View itemview){
            super(itemview);
            //Guardo referencia al elemento correspondiente del recycler
            imageViewGasolinera = itemview.findViewById(R.id.imagen);
            textViewHorario = itemview.findViewById(R.id.horario);
            textViewDireccion = itemview.findViewById(R.id.direccion);
            textViewLocalidad = itemview.findViewById(R.id.localidad);
            itemview.setOnClickListener(this);
        }
        //Creo el método onClick
        @Override
        public void onClick(View v) {
            if (listaPrecios != null){
                listItemOnClickInterface.onItemClick(listaPreciosBusqueda.get(getLayoutPosition()).getIDEESS());
            }
        }
    }

    //Método para aplicar el filtrado necesario con los filtros previamente establecidos
    public void FiltraLista(){
        if (listaPrecios != null) {
            logicFunctions l = new logicFunctions();
            SharedPreferences pref = (SharedPreferences) PreferenceManager.getDefaultSharedPreferences(context);
            List<Boolean> prefs = new ArrayList<>();
            prefs.add(pref.getBoolean("cbGijon", false));
            prefs.add(pref.getBoolean("cbOviedo", false));
            prefs.add(pref.getBoolean("cbAviles", false));
            prefs.add(pref.getBoolean("cbRepsol", false));
            prefs.add(pref.getBoolean("cbCepsa", false));
            prefs.add(pref.getBoolean("cbShell", false));
            prefs.add(pref.getBoolean("cbBP", false));
            prefs.add(pref.getBoolean("cbPetronor", false));
            prefs.add(pref.getBoolean("cbCampsa", false));
            prefs.add(pref.getBoolean("cbGalp", false));
            prefs.add(pref.getBoolean("cbGasoleoA", false));
            prefs.add(pref.getBoolean("cbGasoleoB", false));
            prefs.add(pref.getBoolean("cbGasoleoPremium", false));
            prefs.add(pref.getBoolean("cbGasolina95E10", false));
            prefs.add(pref.getBoolean("cbGasolina95E5", false));
            prefs.add(pref.getBoolean("cbGasolina95E5Premium", false));
            prefs.add(pref.getBoolean("cbGasolina98E10", false));
            prefs.add(pref.getBoolean("cbGasolina98E5", false));
            prefs.add(pref.getBoolean("cbBiodiesel", false));
            prefs.add(pref.getBoolean("cbHidrogeno", false));
            prefs.add(pref.getBoolean("cbBioetanol", false));
            prefs.add(pref.getBoolean("cbGasNatCompr", false));
            prefs.add(pref.getBoolean("cbGasNatLic", false));
            prefs.add(pref.getBoolean("cbGasLicPetr", false));
            listaPreciosFiltrada = l.FiltraLista(listaPrecios, prefs);
            notifyDataSetChanged();
        }
    }

    //Método que comprueba si hay conexión a Internet
    private boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE) ;
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}
