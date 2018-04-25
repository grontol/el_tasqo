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
import ib.ganz.eltasqo.dataclass.UserData;
import ib.ganz.eltasqo.helper.Gxon;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by limakali on 4/21/2018.
 */

public class ListDialog<T> extends DialogFragment
{
    @BindView(R.id.lvItemDialog)    ListView lvItemDialog;
    @BindView(R.id.ctrLoading)      View ctrLoading;

    Class<T> t;
    CompositeDisposable c;
    List<T> l;
    ArrayAdapter<T> adp;

    OnGettingData<T> onGettingData;
    OnSelected<T> onSelected;

    public static <T> ListDialog<T> create(List<T> l, FragmentManager manager, OnGettingData<T> o, OnSelected<T> o1, Class<T> t)
    {
        Bundle b = new Bundle();
        b.putString("l", Gxon.toJsonArray(l));

        ListDialog<T> d = new ListDialog<>();
        d.setArguments(b);
        d.show(manager, o, o1, t);

        return d;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        c = new CompositeDisposable();
        l = Gxon.fromJsonArray(getArguments().getString("l"), t);
        adp = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, l);

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_list, null);
        ButterKnife.bind(this, v);

        lvItemDialog.setAdapter(adp);
        lvItemDialog.setOnItemClickListener((av, vw, i, lo) ->
        {
            onSelected.onSelected(l.get(i));
            dismiss();
        });

        if (l.isEmpty())
        {
            ctrLoading.setVisibility(View.VISIBLE);

            onGettingData.getData(list ->
            {
                ctrLoading.setVisibility(View.GONE);
                l.clear();
                l.addAll(list);
                adp.notifyDataSetChanged();
            }, () ->
            {
                ctrLoading.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Eiiittss, koneksi anda periksa dulu!", Toast.LENGTH_SHORT).show();
            });
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

    public void show(FragmentManager manager, OnGettingData o, OnSelected o1, Class<T> t)
    {
        super.show(manager, "u");
        this.onGettingData = o;
        this.onSelected = o1;
        this.t = t;
    }

    public interface OnSelected<T>
    {
        void onSelected(T u);
    }

    public interface OnGettingData<T>
    {
        void getData(OnDataLoaded<T> o, OnDataError o1);
    }

    public interface OnDataLoaded<T>
    {
        void onLoaded(List<T> l);
    }

    public interface OnDataError
    {
        void onError();
    }
}
