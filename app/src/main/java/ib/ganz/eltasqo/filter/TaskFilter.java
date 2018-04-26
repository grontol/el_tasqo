package ib.ganz.eltasqo.filter;

import ib.ganz.eltasqo.dataclass.TaskData;

/**
 * Created by GrT on 26/04/2018.
 */

public interface TaskFilter
{
    boolean filtered(TaskData t);
}