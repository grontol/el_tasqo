package ib.ganz.eltasqo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ib.ganz.eltasqo.R;
import ib.ganz.eltasqo.activity.baseactivity.BaseActivityBack;
import ib.ganz.eltasqo.dataclass.TaskData;
import ib.ganz.eltasqo.helper.Gxon;

public class DetailTaskActivity extends BaseActivityBack
{
    public static void go(Context c, TaskData t)
    {
        Intent i = new Intent(c, DetailTaskActivity.class);
        i.putExtra("t", Gxon.toJsonObject(t));
        c.startActivity(i);
    }

    @BindView(R.id.txtJudul)    TextView txtJudul;
    @BindView(R.id.rbTask)      RatingBar rbTask;
    @BindView(R.id.ctrUser)     View ctrUser;
    @BindView(R.id.txtNamaUser) TextView txtNamaUser;
    @BindView(R.id.txtBagian)   TextView txtBagian;
    @BindView(R.id.txtStatus)   TextView txtStatus;
    @BindView(R.id.txtKeterangan)TextView txtKeterangan;

    TaskData t;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_task);
        ButterKnife.bind(this);

        t = Gxon.fromJsonObject(getIntent().getStringExtra("t"), TaskData.class);

        txtJudul.setText(t.getJudul());
        rbTask.setRating(t.getPrioritas());
        if (t.getUserData() == null)
        {
            ctrUser.setVisibility(View.GONE);
        }
        else
        {
            txtNamaUser.setText(t.getUserData().getNamaUser());
        }
        txtBagian.setText(t.getBagianData().getNamaBagian());
        txtStatus.setText(t.getStatus());
        txtKeterangan.setText(t.getKeterangan());
    }
}
