package ib.ganz.eltasqo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ib.ganz.eltasqo.R;

/**
 * Created by limakali on 4/25/2018.
 */

public class BaseFragment extends Fragment
{
    public static final int GENERAL = 0;
    public static final int ONLYYOU = 1;

    @BindView(R.id.lvTask)  ListView lvTask;
    @BindView(R.id.btnAdd)  FloatingActionButton btnAdd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_base, container, false);
        ButterKnife.bind(this, v);



        return v;
    }
}
