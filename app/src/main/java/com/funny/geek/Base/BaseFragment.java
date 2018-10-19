package com.funny.geek.base;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.ViewGroup;

import com.funny.geek.AppManager;
import com.funny.geek.di.component.DaggerFragmentComponent;
import com.funny.geek.di.component.FragmentComponent;
import com.funny.geek.di.module.FragmentModule;

import javax.inject.Inject;

/**
 * Author: Funny
 * Time: 2018/10/17
 * Description: This is BaseFragment
 */
public abstract class BaseFragment<P extends IBasePresenter> extends AllBaseFragment implements IBaseView {

    @Inject
    protected P mPresenter;

    protected FragmentComponent getFragmentComponent() {
        FragmentComponent fragmentComponent = DaggerFragmentComponent.builder()
                // TODO: 2018/10/18 这一行是依赖appComponent，后面再实现
                .appComponent(AppManager.getAppComponent())
                .fragmentModule(new FragmentModule(this))
                .build();

        return fragmentComponent;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initInject();
        mPresenter.attachView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void onShowErrorMsg(String msg) {
        //统一处理toast错误信息，使用snackbar
        Snackbar.make(((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0), msg, Snackbar.LENGTH_SHORT).show();
    }

    protected abstract void initInject();
}
