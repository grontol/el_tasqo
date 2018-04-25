package ib.ganz.eltasqo.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ib.ganz.eltasqo.R;
import ib.ganz.eltasqo.activity.AddTaskActivity;
import ib.ganz.eltasqo.activity.DetailTaskActivity;
import ib.ganz.eltasqo.activity.MainActivity;
import ib.ganz.eltasqo.adapter.AdpTask;
import ib.ganz.eltasqo.dataclass.TaskData;
import ib.ganz.eltasqo.dataclass.UserData;
import ib.ganz.eltasqo.dialog.ListDialog;
import ib.ganz.eltasqo.service.Servize;
import ib.ganz.eltasqo.view.LinearLayoutQu;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by limakali on 4/25/2018.
 */

public abstract class BaseFragment extends Fragment
{
    @BindView(R.id.llQu)    LinearLayoutQu llQu;
    @BindView(R.id.lvTask)  ListView lvTask;
    @BindView(R.id.btnAdd)  FloatingActionButton btnAdd;

    MainActivity m;
    CompositeDisposable cd;
    List<TaskData> lTask;
    AdpTask adpTask;
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_base, container, false);
        ButterKnife.bind(this, v);

        m = (MainActivity) getActivity(); 
        cd = new CompositeDisposable();
        lTask = new ArrayList<>();
        adpTask = new AdpTask(getActivity(), lTask, this, getFragmentManager(), this::getDataUser, o);

        llQu.setOnRefreshClickListener(x -> m.getData());
        btnAdd.setOnClickListener(x -> AddTaskActivity.go(this, null));
        lvTask.setAdapter(adpTask);
        lvTask.setOnItemClickListener((av, vw, i, l) -> DetailTaskActivity.go(m, lTask.get(i)));

        m.onFragmentLoaded();
        return v;
    }

    AdpTask.OnTaskChange o = new AdpTask.OnTaskChange()
    {
        @Override
        public void onTaskAssigned(String idTask, UserData u)
        {
            llQu.setLoading(true);

            cd.add(Servize.assignTask(idTask, u.getIdUser()).subscribe(
                    r -> m.getData(),
                    e -> llQu.setError(true)));
        }

        @Override
        public void onTaskDeleted(String idTask)
        {
            llQu.setLoading(true);

            cd.add(Servize.deleteTask(idTask).subscribe(
                    r -> m.getData(),
                    e ->
                    {
                        llQu.setError(true);
                        e.printStackTrace();
                        Log.e("Elzzz", e.getMessage());
                    }));
        }

        @Override
        public void onTaskStatusChanged(String idTask, String status)
        {
            llQu.setLoading(true);

            cd.add(Servize.updateTaskStatus(idTask, status).subscribe(
                    r -> m.getData(),
                    e -> llQu.setError(true)));
        }
    };

    private void getDataUser(ListDialog.OnDataLoaded<UserData> userDataOnDataLoaded, ListDialog.OnDataError onDataError)
    {
        cd.add(Servize.getUser().subscribe(userDataOnDataLoaded::onLoaded, e ->
        {
            Toast.makeText(getActivity(), "Eeiitttsss, periksa koneksi anda!", Toast.LENGTH_SHORT).show();
            onDataError.onError();
        }));
    }

    public void onGettingData()
    {
        llQu.setLoading(true);
    }
    
    public void onFinished(List<TaskData> lTask)
    {
        this.lTask.clear();
        this.lTask.addAll(lTask);
        filterData();
        adpTask.notifyDataSetChanged();
        llQu.setLoading(false);
    }
    
    public void onError()
    {
        this.lTask.clear();
        llQu.setError(true);
    }

    protected abstract void filterData();

    @Override
    public void onDestroy()
    {
        super.onDestroy();

        if (cd != null && !cd.isDisposed())
        {
            cd.dispose();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        
        if (resultCode == Activity.RESULT_OK)
        {
            m.getData();
        }
    }
}
