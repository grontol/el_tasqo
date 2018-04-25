package ib.ganz.eltasqo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ib.ganz.eltasqo.R;
import ib.ganz.eltasqo.activity.baseactivity.BaseActivity;
import ib.ganz.eltasqo.adapter.AdpFragment;
import ib.ganz.eltasqo.dataclass.TaskData;
import ib.ganz.eltasqo.dialog.DialogManager;
import ib.ganz.eltasqo.fragment.AssignedFragment;
import ib.ganz.eltasqo.fragment.BaseFragment;
import ib.ganz.eltasqo.fragment.UnassignedFragment;
import ib.ganz.eltasqo.fragment.YourAssignmentFragment;
import ib.ganz.eltasqo.helper.SessionManager;
import ib.ganz.eltasqo.service.Servize;

public class MainActivity extends BaseActivity
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
    AssignedFragment af;
    YourAssignmentFragment yf;
    Fragment[] arf;

    AdpFragment adpFragment;
    List<TaskData> lTask;

    int loadedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        uf = UnassignedFragment.create();
        af = AssignedFragment.create();
        yf = YourAssignmentFragment.create();
        arf = new Fragment[] { uf, af, yf };

        adpFragment = new AdpFragment(getSupportFragmentManager(), arf,
                new String[] {"Unassigned", "Assigned", "Your Assignment"});

        lTask = new ArrayList<>();

        viewPager.setAdapter(adpFragment);
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void onFragmentLoaded()
    {
        loadedFragment++;

        if (loadedFragment == 3)
        {
            getData();
        }
    }

    public void getData()
    {
        for (Fragment f : arf)
        {
            ((BaseFragment) f).onGettingData();
        }

        getCompositeDisposable().add(Servize.getTask().subscribe(r ->
        {
            lTask.clear();
            lTask.addAll(r);

            for (Fragment f : arf)
            {
                ((BaseFragment) f).onFinished(lTask);
            }
        }, e ->
        {
            makeToastPeriksaKoneksi();

            for (Fragment f : arf)
            {
                ((BaseFragment) f).onError();
            }
        }));
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
