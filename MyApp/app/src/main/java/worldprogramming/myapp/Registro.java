package worldprogramming.myapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Registro extends AppCompatActivity{

    private static final String IP_REGISTRAR = "https://sergiomyapp.000webhostapp.com/ArchivosPHP/Registro_INSERT.php";

    private EditText user;
    private EditText password;
    private EditText nombre;
    private EditText apellido;
    private EditText correo;
    private EditText dia;
    private EditText mes;
    private EditText year;
    private EditText celular;
    private RadioButton RBHombre;
    private RadioButton RBMujer;
    private Button registro;

    private VolleyRP volley;
    private RequestQueue mRequest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        volley = VolleyRP.getInstance(this);
        mRequest = volley.getRequestQueue();

        user = (EditText)findViewById(R.id.userRegister);
        password = (EditText)findViewById(R.id.PasswordRegistro);
        nombre = (EditText)findViewById(R.id.NombreRegistro);
        apellido = (EditText)findViewById(R.id.ApellidoRegistro);
        dia = (EditText)findViewById(R.id.DiaRegistro);
        mes = (EditText)findViewById(R.id.MesRegistro);
        year = (EditText)findViewById(R.id.YearRegistro);
        correo = (EditText)findViewById(R.id.CorreoRegistro);
        celular = (EditText)findViewById(R.id.YearRegistro);
        RBHombre = (RadioButton)findViewById(R.id.RBHombre);
        RBMujer = (RadioButton)findViewById(R.id.RBMujer);

        RBHombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RBMujer.setChecked(false);
            }
        });
        RBMujer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RBHombre.setChecked(false);
            }
        });

        registro = (Button)findViewById(R.id.BotonRegistro);

        registro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String genero="";
                if(RBHombre.isChecked()) genero = "Hombre";
                if(RBMujer.isChecked()) genero = "Mujer";

                RegistrarWebService(
                        getStringET(user).trim(),
                        getStringET(password).trim(),
                        getStringET(nombre),
                        getStringET(apellido).trim(),
                        getStringET(dia).trim()+"/"+getStringET(mes).trim()+"/"+getStringET(year).trim(),
                        getStringET(correo).trim(),
                        getStringET(celular).trim(),
                        genero);
            }
        });
    }


    private void RegistrarWebService(String usuario, String contra, String nombre, String apellido, String fechaNacimiento, String correo, String numero, String genero) {


        if (!usuario.isEmpty() &&
                !contra.isEmpty() &&
                !nombre.isEmpty() &&
                !apellido.isEmpty() &&
                !fechaNacimiento.isEmpty() &&
                !correo.isEmpty() &&
                !numero.isEmpty() &&
                !genero.isEmpty()) {


                HashMap<String, String> hasMapToken = new HashMap<>();
                hasMapToken.put("id", usuario);
                hasMapToken.put("password", contra);
                hasMapToken.put("nombre", nombre);
                hasMapToken.put("apellido", apellido);
                hasMapToken.put("fecha", fechaNacimiento);
                hasMapToken.put("correo", correo);
                hasMapToken.put("telefono", numero);
                hasMapToken.put("genero", genero);

                JsonObjectRequest solicitud = new JsonObjectRequest(Request.Method.POST, IP_REGISTRAR, new JSONObject(hasMapToken), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject datos) {
                        try {
                            String estado = datos.getString("resultado");
                            if (estado.equalsIgnoreCase("El usuario se registró correctamente.")) {
                                Toast.makeText(Registro.this, estado, Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(Registro.this, estado, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(Registro.this, "No se pudo registrar al usuario", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(Registro.this, "", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Registro.this, "No se pudo registrar al usuario", Toast.LENGTH_SHORT).show();
                    }
                });
                VolleyRP.addToQueue(solicitud, mRequest, this, volley);
            }else{
                Toast.makeText(this, "Llenar los campos vacios", Toast.LENGTH_SHORT).show();
            }
        }


    private String getStringET(EditText e){
        return e.getText().toString();
    }
}
