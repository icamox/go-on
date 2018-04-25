package worldprogramming.myapp.Amigos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import worldprogramming.myapp.Login;
import worldprogramming.myapp.Mensajes.Mensajeria;
import worldprogramming.myapp.Preferences;
import worldprogramming.myapp.R;
import worldprogramming.myapp.VolleyRP;

public class ActivityAmigos extends AppCompatActivity{

    private RecyclerView rv;
    private List<AmigosAtributos>atributosList;
    private AmigosAdapter adapter;

    private VolleyRP volley;
    private RequestQueue mRequest;

    private static final String URL_GET_ALL_USUARIOS = "https://sergiomyapp.000webhostapp.com/ArchivosPHP/Amigos_GETALL.php";


        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_amigos);
            setTitle("Lista De Amigos");

            volley = VolleyRP.getInstance(this);
            mRequest = volley.getRequestQueue();

            atributosList = new ArrayList<>();

            rv=(RecyclerView)findViewById(R.id.amigosRecyclerView);
            LinearLayoutManager lm = new LinearLayoutManager(this);
            rv.setLayoutManager(lm);

            adapter = new AmigosAdapter(atributosList, this);
            rv.setAdapter(adapter);



            SolicitudJSON();
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_amigos, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id==R.id.CerrarSesionMenu){
            Preferences.savePreferencesBoolean(ActivityAmigos.this, false, Preferences.PREFENCE_ESTADO_BUTTON_SESION);
            Intent i = new Intent(ActivityAmigos.this,Login.class);
            startActivity(i);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void agregarAmigo(int fotoDePerfil, String nombre, String ultimoMensaje, String hora, String id){
            AmigosAtributos amigosAtributos = new AmigosAtributos();
            amigosAtributos.setFotoDePerfil(fotoDePerfil);
            amigosAtributos.setNombre(nombre);
            amigosAtributos.setUltimoMensaje(ultimoMensaje);
            amigosAtributos.setHora(hora);
            amigosAtributos.setId(id);
            atributosList.add(amigosAtributos);
            adapter.notifyDataSetChanged();
        }

    public void SolicitudJSON(){
        JsonObjectRequest solicitud = new JsonObjectRequest(URL_GET_ALL_USUARIOS, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject datos) {
                try {
                    String TodosLosDatos = datos.getString("resultado");
                    String TodosLosUsuariosQeuTienenToken = datos.getString("usuariosConTOkens");
                    JSONArray jsonArray = new JSONArray(TodosLosDatos);
                    JSONArray jsUserTokens = new JSONArray(TodosLosUsuariosQeuTienenToken);
                    String NuestroUsuario = Preferences.obtenerPreferenceString(ActivityAmigos.this, Preferences.PREFERENCE_USUARIO_LOGIN);
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject js = jsonArray.getJSONObject(i);
                        if (!NuestroUsuario.equals(js.getString("id"))){
                            for (int j=0;j<jsUserTokens.length();j++){
                                JSONObject UsuarioConToken = jsUserTokens.getJSONObject(j);
                                if (js.getString("id").equals(UsuarioConToken.getString("id"))){
                                    agregarAmigo(R.drawable.notification2, js.getString("nombre"), "Mensaje "+i, "00:00", js.getString("id"));
                                }
                            }
                        }
                    }
                } catch (JSONException e) {
                    Toast.makeText(ActivityAmigos.this,"Ocurrió un error al descomponer el JSON",Toast.LENGTH_SHORT).show();
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ActivityAmigos.this,"Ocurrió un error, por favor contacte al administrador",Toast.LENGTH_SHORT).show();
            }
        });
        VolleyRP.addToQueue(solicitud, mRequest, this, volley);
    }

    }
