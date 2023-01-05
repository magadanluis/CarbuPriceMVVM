package es.uniovi.carbupricemvvm.View.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import es.uniovi.carbupricemvvm.R;

public class FilterFragment extends Fragment {

    //Variables a usar, en este caso todos los checkboxes
    private CheckBox cbGijon;
    private CheckBox cbOviedo;
    private CheckBox cbAviles;
    private CheckBox cbRepsol;
    private CheckBox cbCepsa;
    private CheckBox cbShell;
    private CheckBox cbBP;
    private CheckBox cbPetronor;
    private CheckBox cbCampsa;
    private CheckBox cbGalp;
    private CheckBox cbGasoleoA;
    private CheckBox cbGasoleoB;
    private CheckBox cbGasoleoPremium;
    private CheckBox cbGasolina95E10;
    private CheckBox cbGasolina95E5;
    private CheckBox cbGasolina95E5Premium;
    private CheckBox cbGasolina98E10;
    private CheckBox cbGasolina98E5;
    private CheckBox cbBiodiesel;
    private CheckBox cbHidrogeno;
    private CheckBox cbBioetanol;
    private CheckBox cbGasNatCompr;
    private CheckBox cbGasNatLic;
    private CheckBox cbGasLicPetr;

    public FilterFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflo la vista con el fragmento
        View rootView = inflater.inflate(R.layout.filter_fragment, container, false);
        setHasOptionsMenu(true);

        // Saco la referencia a los checkboxes
        cbGijon = rootView.findViewById(R.id.cbGijon);
        cbOviedo = rootView.findViewById(R.id.cbOviedo);
        cbAviles = rootView.findViewById(R.id.cbAviles);
        cbRepsol = rootView.findViewById(R.id.cbRepsol);
        cbCepsa = rootView.findViewById(R.id.cbCepsa);
        cbShell = rootView.findViewById(R.id.cbShell);
        cbBP = rootView.findViewById(R.id.cbBP);
        cbPetronor = rootView.findViewById(R.id.cbPetronor);
        cbCampsa = rootView.findViewById(R.id.cbCampsa);
        cbGalp = rootView.findViewById(R.id.cbGalp);
        cbGasoleoA = rootView.findViewById(R.id.cbGasoleoA);
        cbGasoleoB = rootView.findViewById(R.id.cbGasoleoB);
        cbGasoleoPremium = rootView.findViewById(R.id.cbGasoleoPremium);
        cbGasoleoPremium = rootView.findViewById(R.id.cbGasoleoPremium);
        cbGasolina95E10 = rootView.findViewById(R.id.cbGasolina95E10);
        cbGasolina95E5 = rootView.findViewById(R.id.cbGasolina95E5);
        cbGasolina95E5Premium = rootView.findViewById(R.id.cbGasolina95E5Premium);
        cbGasolina98E10 = rootView.findViewById(R.id.cbGasolina98E10);
        cbGasolina98E5 = rootView.findViewById(R.id.cbGasolina98E5);
        cbBiodiesel = rootView.findViewById(R.id.cbBiodiesel);
        cbHidrogeno = rootView.findViewById(R.id.cbHidrogeno);
        cbBioetanol = rootView.findViewById(R.id.cbBioetanol);
        cbGasNatCompr = rootView.findViewById(R.id.cbGasNatCompr) ;
        cbGasNatLic = rootView.findViewById(R.id.cbGasNatLic);
        cbGasLicPetr = rootView.findViewById(R.id.cbGasLicPetr);

