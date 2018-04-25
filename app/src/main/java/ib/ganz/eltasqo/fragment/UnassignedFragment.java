package ib.ganz.eltasqo.fragment;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import ib.ganz.eltasqo.dataclass.TaskData;

/**
 * Created by limakali on 4/25/2018.
 */

public class UnassignedFragment extends BaseFragment
{
    public static UnassignedFragment create()
    {
        return new UnassignedFragment();
    }

    @Override
    protected void filterData()
    {
        List<TaskData> lTemp = new ArrayList<>();
        lTemp.addAll(lTask);

        lTask.clear();

        for (TaskData t : lTemp)
        {
            if (t.getAssignment() == TaskData.UNASSIGNED)
            {
                lTask.add(t);
            }
        }
    }
}
