package worldprogramming.myapp.ActividadDeUsuarios;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class AdapterUsuarios extends FragmentPagerAdapter{


    public AdapterUsuarios(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position==0){
            return new Fragment_1();
        }else if (position==1){
            return new Fragment_2();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
