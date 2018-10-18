package com.funny.geek.ui.zhihu;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.funny.geek.base.AllBaseFragment;
import com.funny.geek.R;
import com.funny.geek.ui.adpter.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Author: Funny
 * Time: 2018/10/16
 * Description: This is ZhihuMainFragment
 */
public class ZhihuMainFragment extends AllBaseFragment {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    Unbinder unbinder;

    private List<String> titleList = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();

    public static ZhihuMainFragment newInstance() {
        Bundle args = new Bundle();
        ZhihuMainFragment fragment = new ZhihuMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mian_zhihu;
    }

    @Override
    protected void initView(View view) {
        titleList.add(getString(R.string.daily_paper));
        titleList.add(getString(R.string.subject));
        titleList.add(getString(R.string.special));
        titleList.add(getString(R.string.hot));
        fragmentList.add(DailyFragment.newInstance());
        fragmentList.add(HotFragment.newInstance());
        fragmentList.add(SpecialFragment.newInstance());
        fragmentList.add(SubjectFragment.newInstance());

        FragmentAdapter adapter = new FragmentAdapter(getFragmentManager(), fragmentList, titleList);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
