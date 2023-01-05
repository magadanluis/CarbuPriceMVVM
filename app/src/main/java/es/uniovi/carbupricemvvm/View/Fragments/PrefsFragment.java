package es.uniovi.carbupricemvvm.View.Fragments;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import es.uniovi.carbupricemvvm.R;

public class PrefsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        //Añado las preferencias del xml correspondiente
        addPreferencesFromResource(R.xml.preferences);
        //En caso de no ser superior a la API 25 elimmino la preferencia de modo Noche por que no funciona de forma correcta
        //Añado un changeListener a la preferencia correspondiente al modo noche
        Preference modoNoche = findPreference("modoNoche");
        if (Build.VERSION.SDK_INT <Build.VERSION_CODES.N_MR1){
            modoNoche.setVisible(false);
        }
        else {
            modoNoche.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
                    SharedPreferences.Editor prefsEditor = prefs.edit();
                    prefsEditor.putBoolean(preference.getKey(), (boolean) newValue);
                    prefsEditor.apply();
                    //Recreo la actividad cada vez que se modifica la preferencia
                    getActivity().recreate();
                    return false;
                }
            });
        }
    }
}
