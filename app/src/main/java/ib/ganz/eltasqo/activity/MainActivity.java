package ib.ganz.eltasqo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import ib.ganz.eltasqo.R;
import ib.ganz.eltasqo.adapter.AdpFragment;
import ib.ganz.eltasqo.fragment.BaseFragment;
import ib.ganz.eltasqo.fragment.GeneralFragment;
import ib.ganz.eltasqo.fragment.OnlyYouFragment;

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

    GeneralFragment gf;
    OnlyYouFragment of;
    AdpFragment adpFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        gf = GeneralFragment.create(BaseFragment.GENERAL);
        of = OnlyYouFragment.create(BaseFragment.ONLYYOU);
        adpFragment = new AdpFragment(getSupportFragmentManager(), new Fragment[] {gf, of}, new String[] {"General", "Only U"});

        viewPager.setAdapter(adpFragment);
        tabLayout.setupWithViewPager(viewPager);
    }
}
