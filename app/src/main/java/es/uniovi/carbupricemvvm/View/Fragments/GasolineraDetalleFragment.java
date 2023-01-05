package es.uniovi.carbupricemvvm.View.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.Set;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import es.uniovi.carbupricemvvm.ViewModel.GasolineraDetallesViewModel;
import es.uniovi.carbupricemvvm.data.utilities.ListaEESSPrecio;
import es.uniovi.carbupricemvvm.R;

public class GasolineraDetalleFragment extends Fragment implements OnMapReadyCallback {

    //Variables a usar
    private static String IDEESS = "IDEESS";
    private static String list_preference_comb = "list_preference_combustibles";
    private Set<String> selection;
    private GoogleMap mMap;
    private GasolineraDetallesViewModel gasolineraDetallesViewModel;
    private TextView preciosCombustibles;
    private TextView rotuloGasolinera;
    private TextView precioGasoleoA;
    private TextView precioGasoleoB;
    private TextView precioGasoleoPremium;
    private TextView precioGasolina95E10;
    private TextView precioGasolina95E5;
    private TextView precioGasolina95E5Premium;
    private TextView precioGasolina98E10;
    private TextView precioGasolina98E5;
    private TextView precioBiodiesel;
    private TextView precioHidrogeno;
    private TextView precioBioetanol;
    private TextView precioGasNatCompr;
    private TextView precioGasNatLic;
    private TextView precioGasPetrLic;
    private TextView horarioGasolinera;
    private TextView direccionGasolinera;
    private String latitud;
    private String longitud;
    private String rotulo;

