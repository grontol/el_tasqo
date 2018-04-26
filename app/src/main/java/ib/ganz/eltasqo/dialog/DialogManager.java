package ib.ganz.eltasqo.dialog;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

import ib.ganz.eltasqo.R;

/**
 * Created by limakali on 4/25/2018.
 */

public class DialogManager
{
    public static void filter(Activity a)
    {
        View v = a.getLayoutInflater().inflate(R.layout.dialog_filter, null);
        CheckBox cbPending = v.findViewById(R.id.cbPending);
        CheckBox cbProses = v.findViewById(R.id.cbProses);
        CheckBox cbSelesai = v.findViewById(R.id.cbSelesai);

        View ctrBagian = v.findViewById(R.id.ctrBagian);
        Button btnAddBagian = v.findViewById(R.id.btnAddBagian);

        RadioButton rbPembuatan = v.findViewById(R.id.rbPembuatan);
        RadioButton rbPrioritas = v.findViewById(R.id.rbPrioritas);

        AlertDialog ad = new AlertDialog.Builder(a)
                .setView(v)
                .show();

        Button btnFilter = v.findViewById(R.id.btnFilter);
        btnFilter.setOnClickListener(x -> ad.dismiss());
    }

    public static void confirmLogin(Activity a, String nama, OkCancel o)
    {
        okCancel(a, "Eiiits, apakah anda mau login sebagai " + nama, o);
    }

    public static void okCancel(Context a, String s, OkCancel o)
    {
        new AlertDialog.Builder(a)
                .setMessage(s)
                .setPositiveButton("Ok", (dialogInterface, i) -> o.onOk())
                .setNegativeButton("Cancel", null)
                .show();
    }

    public interface OkCancel
    {
        void onOk();
    }
}
