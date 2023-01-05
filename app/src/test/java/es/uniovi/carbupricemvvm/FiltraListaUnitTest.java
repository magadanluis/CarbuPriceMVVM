package es.uniovi.carbupricemvvm;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import es.uniovi.carbupricemvvm.Controller.logicFunctions;
import es.uniovi.carbupricemvvm.data.utilities.ListaEESSPrecio;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class FiltraListaUnitTest {

    private logicFunctions logic;
    private List<ListaEESSPrecio> L2;

    @Before
    public void SetUp() {
        logic = new logicFunctions();
        L2 = new ArrayList<>();
        ListaEESSPrecio l1 = new ListaEESSPrecio();
        ListaEESSPrecio l2 = new ListaEESSPrecio();
        ListaEESSPrecio l3 = new ListaEESSPrecio();
        ListaEESSPrecio l4 = new ListaEESSPrecio();
        ListaEESSPrecio l5 = new ListaEESSPrecio();
        l1.setMunicipio("Gijón");
        l1.setRTulo("CEPSA");
        L2.add(l1);
        l2.setMunicipio("Oviedo");
        l2.setRTulo("REPSOL");
        L2.add(l2);
        l3.setMunicipio("Oviedo");
        l3.setRTulo("SHELL");
        L2.add(l3);
        l4.setMunicipio("Avilés");
        l4.setRTulo("REPSOL");
        L2.add(l4);
        l5.setMunicipio("Oviedo");
        l5.setRTulo("REPSOL");
        L2.add(l5);
        System.out.println("EJEMPLOS PRECARGADOS DE GASOLINERAS CON DIFERENTE ROTULO Y MUNICIPIO");
        for (int i=0; i< L2.size(); i++){
            System.out.println("Gasolinera "+ i +":" + L2.get(i).getRTulo() + " " + L2.get(i).getMunicipio());
        }
    }

    @Test
    public void filtraOviedoRepsol() {
        //B es un array booleano que corresponde a si hay que filtrar por (Gijon, Oviedo, Aviles, Repsol, Cepsa, Shell, BP, Petronor, Campsa, Galp)
        List<Boolean> b = new ArrayList<>();
        b.add(false);
        b.add(true);
        b.add(false);
        b.add(true);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        List<ListaEESSPrecio> res = logic.FiltraLista(L2, b);
        assertEquals(2, res.size());
    }

    @Test
    public void filtraOviedo() {
        //B es un array booleano que corresponde a si hay que filtrar por (Gijon, Oviedo, Aviles, Repsol, Cepsa, Shell, BP, Petronor, Campsa, Galp)
        List<Boolean> b = new ArrayList<>();
        b.add(false);
        b.add(true);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        List<ListaEESSPrecio> res = logic.FiltraLista(L2, b);
        assertEquals(3, res.size());
    }

    @Test
    public void filtraGijonOviedo() {
        //B es un array booleano que corresponde a si hay que filtrar por (Gijon, Oviedo, Aviles, Repsol, Cepsa, Shell, BP, Petronor, Campsa, Galp)
        List<Boolean> b = new ArrayList<>();
        b.add(true);
        b.add(true);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        List<ListaEESSPrecio> res = logic.FiltraLista(L2, b);
        assertEquals(4, res.size());
    }

    @Test
    public void sinFiltro() {
        //B es un array booleano que corresponde a si hay que filtrar por (Gijon, Oviedo, Aviles, Repsol, Cepsa, Shell, BP, Petronor, Campsa, Galp)
        List<Boolean> b = new ArrayList<>();
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        List<ListaEESSPrecio> res = logic.FiltraLista(L2, b);
        assertEquals(5, res.size());
    }

    @Test
    public void filtraRepsolCepsaGijon() {
        //B es un array booleano que corresponde a si hay que filtrar por (Gijon, Oviedo, Aviles, Repsol, Cepsa, Shell, BP, Petronor, Campsa, Galp)
        List<Boolean> b = new ArrayList<>();
        b.add(true);
        b.add(false);
        b.add(false);
        b.add(true);
        b.add(true);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        List<ListaEESSPrecio> res = logic.FiltraLista(L2, b);
        assertEquals(1, res.size());
    }

    @Test
    public void filtraAvilesBP() {
        //B es un array booleano que corresponde a si hay que filtrar por (Gijon, Oviedo, Aviles, Repsol, Cepsa, Shell, BP, Petronor, Campsa, Galp)
        List<Boolean> b = new ArrayList<>();
        b.add(false);
        b.add(false);
        b.add(true);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(true);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        List<ListaEESSPrecio> res = logic.FiltraLista(L2, b);
        assertEquals(0, res.size());
    }

    @Test
    public void filtraAviles() {
        //B es un array booleano que corresponde a si hay que filtrar por (Gijon, Oviedo, Aviles, Repsol, Cepsa, Shell, BP, Petronor, Campsa, Galp)
        List<Boolean> b = new ArrayList<>();
        b.add(false);
        b.add(false);
        b.add(true);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        b.add(false);
        List<ListaEESSPrecio> res = logic.FiltraLista(L2, b);
        assertEquals(1, res.size());
    }
}

