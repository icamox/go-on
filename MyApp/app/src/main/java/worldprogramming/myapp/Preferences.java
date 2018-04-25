package worldprogramming.myapp;

import android.content.Context;
import android.content.SharedPreferences;

    public class Preferences {

        public static final String STRING_PREFENCERS = "myapp.Mensajes.Mensajeria";
        public static final String PREFENCE_ESTADO_BUTTON_SESION = "estado.buton.sesion";
        public static final String PREFERENCE_USUARIO_LOGIN = "usuario.login";

        public static void savePreferencesBoolean(Context c, boolean b, String key){
            SharedPreferences preferences = c.getSharedPreferences(STRING_PREFENCERS,c.MODE_PRIVATE);
            preferences.edit().putBoolean(key,b).apply();
        }

        public static void savePreferenceString(Context c, String b, String key){
            SharedPreferences preferences = c.getSharedPreferences(STRING_PREFENCERS,c.MODE_PRIVATE);
            preferences.edit().putString(key,b).apply();
        }

        public static boolean obtenerPreferenceBoolean(Context c, String key){
            SharedPreferences preferences = c.getSharedPreferences(STRING_PREFENCERS,c.MODE_PRIVATE);
            return preferences.getBoolean(key, false);
        }

        public static String obtenerPreferenceString(Context c, String key){
            SharedPreferences preferences = c.getSharedPreferences(STRING_PREFENCERS,c.MODE_PRIVATE);
            return preferences.getString(key, "");
        }

    }
