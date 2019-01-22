package com.funny.geek.ui.zhihu;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;

import com.funny.geek.R;
import com.funny.geek.base.RootActivity;
import com.funny.geek.contract.zhihu.SelectDateContract;
import com.funny.geek.presenter.zhihu.SelectDatePresenter;

import butterknife.BindView;

public class SelectDateActivity extends RootActivity<SelectDatePresenter> implements SelectDateContract.View {

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;

    public static void start(Context context) {
        Intent starter = new Intent(context, SelectDateActivity.class);
        context.startActivity(starter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_date;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(SelectDateActivity.this);
    }

    @Override
    protected void initView() {
        setSupportActionBar(mToolBar);
        //ToolBar左边增加返回箭头
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //返回箭头可用
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    protected void initData() {
        mPresenter.doLoadData();

    }


    @Override
    public void onShowContentView() {

    }

}
