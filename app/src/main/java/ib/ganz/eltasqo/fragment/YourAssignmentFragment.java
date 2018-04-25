package ib.ganz.eltasqo.fragment;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import ib.ganz.eltasqo.dataclass.TaskData;

/**
 * Created by limakali on 4/25/2018.
 */

public class YourAssignmentFragment extends BaseFragment
{
    public static YourAssignmentFragment create()
    {
        return new YourAssignmentFragment();
    }

    @Override
    protected void filterData()
    {
        List<TaskData> lTemp = new ArrayList<>();
        lTemp.addAll(lTask);

        lTask.clear();

        for (TaskData t : lTemp)
        {
            if (t.getAssignment() == TaskData.MYASSIGNMENT)
            {
                lTask.add(t);
            }
        }
    }
}
