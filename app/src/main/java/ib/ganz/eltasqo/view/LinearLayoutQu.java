package ib.ganz.eltasqo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import ib.ganz.eltasqo.R;

/**
 * Created by GrT on 14/04/2018.
 */

public class LinearLayoutQu extends LinearLayout
{
    private View vError;
    private View vLoading;
    private View ctrLoading;
    private View ctrRefresh;
    private View btnRefresh;
    
    public LinearLayoutQu(Context context)
    {
        super(context);
        init();
    }
    
    public LinearLayoutQu(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }
    
    public LinearLayoutQu(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }
    
    private void init()
    {
        vError = LayoutInflater.from(getContext()).inflate(R.layout.v_linearlayout_error, null);
        btnRefresh = vError.findViewById(R.id.btnRefresh);
        ctrRefresh = vError.findViewById(R.id.ctrRefresh);
    
        //vError.setVisibility(GONE);
        addView(vError);
        
        vLoading = LayoutInflater.from(getContext()).inflate(R.layout.v_linearlayout_loading, null);
        ctrLoading = vLoading.findViewById(R.id.ctrLoading);
        
        //vLoading.setVisibility(GONE);
        addView(vLoading);
    }
    
    public void setError(boolean b)
    {
        for (int i = 0; i < getChildCount(); i++)
        {
            if (getChildAt(i) != vError && getChildAt(i) != vLoading)
                getChildAt(i).setVisibility(b ? GONE : VISIBLE);
        }
        
        vError.setVisibility(b ? VISIBLE : GONE);
        if (b) vLoading.setVisibility(GONE);
    }
    
    public void setLoading(boolean b)
    {
        for (int i = 0; i < getChildCount(); i++)
        {
            if (getChildAt(i) != vError && getChildAt(i) != vLoading)
                getChildAt(i).setVisibility(b ? GONE : VISIBLE);
        }
        
        vLoading.setVisibility(b ? VISIBLE : GONE);
        if (b) vError.setVisibility(GONE);
    }
    
    @Override
    public void removeAllViews()
    {
        for (int i = 0; i < getChildCount(); i++)
        {
            if (getChildAt(i) != vError && getChildAt(i) != vLoading)
                removeView(getChildAt(i));
        }
    }
    
    public void setOnRefreshClickListener(OnClickListener o)
    {
        btnRefresh.setOnClickListener(o);
    }
}
