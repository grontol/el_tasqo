package ib.ganz.eltasqo.filter;

import ib.ganz.eltasqo.dataclass.TaskData;

/**
 * Created by GrT on 26/04/2018.
 */

public class AssignmentFilter implements TaskFilter
{
    private int assignment;
    
    public AssignmentFilter(int assignment)
    {
        this.assignment = assignment;
    }
    
    @Override
    public boolean filtered(TaskData t)
    {
        return t.getAssignment() == assignment;
    }
}
