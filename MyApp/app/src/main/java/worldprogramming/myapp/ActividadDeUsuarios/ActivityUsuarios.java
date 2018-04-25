package worldprogramming.myapp.ActividadDeUsuarios;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import worldprogramming.myapp.R;

public class ActivityUsuarios extends AppCompatActivity{

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
        tabLayout = (TabLayout)findViewById(R.id.tabLayoutUsuarios);
        viewPager = (ViewPager)findViewById(R.id.viewPagerUsuarios);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(new AdapterUsuarios(getSupportFragmentManager()));
    }
}
