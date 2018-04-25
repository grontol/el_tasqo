package ib.ganz.eltasqo.activity.baseactivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by limakali on 1/5/2018.
 */

public class BaseActivity extends AppCompatActivity
{
    private ProgressDialog progressDialog;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (!compositeDisposable.isDisposed()) compositeDisposable.dispose();
    }

    public CompositeDisposable getCompositeDisposable()
    {
        return compositeDisposable;
    }

    public CompositeDisposable getCompositeDisposablePD()
    {
        showProgressDialog();
        return compositeDisposable;
    }
    public void makeToast(String s)
    {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
    public void makeLongToast(String s)
    {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }

    public void makeToastPeriksaKoneksi()
    {
        makeToast("Periksa koneksi Anda!");
    }

    public void showProgressDialog()
    {
        if (!progressDialog.isShowing()) progressDialog.show();
    }

    public void closeProgressDialog()
    {
        if (progressDialog.isShowing()) progressDialog.dismiss();
    }

    public void hide(View v) { v.setVisibility(View.GONE); }

    public void show(View v) { v.setVisibility(View.VISIBLE); }
}
