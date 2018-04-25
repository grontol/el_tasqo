package ib.ganz.eltasqo.helper;

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
import ib.ganz.eltasqo.dataclass.UserData;
import ib.ganz.eltasqo.service.Servize;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by limakali on 4/21/2018.
 */

public class ListUserDialog extends DialogFragment
{
    @BindView(R.id.lvItemDialog)    ListView lvItemDialog;
    @BindView(R.id.ctrLoading)      View ctrLoading;

    CompositeDisposable c;
    List<UserData> l;
    ArrayAdapter<UserData> adp;

    public static <T> ListUserDialog create(List<UserData> l)
    {
        Bundle b = new Bundle();
        b.putString("l", Gxon.toJsonArray(l));

        ListUserDialog d = new ListUserDialog();
        d.setArguments(b);

        return d;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        c = new CompositeDisposable();
        l = Gxon.fromJsonArray(getArguments().getString("l"), UserData.class);
        adp = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, l);

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_list, null);
        ButterKnife.bind(this, v);

        lvItemDialog.setAdapter(adp);

        if (l.isEmpty())
        {
            ctrLoading.setVisibility(View.VISIBLE);
            c.add(Servize.getUser().subscribe(r ->
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

    public void show(FragmentManager manager, OnSelectedUser o)
    {
        super.show(manager, "u");
        lvItemDialog.setOnItemClickListener((av, v, i, lo) -> o.onSelected(l.get(i)));
    }

    public interface OnSelectedUser
    {
        void onSelected(UserData u);
    }
}
