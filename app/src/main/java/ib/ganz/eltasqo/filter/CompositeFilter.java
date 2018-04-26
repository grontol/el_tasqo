package ib.ganz.eltasqo.filter;

import java.util.ArrayList;
import java.util.List;

import ib.ganz.eltasqo.dataclass.TaskData;

/**
 * Created by GrT on 26/04/2018.
 */

public class CompositeFilter implements TaskFilter
{
    private List<TaskFilter> filters;
    
    public CompositeFilter()
    {
        filters = new ArrayList<>();
    }
    
    public void addFilter(TaskFilter f)
    {
        filters.add(f);
    }
    
    public void removeFilter(TaskFilter f)
    {
        filters.remove(f);
    }
    
    @Override
    public boolean filtered(TaskData t)
    {
        boolean b = false;
        
        for (TaskFilter f : filters)
        {
            b |= f.filtered(t);
        }
        
        return b;
    }
}
