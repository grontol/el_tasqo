package ib.ganz.eltasqo.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ib.ganz.eltasqo.R;
import ib.ganz.eltasqo.dataclass.BagianData;
import ib.ganz.eltasqo.helper.Gxon;
import ib.ganz.eltasqo.service.Servize;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by limakali on 4/25/2018.
 */

public class ListBagianDialog extends DialogFragment
{
    @BindView(R.id.lvItemDialog)    ListView lvItemDialog;
    @BindView(R.id.ctrLoading)      View ctrLoading;

    CompositeDisposable c;
    List<BagianData> l;
    ArrayAdapter<BagianData> adp;

    public static <T> ListBagianDialog create(List<BagianData> l)
    {
        Bundle b = new Bundle();
        b.putString("l", Gxon.toJsonArray(l));

        ListBagianDialog d = new ListBagianDialog();
        d.setArguments(b);

        return d;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        c = new CompositeDisposable();
        l = Gxon.fromJsonArray(getArguments().getString("l"), BagianData.class);
        adp = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, l);

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_list, null);
        ButterKnife.bind(this, v);

        lvItemDialog.setAdapter(adp);

        if (l.isEmpty())
        {
            ctrLoading.setVisibility(View.VISIBLE);
            c.add(Servize.getBagian().subscribe(r ->
            {
                ctrLoading.setVisibility(View.GONE);
                l.clear();
                l.addAll(r);
                adp.notifyDataSetChanged();
            }, e ->
            {
                ctrLoading.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Eiiittss, koneksi anda periksa dulu!", Toast.LENGTH_SHORT).show();
            }));
        }
        else
        {
            ctrLoading.setVisibility(View.GONE);
        }

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .create();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();

        if (c != null && !c.isDisposed())
        {
            c.dispose();
        }
    }

    public void show(FragmentManager manager, OnSelectedBagian o)
    {
        super.show(manager, "u");
        lvItemDialog.setOnItemClickListener((av, v, i, lo) -> o.onSelected(l.get(i)));
    }

    public interface OnSelectedBagian
    {
        void onSelected(BagianData u);
    }
}
