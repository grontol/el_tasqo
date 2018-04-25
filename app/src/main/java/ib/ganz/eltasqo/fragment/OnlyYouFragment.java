package ib.ganz.eltasqo.fragment;

import android.os.Bundle;

/**
 * Created by limakali on 4/25/2018.
 */

public class OnlyYouFragment extends BaseFragment
{
    public static OnlyYouFragment create(int kind)
    {
        Bundle b = new Bundle();
        b.putInt("kind", kind);

        OnlyYouFragment o = new OnlyYouFragment();
        o.setArguments(b);

        return o;
    }
}
