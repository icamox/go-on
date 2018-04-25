package worldprogramming.myapp.ActividadDeUsuarios;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import worldprogramming.myapp.R;

public class Fragment_1 extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login, container, false);
        Toast.makeText(getContext(), "Estoy en el Fragmento #1", Toast.LENGTH_SHORT).show();
        return view;
    }
}
