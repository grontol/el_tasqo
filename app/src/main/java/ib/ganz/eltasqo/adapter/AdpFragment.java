package ib.ganz.eltasqo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by limakali on 4/25/2018.
 */

public class AdpFragment extends FragmentPagerAdapter
{
    Fragment[] arf;
    String[] ars;

    public AdpFragment(FragmentManager fm, Fragment[] arf, String[] ars)
    {
        super(fm);

        this.arf = arf;
        this.ars = ars;
    }

    @Override
    public Fragment getItem(int position)
    {
        return arf[position];
    }

    @Override
    public int getCount()
    {
        return arf.length;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return ars[position];
    }
}
