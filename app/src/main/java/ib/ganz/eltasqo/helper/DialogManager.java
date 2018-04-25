package ib.ganz.eltasqo.helper;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by limakali on 4/25/2018.
 */

public class DialogManager
{
    public static void confirmLogin(Activity a, String nama, OkCancel o)
    {
        new AlertDialog.Builder(a)
                .setMessage("Eiiits, apakah anda mau login sebagai " + nama)
                .setPositiveButton("Ok", (dialogInterface, i) -> o.onOk())
                .setNegativeButton("Cancel", null)
                .show();
    }

    public interface OkCancel
    {
        void onOk();
    }
}