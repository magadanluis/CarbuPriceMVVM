package es.uniovi.carbupricemvvm.Controller;

import java.util.ArrayList;
import java.util.List;
import es.uniovi.carbupricemvvm.R;
import es.uniovi.carbupricemvvm.data.utilities.ListaEESSPrecio;

public class logicFunctions {

    //Función a la que pasada una lista de gasolineras y un array de booleanos correspondiente a los filtros activados devuelve una lista filtrada
    public List<ListaEESSPrecio> FiltraLista(List<ListaEESSPrecio> listaPrecios, List<Boolean> prefs){
        // Formato del booleano [Gijon, Oviedo, Aviles, Repsol, Cepsa, Shell, BP, Petronor, Campsa, Galp, GasoleoA,
        // GasoleoB, Gasolina98, Gasolina95, NuevoGasoleoA, Biodiesel, Bioetanol, GasNaturalComprimido, GasNaturalLicuado, GasesLicuadosPetroleo]
        boolean Gijon = prefs.get(0);
        boolean Oviedo = prefs.get(1);
        boolean Aviles = prefs.get(2);
        List<ListaEESSPrecio> filteredAuxListMun = new ArrayList<>();
        //Aplico el filtro sobre municipio
        for (ListaEESSPrecio g : listaPrecios) {
            if (Gijon) {
                if (g.getMunicipio().contains("Gijón")) {
                    filteredAuxListMun.add(g);
                }
            }
            if (Oviedo) {
                if (g.getMunicipio().contains("Oviedo")) {
                    if (!filteredAuxListMun.contains(g)) {
                        filteredAuxListMun.add(g);
                    }
                }
            }
            if (Aviles) {
                if (g.getMunicipio().contains("Avilés")) {
                    if (!filteredAuxListMun.contains(g)) {
                        filteredAuxListMun.add(g);
                    }
                }
            }
            if (!(Aviles || Gijon || Oviedo)) {
                filteredAuxListMun.add(g);
            }
        }
        //Una vez filtro por municipio procedo a filtrar por marca
        boolean Repsol = prefs.get(3);
        boolean Cepsa = prefs.get(4);
        boolean Shell = prefs.get(5);
        boolean BP = prefs.get(6);
        boolean Petronor = prefs.get(7);
        boolean Campsa = prefs.get(8);
        boolean Galp = prefs.get(9);
        List<ListaEESSPrecio> filteredAuxListMarca = new ArrayList<>();
        //Comienzo el filtrado por marca
        for (ListaEESSPrecio g : filteredAuxListMun) {
            if (Repsol) {
                if (g.getRTulo().contains("REPSOL")) {
                    filteredAuxListMarca.add(g);
                }
            }
            if (Cepsa) {
                if (g.getRTulo().contains("CEPSA")) {
                    filteredAuxListMarca.add(g);
                }
            }
            if (Shell) {
                if (g.getRTulo().contains("SHELL")) {
                    filteredAuxListMarca.add(g);
                }
            }
            if (BP) {
                if (g.getRTulo().contains("BP")) {
                    filteredAuxListMarca.add(g);
                }
            }
            if (Petronor) {
                if (g.getRTulo().contains("PETRONOR")) {
                    filteredAuxListMarca.add(g);
                }
            }
            if (Campsa) {
                if (g.getRTulo().contains("CAMPSA")) {
                    filteredAuxListMarca.add(g);
                }
            }
            if (Galp) {
                if (g.getRTulo().contains("GALP")) {
                    filteredAuxListMarca.add(g);
                }
            }
            if (!(Repsol || Cepsa || Shell || BP || Petronor || Campsa || Galp)){
                filteredAuxListMarca.add(g);
            }
        }

        //Finalmente procedo a filtrar aquellas que tengan alguno de los tipos de combustible seleccionados
        boolean GasoleoA = prefs.get(10);
        boolean GasoleoB = prefs.get(11);
        boolean GasoleoPremium = prefs.get(12);
        boolean Gasolina95E10 = prefs.get(13);
        boolean Gasolina95E5 = prefs.get(14);
        boolean Gasolina95E5Premium = prefs.get(15);
        boolean Gasolina98E10 = prefs.get(16);
        boolean Gasolina98E5 = prefs.get(17);
        boolean Biodiesel = prefs.get(18);
        boolean Hidrogeno = prefs.get(19);
        boolean Bioetanol = prefs.get(20);
        boolean GasNatCompr = prefs.get(21);
        boolean GasNatLic = prefs.get(22);
        boolean GasLicPetr = prefs.get(23);
        List<ListaEESSPrecio> filteredAuxListCombustible = new ArrayList<>();

        for (ListaEESSPrecio g : filteredAuxListMarca){
            if (GasoleoA){
                if (!g.getPrecioGasoleoA().equals("")){
                    if (!filteredAuxListCombustible.contains(g)) {
                        filteredAuxListCombustible.add(g);
                    }
                }
            }
            if (GasoleoB) {
                if (!g.getPrecioGasoleoB().equals("")) {
                    if (!filteredAuxListCombustible.contains(g)) {
                        filteredAuxListCombustible.add(g);
                    }
                }
            }
            if (GasoleoPremium) {
                if (!g.getPrecioGasoleoPremium().equals("")) {
                    if (!filteredAuxListCombustible.contains(g)) {
                        filteredAuxListCombustible.add(g);
                    }
                }
            }
            if (Gasolina95E10) {
                if (!g.getPrecioGasolina95E10().equals("")) {
                    if (!filteredAuxListCombustible.contains(g)) {
                        filteredAuxListCombustible.add(g);
                    }
                }
            }
            if (Gasolina95E5) {
                if (!g.getPrecioGasolina95E5().equals("")) {
                    if (!filteredAuxListCombustible.contains(g)) {
                        filteredAuxListCombustible.add(g);
                    }
                }
            }
            if (Gasolina95E5Premium) {
                if (!g.getPrecioGasolina95E5Premium().equals("")) {
                    if (!filteredAuxListCombustible.contains(g)) {
                        filteredAuxListCombustible.add(g);
                    }
                }
            }
            if (Gasolina98E10) {
                if (!g.getPrecioGasolina98E10().equals("")) {
                    if (!filteredAuxListCombustible.contains(g)) {
                        filteredAuxListCombustible.add(g);
                    }
                }
            }
            if (Gasolina98E5) {
                if (!g.getPrecioGasolina98E5().equals("")) {
                    if (!filteredAuxListCombustible.contains(g)) {
                        filteredAuxListCombustible.add(g);
                    }
                }
            }
            if (Biodiesel) {
                if (!g.getPrecioBiodiesel().equals("")){
                    if (!filteredAuxListCombustible.contains(g)) {
                        filteredAuxListCombustible.add(g);
                    }
                }
            }
            if (Hidrogeno) {
                if (!g.getPrecioHidrogeno().equals("")){
                    if (!filteredAuxListCombustible.contains(g)) {
                        filteredAuxListCombustible.add(g);
                    }
                }
            }
            if (Bioetanol) {
                if (!g.getPrecioBioetanol().equals("")) {
                    if (!filteredAuxListCombustible.contains(g)) {
                        filteredAuxListCombustible.add(g);
                    }
                }
            }
            if (GasNatCompr) {
                if (!g.getPrecioGasNaturalComprimido().equals("")) {
                    if (!filteredAuxListCombustible.contains(g)) {
                        filteredAuxListCombustible.add(g);
                    }
                }
            }
            if (GasNatLic) {
                if (g.getPrecioGasNaturalLicuado().equals("")) {
                    if (!filteredAuxListCombustible.contains(g)) {
                        filteredAuxListCombustible.add(g);
                    }
                }
            }
            if (GasLicPetr) {
                if (g.getPrecioGasesLicuadosDelPetrLeo().equals("")) {
                    if (!filteredAuxListCombustible.contains(g)) {
                        filteredAuxListCombustible.add(g);
                    }
                }
            }
            if (!(GasoleoA || GasoleoB || GasoleoPremium || Gasolina95E10 || Gasolina95E5 || Gasolina95E5Premium || Gasolina98E10 || Gasolina98E5 || Biodiesel || Hidrogeno || Bioetanol || GasNatCompr || GasNatLic || GasLicPetr)){
                filteredAuxListCombustible.add(g);
            }
        }
        return filteredAuxListCombustible;
    }

