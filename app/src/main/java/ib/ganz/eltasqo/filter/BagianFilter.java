package ib.ganz.eltasqo.filter;

import ib.ganz.eltasqo.dataclass.TaskData;

/**
 * Created by GrT on 26/04/2018.
 */

public class BagianFilter implements TaskFilter
{
    private String idBagian;
    
    public BagianFilter()
    {
        idBagian = "";
    }
    
    public void setIdBagian(String idBagian)
    {
        this.idBagian = idBagian;
    }
    
    @Override
    public boolean filtered(TaskData t)
    {
        if (t.getStatus().equals("-") || t.getIdBagian().equals(idBagian))
            return false;
        else
            return true;
    }
}
