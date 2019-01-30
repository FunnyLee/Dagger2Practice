package com.funny.geek.ui.zhihu;

import android.os.Bundle;

import com.funny.geek.R;
import com.funny.geek.base.BaseMvpFragment;

/**
 * Author: Funny
 * Time: 2018/10/17
 * Description: This is 主题 Fragment
 */
public class SubjectFragment extends BaseMvpFragment {

    public static SubjectFragment newInstance() {
        Bundle args = new Bundle();
        SubjectFragment fragment = new SubjectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_subject;
    }

    @Override
    protected void initInject() {

    }
}
