package ib.ganz.eltasqo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ib.ganz.eltasqo.R;
import ib.ganz.eltasqo.activity.baseactivity.BaseActivityBack;
import ib.ganz.eltasqo.dataclass.BagianData;
import ib.ganz.eltasqo.dataclass.TaskData;
import ib.ganz.eltasqo.dialog.ListBagianDialog;
import ib.ganz.eltasqo.dialog.ListDialog;
import ib.ganz.eltasqo.helper.Gxon;
import ib.ganz.eltasqo.service.Servize;

public class AddTaskActivity extends BaseActivityBack
{
    public static void go(Fragment a, TaskData t)
    {
        Intent i = new Intent(a.getActivity(), AddTaskActivity.class);
        i.putExtra("t", t != null ? Gxon.toJsonObject(t) : null);
        a.startActivityForResult(i, 1);
    }

    @BindView(R.id.edtJudul)        EditText edtJudul;
    @BindView(R.id.edtKeterangan)   EditText edtKeterangan;
    @BindView(R.id.edtBagian)       EditText edtBagian;
    @BindView(R.id.rbTask)          RatingBar rbTask;
    @BindView(R.id.btnSave)         Button btnSave;

    List<BagianData> lBagian;
    BagianData selectedBagian;

    String judul, keterangan, jsonTaskData;
    float prioritas;
    boolean isEdit;
    TaskData t;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        ButterKnife.bind(this);

        lBagian = new ArrayList<>();
        jsonTaskData = getIntent().getStringExtra("t");

        if (jsonTaskData != null)
        {
            isEdit = true;
            t = Gxon.fromJsonObject(jsonTaskData, TaskData.class);
            setView();
        }
        else isEdit = false;

        edtBagian.setOnClickListener(x -> ListDialog.create(lBagian, getSupportFragmentManager(), this::getDataBagian, u ->
        {
            selectedBagian = u;
            edtBagian.setText(selectedBagian.getNamaBagian());

        }, BagianData.class));

        btnSave.setOnClickListener(x ->
        {
            if (isValid())
            {
                if (isEdit)
                    edit();
                else
                    save();
            }
        });
    }

    private void setView()
    {
        edtJudul.setText(t.getJudul());
        edtKeterangan.setText(t.getKeterangan());
        edtBagian.setText(t.getBagianData().getNamaBagian());
        rbTask.setRating(t.getPrioritas());
        selectedBagian = t.getBagianData();
    }

    private void getDataBagian(ListDialog.OnDataLoaded o, ListDialog.OnDataError o1)
    {
        getCompositeDisposable().add(Servize.getBagian().subscribe(r ->
        {
            lBagian.clear();
            lBagian.addAll(r);
            o.onLoaded(lBagian);
        },e ->
        {
            makeToastPeriksaKoneksi();
            o1.onError();
        }));
    }

    private boolean isValid()
    {
        judul = edtJudul.getText().toString().trim();
        keterangan = edtKeterangan.getText().toString().trim();
        prioritas = rbTask.getRating();

        if (judul.isEmpty())
        {
            edtJudul.requestFocus();
            edtJudul.setError("Eeiittsss, masukkan judul");
            return false;
        }
        else if (keterangan.isEmpty())
        {
            edtKeterangan.requestFocus();
            edtKeterangan.setError("EEiiitttss, masukkan keterangan dahulu!");
            return false;
        }
        else if (selectedBagian == null)
        {
            edtBagian.requestFocus();
            edtBagian.setError("EEiiitttss, pilih  bagian dulu!");
            return false;
        }
        return true;
    }

    private void save()
    {
        getCompositeDisposablePD().add(Servize.addTask(judul, keterangan, prioritas, selectedBagian.getIdBagian()).subscribe(r ->
        {
            closeProgressDialog();
            makeToast("Task berhasil ditambah");

            setResult(RESULT_OK);
            finish();
        }, e ->
        {
            closeProgressDialog();
            makeToastPeriksaKoneksi();
        }));
    }

    private void edit()
    {
        getCompositeDisposablePD().add(Servize.editTask(t.getIdTask(), judul, keterangan, prioritas, selectedBagian.getIdBagian()).subscribe(r ->
        {
            closeProgressDialog();
            makeToast("Task berhasil diedit");

            setResult(RESULT_OK);
            finish();
        }, e ->
        {
            closeProgressDialog();
            makeToastPeriksaKoneksi();
        }));
    }
}
