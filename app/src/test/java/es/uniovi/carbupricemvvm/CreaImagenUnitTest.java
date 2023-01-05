package es.uniovi.carbupricemvvm;

import org.junit.Before;
import org.junit.Test;
import es.uniovi.carbupricemvvm.View.Adapter.ListaPreciosViewAdapter;
import es.uniovi.carbupricemvvm.Controller.logicFunctions;
import static org.junit.Assert.assertEquals;

public class CreaImagenUnitTest {

    private logicFunctions logic;
    private ListaPreciosViewAdapter.ListaPreciosHolder lpHolder;

    @Before
    public void SetUp() {
        logic = new logicFunctions();
    }

    @Test
    public void creaImagenGalpTest(){
        assertEquals(R.drawable.galp, logic.creaImagen("GALP Gijon"));
    }

    @Test
    public void creaImagenBPTest(){
        assertEquals(R.drawable.bp, logic.creaImagen("BP Gijon"));
    }

    @Test
    public void creaImagenRepsolTest(){
        assertEquals(R.drawable.repsol, logic.creaImagen("REPSOL"));
    }

    @Test
    public void creaImagenCepsaTest(){
        assertEquals(R.drawable.cepsa, logic.creaImagen("CEPSA"));
    }

    @Test
    public void creaImagenPetronorTest(){
        assertEquals(R.drawable.petronor, logic.creaImagen("PETRONOR"));
    }

    @Test
    public void creaImagenCampsaTest(){
        assertEquals(R.drawable.campsa, logic.creaImagen("CAMPSA"));
    }

    @Test
    public void creaImagenAVIATest(){
        assertEquals(R.drawable.avia, logic.creaImagen("AVIA"));
    }

    @Test
    public void creaImagenEroskiTest(){
        assertEquals(R.drawable.eroski, logic.creaImagen("EROSKI"));
    }

    @Test
    public void creaImagenCarrefourTest(){
        assertEquals(R.drawable.carrefour, logic.creaImagen("CARREFOUR"));
    }

    @Test
    public void creaImagenAlcampoTest(){
        assertEquals(R.drawable.alcampo, logic.creaImagen("ALCAMPO"));
    }

    @Test
    public void creaImagenShellTest(){
        assertEquals(R.drawable.shell, logic.creaImagen("SHELL"));
    }

    @Test
    public void creaImagenSarasTest(){
        assertEquals(R.drawable.saras, logic.creaImagen("SARAS"));
    }

    @Test
    public void creaImagenFalloConexionTest(){
        assertEquals(R.drawable.alerta, logic.creaImagen("NoDato"));
    }

    @Test
    public void creaImagenGasolineraMarcaPocoConocida(){
        assertEquals(R.drawable.gasolinera, logic.creaImagen("EMPRESOIL"));
    }

    @Test
    public void creaImagenCadenaVacia(){
        assertEquals(-1, logic.creaImagen(""));
    }

}