    //Método que según el rótulo, asocia una imagen distinta para cada caso retornando el id de dicha imagen
    public int creaImagen(String rotulo){
        if (rotulo == "" ){
            return -1;
        }
        //Si contiene BP entonces se retorna la imagen correspondiente
        if (rotulo.contains("BP")){
            return R.drawable.bp;
        }
        //En caso contrario
        else{
            //compruebo que contenga galp y procedo a hacer lo mismo
            if (rotulo.contains("GALP")){
                return R.drawable.galp;
            }
            else{
                //si tampoco contiene galp realizo un switch y en funcion de la cadena retorno una imagen u otra de acorde al rotulo
                switch (rotulo){
                    case "CEPSA":
                        return R.drawable.cepsa;
                    case "REPSOL":
                        return R.drawable.repsol;
                    case "PETRONOR":
                        return R.drawable.petronor;
                    case "CAMPSA":
                        return R.drawable.campsa;
                    case "AVIA":
                        return R.drawable.avia;
                    case "EROSKI":
                        return R.drawable.eroski;
                    case "CARREFOUR":
                        return R.drawable.carrefour;
                    case "ALCAMPO":
                        return R.drawable.alcampo;
                    case "SHELL":
                        return R.drawable.shell;
                    case "SARAS":
                        return R.drawable.saras;
                    case "NoDato":
                        return R.drawable.alerta;
                    default:
                        return R.drawable.gasolinera;
                }
            }
        }
    }

}

