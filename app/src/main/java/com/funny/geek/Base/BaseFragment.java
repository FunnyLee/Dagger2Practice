package com.funny.geek.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.funny.geek.AppManager;
import com.funny.geek.di.component.DaggerFragmentComponent;
import com.funny.geek.di.component.FragmentComponent;
import com.funny.geek.di.module.FragmentModule;

import javax.inject.Inject;

/**
 * Author: Funny
 * Time: 2018/10/17
 * Description: This is MVP的BaseFragment
 */
public abstract class BaseFragment<P extends IBasePresenter> extends AllBaseFragment implements IBaseView {

    @Inject
    protected P mPresenter;

    protected FragmentComponent getFragmentComponent() {
        FragmentComponent fragmentComponent = DaggerFragmentComponent.builder()
                //表示dependencies
                .appComponent(AppManager.getAppComponent())
                //表示modules
                .fragmentModule(new FragmentModule(this))
                .build();

        return fragmentComponent;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initInject();
        mPresenter.attachView(this);
        return super.onCreateView(inflater, container, savedInstanceState);
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
