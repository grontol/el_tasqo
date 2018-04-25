package ib.ganz.eltasqo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import ib.ganz.eltasqo.R;
import ib.ganz.eltasqo.adapter.AdpFragment;
import ib.ganz.eltasqo.fragment.AssignedFragment;
import ib.ganz.eltasqo.fragment.BaseFragment;
import ib.ganz.eltasqo.fragment.UnassignedFragment;
import ib.ganz.eltasqo.fragment.YourAssignmentFragment;
import ib.ganz.eltasqo.helper.DialogManager;
import ib.ganz.eltasqo.helper.SessionManager;

public class MainActivity extends AppCompatActivity
{
    public static void go(Activity c)
    {
        Intent i = new Intent(c, MainActivity.class);
        c.startActivity(i);
        c.finish();
    }

    @BindView(R.id.tablayout)   TabLayout tabLayout;
    @BindView(R.id.viewpager)   ViewPager viewPager;

    UnassignedFragment uf;
    AssignedFragment as;
    YourAssignmentFragment yf;

    AdpFragment adpFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        uf = UnassignedFragment.create(BaseFragment.UNASSIGNED);
        yf = YourAssignmentFragment.create(BaseFragment.MYASSIGNMENT);
        adpFragment = new AdpFragment(getSupportFragmentManager(),
                new Fragment[] {uf, as, yf},
                new String[] {"Unassigned", "Assigned", "Your Assignment"});

        viewPager.setAdapter(adpFragment);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_logout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == R.id.mn_logout)
        {
            DialogManager.okCancel(this, "Eeiiitttsss, anda mau logout???", this::finish);
            SessionManager.logout();
        }
        return super.onOptionsItemSelected(item);
    }
}
