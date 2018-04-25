package ib.ganz.eltasqo.fragment;

import android.os.Bundle;

/**
 * Created by limakali on 4/25/2018.
 */

public class GeneralFragment extends BaseFragment
{
    public static GeneralFragment create(int kind)
    {
        Bundle b = new Bundle();
        b.putInt("kind", kind);

        GeneralFragment g = new GeneralFragment();
        g.setArguments(b);

        return g;
    }
}