    public static GasolineraDetalleFragment newInstance(String ideess) {
        GasolineraDetalleFragment fragment = new GasolineraDetalleFragment();
        Bundle args = new Bundle();
        args.putString(IDEESS, ideess);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflo la vista
        View rootView;
        if (getActivity().getSupportFragmentManager().findFragmentById(R.id.gasolinera_details_frag)!= null){
            rootView = inflater.inflate(R.layout.gasolinera_detalles_fragment_double_pane, container, false);
        }
        else {
            rootView = inflater.inflate(R.layout.gasolinera_detalles_fragment, container, false);
        }
        //Obtengo la posicion accediendo al Bundle
        String ideess = "";
        Bundle args = getArguments();
        if (args != null && savedInstanceState==null){
            ideess = args.getString(IDEESS);
        }
        //Obtengo la referencia a los textViews correspondientes
        rotuloGasolinera = (TextView) rootView.findViewById(R.id.rotuloGasolinera);
        preciosCombustibles = (TextView) rootView.findViewById(R.id.PrecioTitulo);
        precioGasoleoA = (TextView) rootView.findViewById(R.id.precioGasoleoA);
        precioGasoleoB = (TextView) rootView.findViewById(R.id.precioGasoleoB);
        precioGasoleoPremium = (TextView) rootView.findViewById(R.id.precioGasoleoPremium);
        precioGasolina95E10 = (TextView) rootView.findViewById(R.id.precioGasolina95E10);
        precioGasolina95E5 = (TextView) rootView.findViewById(R.id.precioGasolina95E5);
        precioGasolina95E5Premium = (TextView) rootView.findViewById(R.id.precioGasolina95E5Premium);
        precioGasolina98E10 = (TextView) rootView.findViewById(R.id.precioGasolina98E10);
        precioGasolina98E5 = (TextView) rootView.findViewById(R.id.precioGasolina98E5);
        precioBiodiesel = (TextView) rootView.findViewById(R.id.precioBiodiesel);
        precioHidrogeno = (TextView) rootView.findViewById(R.id.precioHidrogeno);
        precioBioetanol = (TextView) rootView.findViewById(R.id.precioBioetanol);
        precioGasNatCompr = (TextView) rootView.findViewById(R.id.precioGasNaturalComprimido);
        precioGasNatLic = (TextView) rootView.findViewById(R.id.precioGasNaturalLicuado);
        precioGasPetrLic = (TextView) rootView.findViewById(R.id.precioGasesLicuadosPetroleo);
        direccionGasolinera = (TextView) rootView.findViewById(R.id.direccionGasolinera);
        horarioGasolinera = (TextView) rootView.findViewById(R.id.horarioGasolinera);

        //Preferencia de combustibles
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        selection = pref.getStringSet(list_preference_comb,null);

        //Cargo el mapa en el fragmento
        MapFragment map = (MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.map);
        map.getMapAsync(this);
        gasolineraDetallesViewModel = ViewModelProviders.of(this).get(GasolineraDetallesViewModel.class);
        gasolineraDetallesViewModel.getGasolinera().observe(this, new Observer<ListaEESSPrecio>() {
            @Override
            public void onChanged(ListaEESSPrecio listaEESSPrecio) {
                //Actualizo los datos de la vista
                preciosCombustibles.setVisibility(View.VISIBLE);
                rotuloGasolinera.setText(listaEESSPrecio.getRTulo());
                if (listaEESSPrecio.getPrecioGasoleoA()!=""){
                    precioGasoleoA.setText(getString(R.string.GasoleoA) +" " + listaEESSPrecio.getPrecioGasoleoA() + " €");
                    if (selection.contains("GasA")){
                        precioGasoleoA.setVisibility(View.VISIBLE);
                    }
                }
                else{
                    precioGasoleoA.setVisibility(View.GONE);
                    precioGasoleoA.setText("");
                }
                if (!listaEESSPrecio.getPrecioGasoleoB().equals("")){
                    precioGasoleoB.setText(getString(R.string.GasoleoB) + " "+listaEESSPrecio.getPrecioGasoleoB()+" €");
                    if (selection.contains("GasB")){
                        precioGasoleoB.setVisibility(View.VISIBLE);
                    }
                }
                else{
                    precioGasoleoB.setVisibility(View.GONE);
                    precioGasoleoB.setText("");
                }
                if (!listaEESSPrecio.getPrecioGasoleoPremium().equals("")){
                    precioGasoleoPremium.setText(getString(R.string.GasoleoPremium) + " "+listaEESSPrecio.getPrecioGasoleoPremium()+" €");
                    if (selection.contains("GasPrem")){
                        precioGasoleoPremium.setVisibility(View.VISIBLE);
                    }
                }
                else{
                    precioGasoleoPremium.setVisibility(View.GONE);
                    precioGasoleoPremium.setText("");
                }
                if (!listaEESSPrecio.getPrecioGasolina95E10().equals("")){
                    precioGasolina95E10.setText(getString(R.string.Gasolina95E10) + " "+listaEESSPrecio.getPrecioGasolina95E10()+" €");
                    if (selection.contains("Gas95E10")){
                        precioGasolina95E10.setVisibility(View.VISIBLE);
                    }
                }
                else{
                    precioGasolina95E10.setVisibility(View.GONE);
                    precioGasolina95E10.setText("");
                }
                if (!listaEESSPrecio.getPrecioGasolina95E5().equals("")){
                    precioGasolina95E5.setText(getString(R.string.Gasolina95E5) + " "+listaEESSPrecio.getPrecioGasolina95E5()+" €");
                    if (selection.contains("Gas95E5")){
                        precioGasolina95E5.setVisibility(View.VISIBLE);
                    }
                }
                else{
                    precioGasolina95E5.setVisibility(View.GONE);
                    precioGasolina95E5.setText("");
                }
                if (!listaEESSPrecio.getPrecioGasolina95E5Premium().equals("")){
                    precioGasolina95E5Premium.setText(getString(R.string.Gasolina95E5Premium) + " "+listaEESSPrecio.getPrecioGasolina95E5Premium()+" €");
                    if (selection.contains("Gas95E5Prem")){
                        precioGasolina95E5Premium.setVisibility(View.VISIBLE);
                    }
                }
                else{
                    precioGasolina95E5Premium.setVisibility(View.GONE);
                    precioGasolina95E5Premium.setText("");
                }
                if (!listaEESSPrecio.getPrecioGasolina98E10().equals("")){
                    precioGasolina98E10.setText(getString(R.string.Gasolina98E10) + " "+listaEESSPrecio.getPrecioGasolina98E10()+" €");
                    if (selection.contains("Gas98E10")){
                        precioGasolina98E10.setVisibility(View.VISIBLE);
                    }
                }
                else{
                    precioGasolina98E10.setVisibility(View.GONE);
                    precioGasolina98E10.setText("");
                }
                if (!listaEESSPrecio.getPrecioGasolina98E5().equals("")){
                    precioGasolina98E5.setText(getString(R.string.Gasolina98E5) + " "+listaEESSPrecio.getPrecioGasolina98E5()+" €");
                    if (selection.contains("Gas98E5")){
                        precioGasolina98E5.setVisibility(View.VISIBLE);
                    }
                }
                else{
                    precioGasolina98E5.setVisibility(View.GONE);
                    precioGasolina98E5.setText("");
                }
                if (!listaEESSPrecio.getPrecioBiodiesel().equals("")){
                    precioBiodiesel.setText(getString(R.string.Biodiesel) + " " + listaEESSPrecio.getPrecioBiodiesel()+ " €");
                    if (selection.contains("Biod")){
                        precioBiodiesel.setVisibility(View.VISIBLE);
                    }
                }
                else{
                    precioBiodiesel.setVisibility(View.GONE);
                    precioBiodiesel.setText("");
                }
                if (!listaEESSPrecio.getPrecioHidrogeno().equals("")){
                    precioHidrogeno.setText(getString(R.string.Hidrogeno) + " " + listaEESSPrecio.getPrecioHidrogeno()+ " €");
                    if (selection.contains("Hidr")){
                        precioHidrogeno.setVisibility(View.VISIBLE);
                    }
                }
                else{
                    precioHidrogeno.setVisibility(View.GONE);
                    precioHidrogeno.setText("");
                }
                if (!listaEESSPrecio.getPrecioBioetanol().equals("")){
                    precioBioetanol.setText(getString(R.string.Bioetanol) + " "+ listaEESSPrecio.getPrecioBioetanol()+ " €");
                    if (selection.contains("Bioet")){
                        precioBioetanol.setVisibility(View.VISIBLE);
                    }
                }
                else{
                    precioBioetanol.setVisibility(View.GONE);
                    precioBioetanol.setText("");
                }
                if (!listaEESSPrecio.getPrecioGasNaturalComprimido().equals("")){
                    precioGasNatCompr.setText(getString(R.string.GasNaturalComprimido) + " " + listaEESSPrecio.getPrecioGasNaturalComprimido() + " €");
                    if (selection.contains("Gnc")){
                        precioGasNatCompr.setVisibility(View.VISIBLE);
                    }
                }
                else{
                    precioGasNatCompr.setVisibility(View.GONE);
                    precioGasNatCompr.setText("");
                }
                if (!listaEESSPrecio.getPrecioGasNaturalLicuado().equals("")){
                    precioGasNatLic.setText(getString(R.string.GasNaturalLicuado) + " " +listaEESSPrecio.getPrecioGasNaturalLicuado() + " €");
                    if (selection.contains("Gnl")){
                        precioGasNatLic.setVisibility(View.VISIBLE);
                    }
                }
                else{
                    precioGasNatLic.setVisibility(View.GONE);
                    precioGasNatLic.setText("");
                }
                if (!listaEESSPrecio.getPrecioGasesLicuadosDelPetrLeo().equals("")){
                    precioGasPetrLic.setText(getString(R.string.GasesLicuadosPetroleo)+ " "+listaEESSPrecio.getPrecioGasesLicuadosDelPetrLeo() + " €");
                    if (selection.contains("Glp")){
                        precioGasPetrLic.setVisibility(View.VISIBLE);
                    }
                }
                else{
                    precioGasPetrLic.setVisibility(View.GONE);
                    precioGasPetrLic.setText("");
                }
                direccionGasolinera.setText(getString(R.string.direccionTv) + " " +listaEESSPrecio.getDirecciN()+", "+listaEESSPrecio.getCP());
                direccionGasolinera.setVisibility(View.VISIBLE);
                horarioGasolinera.setText(getString(R.string.horarioGasolinera) + " " + listaEESSPrecio.getHorario());
                horarioGasolinera.setVisibility(View.VISIBLE);
                //Obtengo la latitud y la longitud y la represento en el mapa con un marcador de color azul
                latitud = listaEESSPrecio.getLatitud();
                latitud = latitud.replace(',', '.');
                longitud = listaEESSPrecio.getLongitudWGS84();
                longitud = longitud.replace(',', '.');
                rotulo = listaEESSPrecio.getRTulo();
                if (mMap != null){
                    float Latitude = Float.parseFloat(latitud);
                    float Longitude = Float.parseFloat(longitud);
                    LatLng LatLong = new LatLng(Latitude, Longitude);
                    MarkerOptions marker = new MarkerOptions().
                            position(LatLong)
                            .title(rotulo).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                    mMap.clear();
                    mMap.addMarker(marker);
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLong, 13));
                }
            }
        });

        final ScrollView svRestoDetail = (ScrollView) rootView.findViewById(R.id.scrollView);
        ImageView ivMapTransparent = (ImageView) rootView.findViewById(R.id.ivMapTransparent);
        ivMapTransparent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        svRestoDetail.requestDisallowInterceptTouchEvent(true);
                        // Disable touch on transparent view
                        return false;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        svRestoDetail.requestDisallowInterceptTouchEvent(false);
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        svRestoDetail.requestDisallowInterceptTouchEvent(true);
                        return false;

                    default:
                        return true;
                }
            }});

        //En caso de que el ideess sea distinto de la cadena vacia modifico la posicion del viewModel
        if (ideess != ""){
            gasolineraDetallesViewModel.setIDEESS(ideess);
        }

        setHasOptionsMenu(true);
        return rootView;
    }

    public void setIDEESS(String ideess) {
        gasolineraDetallesViewModel.setIDEESS(ideess);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LinearLayout l = (LinearLayout) getActivity().findViewById(R.id.layoutfragmentomapa);
        l.setVisibility(View.VISIBLE);
        //Inicio el ViewModel de Detalles
        if (latitud!= null) {
            float Latitude = Float.parseFloat(latitud);
            float Longitude = Float.parseFloat(longitud);
            LatLng LatLong = new LatLng(Latitude, Longitude);
            MarkerOptions marker = new MarkerOptions().
                    position(LatLong)
                    .title(rotulo).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            mMap.clear();
            mMap.addMarker(marker);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLong, 13));
        }
    }


    @Override
    public void onResume(){
        super.onResume();
        //Preferencia de combustibles
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        selection = pref.getStringSet(list_preference_comb,null);
        if (precioBiodiesel.getText()!= "" && selection.contains("Biod")){
            precioBiodiesel.setVisibility(View.VISIBLE);
        }
        else{
            precioBiodiesel.setVisibility(View.GONE);
        }
        if (precioBioetanol.getText()!= "" && selection.contains("Bioet")){
            precioBioetanol.setVisibility(View.VISIBLE);
        }
        else{
            precioBioetanol.setVisibility(View.GONE);
        }
        if (precioGasoleoA.getText()!= "" && selection.contains("GasA")){
            precioGasoleoA.setVisibility(View.VISIBLE);
        }
        else{
            precioGasoleoA.setVisibility(View.GONE);
        }
        if (precioGasoleoB.getText()!= "" && selection.contains("GasB")){
            precioGasoleoB.setVisibility(View.VISIBLE);
        }
        else{
            precioGasoleoB.setVisibility(View.GONE);
        }
        /*
        if (precioGasolina95.getText()!= "" && selection.contains("Gas95")){
            precioGasolina95.setVisibility(View.VISIBLE);
        }
        else{
            precioGasolina95.setVisibility(View.GONE);
        }
        if (precioGasolina98.getText()!= "" && selection.contains("Gas98")){
            precioGasolina98.setVisibility(View.VISIBLE);
        }
        else{
            precioGasolina98.setVisibility(View.GONE);
        }
        if (precioNuevoGasoleoA.getText()!= "" && selection.contains("NGasA")){
            precioNuevoGasoleoA.setVisibility(View.VISIBLE);
        }
        else{
            precioNuevoGasoleoA.setVisibility(View.GONE);
        }*/
        if (precioGasNatCompr.getText() != "" && selection.contains("Gnc")){
            precioGasNatCompr.setVisibility(View.VISIBLE);
        }
        else{
            precioGasNatCompr.setVisibility(View.GONE);
        }
        if (precioGasNatLic.getText()!= "" && selection.contains("Gnl")){
            precioGasNatLic.setVisibility(View.VISIBLE);
        }
        else{
            precioGasNatLic.setVisibility(View.GONE);
        }
        if (precioGasPetrLic.getText()!= "" && selection.contains("Glp")){
            precioGasPetrLic.setVisibility(View.VISIBLE);
        }
        else{
            precioGasPetrLic.setVisibility(View.GONE);
        }
    }

}