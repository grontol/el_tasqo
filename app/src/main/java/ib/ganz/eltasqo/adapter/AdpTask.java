package ib.ganz.eltasqo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import ib.ganz.eltasqo.R;
import ib.ganz.eltasqo.dataclass.TaskData;

/**
 * Created by limakali on 4/25/2018.
 */

public class AdpTask extends ArrayAdapter<TaskData>
{
    public AdpTask(@NonNull Context context, @NonNull List<TaskData> objects)
    {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View v, @NonNull ViewGroup parent)
    {
        if (v == null) v = LayoutInflater.from(getContext()).inflate(R.layout.item_task, parent, false);

        return v;
    }
}
