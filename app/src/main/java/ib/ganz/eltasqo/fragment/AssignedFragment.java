package ib.ganz.eltasqo.fragment;

import android.os.Bundle;

/**
 * Created by limakali on 4/25/2018.
 */

public class AssignedFragment extends BaseFragment
{
    public static AssignedFragment create(int kind)
    {
        Bundle b = new Bundle();
        b.putInt("kind", kind);

        AssignedFragment g = new AssignedFragment();
        g.setArguments(b);

        return g;
    }
}
