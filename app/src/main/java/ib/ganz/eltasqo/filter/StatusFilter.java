package ib.ganz.eltasqo.filter;

import ib.ganz.eltasqo.dataclass.TaskData;

/**
 * Created by GrT on 26/04/2018.
 */

public class StatusFilter implements TaskFilter
{
    private String status;
    
    public StatusFilter()
    {
        status = "-";
    }
    
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    @Override
    public boolean filtered(TaskData t)
    {
        if (status.equals("-") || t.getStatus().equals(status))
            return false;
        else
            return true;
    }
}
