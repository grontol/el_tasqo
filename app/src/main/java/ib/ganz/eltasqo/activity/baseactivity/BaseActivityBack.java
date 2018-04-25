package ib.ganz.eltasqo.activity.baseactivity;

/**
 * Created by limakali on 1/15/2018.
 */

public class BaseActivityBack extends BaseActivity
{
    @Override
    protected void onResume()
    {
        super.onResume();

        if (getSupportActionBar() == null) return;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        finish();
        return super.onSupportNavigateUp();
    }
}
