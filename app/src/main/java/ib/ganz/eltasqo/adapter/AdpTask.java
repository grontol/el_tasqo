package ib.ganz.eltasqo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ib.ganz.eltasqo.R;
import ib.ganz.eltasqo.activity.AddTaskActivity;
import ib.ganz.eltasqo.dataclass.TaskData;
import ib.ganz.eltasqo.dataclass.UserData;
import ib.ganz.eltasqo.dialog.DialogManager;
import ib.ganz.eltasqo.dialog.ListDialog;

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
    @BindView(R.id.ctrNama)     View ctrNama;
    @BindView(R.id.ctrBagian)   View ctrBagian;
    @BindView(R.id.txtBagian)   TextView txtBagian;

    Fragment f;
    FragmentManager fm;
    ListDialog.OnGettingData<UserData> onGettingData;
    OnTaskChange onTaskChange;

    public AdpTask(@NonNull Context context, @NonNull List<TaskData> objects, Fragment f, FragmentManager fragmentManager,
                   ListDialog.OnGettingData<UserData> onGettingData, OnTaskChange onTaskChange)
    {
        super(context, 0, objects);

        this.f = f;
        this.fm = fragmentManager;
        this.onTaskChange = onTaskChange;
        this.onGettingData = onGettingData;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View v, @NonNull ViewGroup parent)
    {
        if (v == null) v = LayoutInflater.from(getContext()).inflate(R.layout.item_task, parent, false);
        ButterKnife.bind(this, v);

        TaskData t = getItem(position);

        txtStatus.setText(t.getStatus());
        txtStatus.setTextColor(t.getColorStatus());
        rbTask.setRating(t.getPrioritas());
        txtJudul.setText(t.getJudul());
        ctrBagian.setBackgroundColor(Color.parseColor(t.getBagianData().getWarna()));
        txtBagian.setText(t.getBagianData().getNamaBagian());
        btnMore.setOnClickListener(x -> showPopup(btnMore, t));

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

    private void showPopup(View v, TaskData t)
    {
        PopupMenu popupMenu = new PopupMenu(getContext(), v);
        popupMenu.inflate(R.menu.menu_task);

        popupMenu.setOnMenuItemClickListener(item ->
        {
            int id = item.getItemId();

            if (id == R.id.mn_assign)
            {
                List<UserData> l = new ArrayList<>();
                ListDialog.create(l, fm, onGettingData, u -> onTaskChange.onTaskAssigned(t.getIdTask(), u), UserData.class);
            }
            else if (id == R.id.mn_edit)
            {
                AddTaskActivity.go(f, t);
            }
            else if (id == R.id.mn_delete)
            {
                DialogManager.okCancel(getContext(), "Eeiittss, yakin mau ngehapus?", () -> onTaskChange.onTaskDeleted(t.getIdTask()));
            }
            else if (id == R.id.mn_status)
            {
                ListDialog.create(Arrays.asList("Pending", "Proses", "Selesai"), fm, null,
                        u -> onTaskChange.onTaskStatusChanged(t.getIdTask(), u), String.class);
            }

            return false;
        });

        popupMenu.show();
    }

    public interface OnTaskChange
    {
        void onTaskAssigned(String idTask, UserData u);
        void onTaskDeleted(String idTask);
        void onTaskStatusChanged(String idTask, String status);
    }
}
