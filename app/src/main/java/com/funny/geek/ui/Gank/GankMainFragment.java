package com.funny.geek.ui.Gank;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.funny.geek.R;
import com.funny.geek.base.BaseFragment;
import com.funny.geek.ui.adpter.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author: Funny
 * Time: 2019/3/27
 * Description: This is GankMainFragment
 */
public class GankMainFragment extends BaseFragment {
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private List<String> titleList = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();

    public static GankMainFragment newInstance() {
        Bundle args = new Bundle();
        GankMainFragment fragment = new GankMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_gank;
    }

    @Override
    protected void initView(View view) {
        titleList.add(getString(R.string.android));
        titleList.add(getString(R.string.ios));
        titleList.add(getString(R.string.front));
        titleList.add(getString(R.string.werfale));
        fragmentList.add(GankTechFragment.newInstance("Android"));
        fragmentList.add(GankTechFragment.newInstance("IOS"));
        fragmentList.add(GankTechFragment.newInstance("前端"));
        fragmentList.add(GankTechFragment.newInstance("Android"));

        FragmentAdapter adapter = new FragmentAdapter(getFragmentManager(), fragmentList, titleList);
        mViewPager.setAdapter(adapter);
//        mViewPager.setOffscreenPageLimit(4);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
