package es.uniovi.carbupricemvvm.View.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.List;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import es.uniovi.carbupricemvvm.data.utilities.ListaEESSPrecio;
import es.uniovi.carbupricemvvm.View.Adapter.ListaPreciosViewAdapter;
import es.uniovi.carbupricemvvm.ViewModel.ListaPreciosViewModel;
import es.uniovi.carbupricemvvm.R;
import es.uniovi.carbupricemvvm.View.Activities.MainActivity;

public class ListaPreciosFragment extends Fragment{

    //Declaración de variables a usar
    private RecyclerView mRecyclerView;
    private ListaPreciosViewAdapter mAdapter;
    private ListaPreciosViewModel listaPreciosViewModel;
    private SwipeRefreshLayout mSwipe;

    public ListaPreciosFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflo la vista con el fragmento
        View rootView = inflater.inflate(R.layout.gasolinera_list_fragment, container, false);
        //Guardo la referencia al recycler view y le pongo un LinearLayout
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //Creo el adaptador y se lo asocio al RecyclerView
        mAdapter = new ListaPreciosViewAdapter(this.getContext());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.FiltraLista();
        //Cargo el ViewModel y observo
        listaPreciosViewModel = ViewModelProviders.of(this).get(ListaPreciosViewModel.class);
        listaPreciosViewModel.getListaPreciosLiveData().observe(this, new Observer<List<ListaEESSPrecio>>() {
            @Override
            public void onChanged(List<ListaEESSPrecio> listaEESSPrecios) {
                //Cuando se recibe una lista de gasolineras las actualizo en el adapter y pongo el swipe a false para que se deje de mostrar
                mAdapter.setmListaPrecios(listaEESSPrecios);
                mAdapter.FiltraLista();
                mSwipe.setRefreshing(false);
                MainActivity main = (MainActivity) getActivity();
                if (main.SearchQuery != null && mAdapter!= null){
                    mAdapter.getFilter().filter(main.SearchQuery);
                }
            }
        });
        //Asigno el setonRefresh al swipelayout
        mSwipe = rootView.findViewById(R.id.swipe);
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh() {
                //Compruebo si se tiene conexión a internet
                if (isOnline()){
                    SharedPreferences.Editor prefsEdit = PreferenceManager.getDefaultSharedPreferences(getContext()).edit();
                    listaPreciosViewModel.updateListaPreciosLiveData();
                }
                else{
                     //Muestro un toast que indique que no hay datos y que por tanto no se procederá a efectuar la actualización
                    Toast toast1 = Toast.makeText(getContext(), getString(R.string.sinconexion), Toast.LENGTH_LONG);
                    toast1.show();
                    mSwipe.setRefreshing(false);
                }
            }
        });
        //Habilito las opciones de la toolbar
        setHasOptionsMenu(true);
        return rootView;
    }

    //Método que comprueba si hay conexión a Internet
    private boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE) ;
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}
