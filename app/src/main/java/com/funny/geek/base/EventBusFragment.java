package com.funny.geek.base;

import android.os.Bundle;

import com.trello.navi2.component.support.NaviFragment;

import org.greenrobot.eventbus.EventBus;

/**
 * Author: Funny
 * Time: 2019/1/29
 * Description: This is 封装了EventBus的Fragment
 */
public class EventBusFragment extends NaviFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
