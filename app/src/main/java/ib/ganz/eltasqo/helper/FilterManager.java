package ib.ganz.eltasqo.helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * Created by limakali on 4/26/2018.
 */

public class FilterManager
{
    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;

    public static void init(Context c)
    {
        if (sp == null || editor == null)
        {
            sp = c.getSharedPreferences("eltasqow", 0);
            editor = sp.edit();
        }
    }

    private static final String STATUS = "s";
    private static final String BAGIAN = "b";
    private static final String ORDER_BY = "o";

    public void setStatusFilter(String... ss)
    {
        StringBuilder s  = new StringBuilder();
        for (int i = 0; i < ss.length; i++)
        {
            if (i > 0) s.append("-");
            s.append(ss[i]);
        }

        editor.putString(STATUS, s.toString()).commit();
    }

    public String[] getStatusFilter()
    {
        return sp.getString(STATUS, "").split("-");
    }

    public void setStatusBagian(String... ss)
    {
        StringBuilder s  = new StringBuilder();
        for (int i = 0; i < ss.length; i++)
        {
            if (i > 0) s.append("-");
            s.append(ss[i]);
        }

        editor.putString(BAGIAN, s.toString()).commit();
    }

    public String[] getStatusBagian()
    {
        return sp.getString(BAGIAN, "").split("-");
    }

    public void setOrderBY(OrderBy o)
    {
        editor.putInt(ORDER_BY, o == OrderBy.PEMBUATAN ? 0 : 1).commit();
    }

    public OrderBy getOrderBY()
    {
        return sp.getInt(ORDER_BY, 0) == 0 ? OrderBy.PEMBUATAN : OrderBy.PRIORITAS;
    }

    public enum OrderBy
    {
        PEMBUATAN, PRIORITAS
    }
}
