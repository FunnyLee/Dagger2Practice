package com.funny.geek.ui.weChat;

import android.os.Bundle;

import com.funny.geek.R;
import com.funny.geek.base.BaseFragment;

/**
 * Author: Funny
 * Time: 2019/2/15
 * Description: This is WeChatMainFragment
 */
public class WeChatMainFragment extends BaseFragment {

    public static WeChatMainFragment newInstance() {
        Bundle args = new Bundle();
        WeChatMainFragment fragment = new WeChatMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_we_chat;
    }
}
