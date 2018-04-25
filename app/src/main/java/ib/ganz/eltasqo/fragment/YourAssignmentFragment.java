package ib.ganz.eltasqo.fragment;

import android.os.Bundle;

/**
 * Created by limakali on 4/25/2018.
 */

public class YourAssignmentFragment extends BaseFragment
{
    public static YourAssignmentFragment create(int kind)
    {
        Bundle b = new Bundle();
        b.putInt("kind", kind);

        YourAssignmentFragment o = new YourAssignmentFragment();
        o.setArguments(b);

        return o;
    }
}
