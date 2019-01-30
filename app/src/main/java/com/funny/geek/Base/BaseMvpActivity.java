package com.funny.geek.base;

import com.funny.geek.AppManager;
import com.funny.geek.di.component.ActivityComponent;
import com.funny.geek.di.component.DaggerActivityComponent;
import com.funny.geek.di.module.ActivityModule;

import javax.inject.Inject;

/**
 * Author: Funny
 * Time: 2019/1/18
 * Description: This is Mvp Activity的基类
 */
public abstract class BaseMvpActivity<P extends IBasePresenter> extends BaseActivity {

    @Inject
    protected P mPresenter;

    protected ActivityComponent getActivityComponent() {
        ActivityComponent activityComponent = DaggerActivityComponent.builder()
                .appComponent(AppManager.getAppComponent())
                .activityModule(new ActivityModule(this))
                .build();
        return activityComponent;
    }

    @Override
    protected void initMvpDagger() {
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    protected abstract void initInject();
}
