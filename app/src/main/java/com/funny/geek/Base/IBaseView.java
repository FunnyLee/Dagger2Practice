package com.funny.geek.base;

/**
 * Author: Funny
 * Time: 2018/10/17
 * Description: This is BaseView
 */
public interface IBaseView {

    void toastErrorMsg(String msg);

    /**
     * 页面加载数据的各种状态
     */
    void onStatusNetError();

    void onStatusEmpty();

    void onStatusSuccess();

    void onStatusLoading();

}
