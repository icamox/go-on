package worldprogramming.myapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import worldprogramming.myapp.Amigos.ActivityAmigos;
import worldprogramming.myapp.Mensajes.Mensajeria;

public class Login extends AppCompatActivity {

    private EditText etuser;
    private EditText etpass;
    private Button btingresar;
    private Button registro;

    private RadioButton RBsesion;

    private static final String IP = "http://sergiomyapp.000webhostapp.com/ArchivosPHP/Login_GETID.php?id=";
    private static final String IP_TOKEN = "https://sergiomyapp.000webhostapp.com/ArchivosPHP/Token_INSERTandUPDATE.php";

    private VolleyRP volley;
    private RequestQueue mRequest;

    private String USER = "";
    private String PASSWORD="";

    private boolean isActivateRadioButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (Preferences.obtenerPreferenceBoolean(this, Preferences.PREFENCE_ESTADO_BUTTON_SESION)){
            iniciarActividadSiguiente();
        }

        volley = VolleyRP.getInstance(this);
        mRequest = volley.getRequestQueue();

        etuser = (EditText)findViewById(R.id.etuser);
        etpass = (EditText)findViewById(R.id.etpass);

        btingresar = (Button)findViewById(R.id.btingresar);
        registro = (Button)findViewById(R.id.registrar);

        RBsesion = (RadioButton)findViewById(R.id.RBSesion);

        isActivateRadioButton = RBsesion.isChecked();//Desactivado

        RBsesion.setOnClickListener(new View.OnClickListener() {
            //Activado
            @Override
            public void onClick(View v) {
                if (isActivateRadioButton){
                    RBsesion.setChecked(false);
                }
                isActivateRadioButton = RBsesion.isChecked();
            }
        });

        btingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerificarLogin(etuser.getText().toString().toLowerCase(),etpass.getText().toString().toLowerCase());
            }
        });

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this,Registro.class);
                startActivity(i);
            }
        });
    }


    public void VerificarLogin(String user, String pass){
        USER = user;
        PASSWORD = pass;
        SolicitudJSON(IP+user);
    }

    public void SolicitudJSON(String URL){
        JsonObjectRequest solicitud = new JsonObjectRequest(URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject datos) { VerificarPass(datos); }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this,"Ocurrió un error, por favor revise su conexión a internet",Toast.LENGTH_SHORT).show();
            }
        });
        VolleyRP.addToQueue(solicitud, mRequest, this, volley);
    }

    public void VerificarPass(JSONObject datos){
        try {
            String estado = datos.getString("resultado");
            if(estado.equals("CC")){
                JSONObject Jsondatos = new JSONObject(datos.getString("datos"));
                String usuario = Jsondatos.getString("id").toLowerCase();
                String contra = Jsondatos.getString("Password").toLowerCase();
                if (usuario.equals(USER) && contra.equals(PASSWORD)){
                    String Token = FirebaseInstanceId.getInstance().getToken();
                    if(Token!=null) SubirToken(Token);
                    else Toast.makeText(this, "El token es nulo",Toast.LENGTH_SHORT).show();
                }else Toast.makeText(this, "Incorrect User or Password",Toast.LENGTH_SHORT).show();
                //Transportar a otra actividad
            }
        } catch (JSONException e) {}
    }

    private void SubirToken(String token){
        HashMap<String,String> hasMapToken = new HashMap<>();
        hasMapToken.put("id",USER);
        hasMapToken.put("token",token);

        JsonObjectRequest solicitud = new JsonObjectRequest(Request.Method.POST, IP_TOKEN, new JSONObject(hasMapToken), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject datos) {
                Preferences.savePreferencesBoolean(Login.this, RBsesion.isChecked(), Preferences.PREFENCE_ESTADO_BUTTON_SESION);
                Preferences.savePreferenceString(Login.this, USER, Preferences.PREFERENCE_USUARIO_LOGIN);
                try {
                    Toast.makeText(Login.this, datos.getString("resultado"),Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {}
                iniciarActividadSiguiente();
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
               Toast.makeText(Login.this,"El token no se pudo subir a la base de datos",Toast.LENGTH_SHORT).show();
            }
        });
        VolleyRP.addToQueue(solicitud, mRequest, this, volley);
    }

    public void iniciarActividadSiguiente(){
        Intent i = new Intent(Login.this, ActivityAmigos.class);
        startActivity(i);
        finish();
    }
}
