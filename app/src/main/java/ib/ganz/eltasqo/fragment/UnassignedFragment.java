package ib.ganz.eltasqo.fragment;

import android.os.Bundle;

/**
 * Created by limakali on 4/25/2018.
 */

public class UnassignedFragment extends BaseFragment
{
    public static UnassignedFragment create(int kind)
    {
        Bundle b = new Bundle();
        b.putInt("kind", kind);

        UnassignedFragment g = new UnassignedFragment();
        g.setArguments(b);

        return g;
    }
}
