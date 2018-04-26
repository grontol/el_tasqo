package ib.ganz.eltasqo.helper;

import android.app.Application;

/**
 * Created by limakali on 3/18/2018.
 */

public class AppQu extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        SessionManager.init(this);
        FilterManager.init(this);
    }
}