        // Modifico los checkboxes en funcion de las preferencias almacenadas
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        cbGijon.setChecked(preferences.getBoolean("cbGijon", false));
        cbOviedo.setChecked(preferences.getBoolean("cbOviedo", false));
        cbAviles.setChecked(preferences.getBoolean("cbAviles", false));
        cbRepsol.setChecked(preferences.getBoolean("cbRepsol", false));
        cbCepsa.setChecked(preferences.getBoolean("cbCepsa", false));
        cbShell.setChecked(preferences.getBoolean("cbShell", false));
        cbBP.setChecked(preferences.getBoolean("cbBP", false));
        cbPetronor.setChecked(preferences.getBoolean("cbPetronor", false));
        cbCampsa.setChecked(preferences.getBoolean("cbCampsa", false));
        cbGalp.setChecked(preferences.getBoolean("cbGalp", false));
        cbGasoleoA.setChecked(preferences.getBoolean("cbGasoleoA", false));;
        cbGasoleoB.setChecked(preferences.getBoolean("cbGasoleoB", false));;
        cbGasoleoPremium.setChecked(preferences.getBoolean("cbGasoleoPremium", false));
        cbGasolina95E10.setChecked(preferences.getBoolean("cbGasolina95E10", false));
        cbGasolina95E5.setChecked(preferences.getBoolean("cbGasolina95E5",false));
        cbGasolina95E5Premium.setChecked(preferences.getBoolean("cbGasolina95E5Premium", false));
        cbGasolina98E10.setChecked(preferences.getBoolean("cbGasolina98E10", false));
        cbGasolina98E5.setChecked(preferences.getBoolean("cbGasolina98E5",false));
        cbBiodiesel.setChecked(preferences.getBoolean("cbBiodiesel", false));;
        cbHidrogeno.setChecked(preferences.getBoolean("cbHidrogeno", false));
        cbBioetanol.setChecked(preferences.getBoolean("cbBioetanol", false));;
        cbGasNatCompr.setChecked(preferences.getBoolean("cbGasNatCompr", false));;
        cbGasNatLic.setChecked(preferences.getBoolean("cbGasNatLic", false));;
        cbGasLicPetr.setChecked(preferences.getBoolean("cbGasLicPetr", false));;
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        //Cada vez que se ejecute onPause guardo en preferencias los valores marcados de los checboxes
        SharedPreferences pref = (SharedPreferences) PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor e = pref.edit();
        e.putBoolean("cbGijon",cbGijon.isChecked());
        e.putBoolean("cbOviedo", cbOviedo.isChecked());
        e.putBoolean("cbAviles", cbAviles.isChecked());
        e.putBoolean("cbRepsol", cbRepsol.isChecked());
        e.putBoolean("cbCepsa", cbCepsa.isChecked());
        e.putBoolean("cbShell",cbShell.isChecked());
        e.putBoolean("cbBP", cbBP.isChecked());
        e.putBoolean("cbPetronor", cbPetronor.isChecked());
        e.putBoolean("cbCampsa", cbCampsa.isChecked());
        e.putBoolean("cbGalp", cbGalp.isChecked());
        e.putBoolean("cbGasoleoA", cbGasoleoA.isChecked());
        e.putBoolean("cbGasoleoB", cbGasoleoB.isChecked());
        e.putBoolean("cbGasoleoPremium", cbGasoleoPremium.isChecked());
        e.putBoolean("cbGasolina95E10", cbGasolina95E10.isChecked());
        e.putBoolean("cbGasolina95E5", cbGasolina95E5.isChecked());
        e.putBoolean("cbGasolina95E5Premium", cbGasolina95E5Premium.isChecked());
        e.putBoolean("cbGasolina98E10", cbGasolina98E10.isChecked());
        e.putBoolean("cbGasolina98E5", cbGasolina98E5.isChecked());
        e.putBoolean("cbBiodiesel", cbBiodiesel.isChecked());
        e.putBoolean("cbHidrogeno", cbHidrogeno.isChecked());
        e.putBoolean("cbBioetanol", cbBioetanol.isChecked());
        e.putBoolean("cbGasNatCompr", cbGasNatCompr.isChecked());
        e.putBoolean("cbGasNatLic", cbGasNatLic.isChecked());
        e.putBoolean("cbGasLicPetr", cbGasLicPetr.isChecked());
        e.commit();
    }
}
