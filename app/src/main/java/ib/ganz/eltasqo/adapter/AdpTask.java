package ib.ganz.eltasqo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ib.ganz.eltasqo.R;
import ib.ganz.eltasqo.dataclass.TaskData;
import ib.ganz.eltasqo.dataclass.UserData;
import ib.ganz.eltasqo.helper.ListUserDialog;

/**
 * Created by limakali on 4/25/2018.
 */

public class AdpTask extends ArrayAdapter<TaskData>
{
    @BindView(R.id.txtStatus)   TextView txtStatus;
    @BindView(R.id.btnMore)     View btnMore;
    @BindView(R.id.rbTask)      RatingBar rbTask;
    @BindView(R.id.txtJudul)    TextView txtJudul;
    @BindView(R.id.txtNamaUser) TextView txtNamaUser;
    @BindView(R.id.ctrNama)     TextView ctrNama;

    FragmentManager fragmentManager;
    OnTaskAssigned o;

    public AdpTask(@NonNull Context context, @NonNull List<TaskData> objects, FragmentManager fragmentManager, OnTaskAssigned o)
    {
        super(context, 0, objects);

        this.fragmentManager = fragmentManager;
        this.o = o;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View v, @NonNull ViewGroup parent)
    {
        if (v == null) v = LayoutInflater.from(getContext()).inflate(R.layout.item_task, parent, false);
        ButterKnife.bind(this, v);

        TaskData t = getItem(position);

        txtStatus.setText(t.getStatus());
        rbTask.setRating(t.getPrioritas());
        txtJudul.setText(t.getJudul());
        btnMore.setOnClickListener(x -> showPopup(btnMore, t.getIdTask()));

        if (t.getAssignment() == TaskData.UNASSIGNED)
        {
            ctrNama.setVisibility(View.GONE);
        }
        else if (t.getAssignment() == TaskData.ASSIGNED)
        {
            ctrNama.setVisibility(View.VISIBLE);
            txtNamaUser.setText(t.getUserData().getNamaUser());
        }
        else if (t.getAssignment() == TaskData.MYASSIGNMENT)
        {
            ctrNama.setVisibility(View.VISIBLE);
            txtNamaUser.setText("Tugas anda ini!");
        }

        return v;
    }

    private void showPopup(View v, String idTask)
    {
        PopupMenu popupMenu = new PopupMenu(getContext(), v);
        popupMenu.inflate(R.menu.menu_task);

        popupMenu.setOnMenuItemClickListener(item ->
        {
            ListUserDialog.create(new ArrayList<>()).show(fragmentManager, u -> o.on(idTask, u));
            return false;
        });

        popupMenu.show();
    }

    public interface OnTaskAssigned
    {
        void on(String idTask, UserData u);
    }
}
