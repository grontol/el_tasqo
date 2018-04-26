package ib.ganz.eltasqo.filter;

import java.util.ArrayList;
import java.util.List;

import ib.ganz.eltasqo.dataclass.TaskData;

/**
 * Created by GrT on 26/04/2018.
 */

public class TaskFilterTEst
{
    public void filter()
    {
        AssignmentFilter af = new AssignmentFilter(0);
        BagianFilter bf = new BagianFilter();
        bf.setIdBagian("1");
        StatusFilter sf = new StatusFilter();
        sf.setStatus(TaskData.PENDING);
        
        CompositeFilter cf = new CompositeFilter();
        cf.addFilter(af);
        cf.addFilter(bf);
        cf.addFilter(sf);
        
        List<TaskData> lTask = new ArrayList<>();
        List<TaskData> lTemp = new ArrayList<>();
        
        lTemp.addAll(lTask);
        lTask.clear();
        
        for (TaskData t : lTemp)
        {
            if (!cf.filtered(t))
                lTask.add(t);
        }
    }
}
