package com.funny.geek.base;

/**
 * Author: Funny
 * Time: 2018/10/17
 * Description: This is BaseView
 */
public interface IBaseView {

    void onShowErrorMsg(String msg);

    /**
     * 页面加载数据的各种状态
     */
    void onStateError();

    void onStateEmpty();

    void onStateSuccess();

    void onStateLoading();

}
