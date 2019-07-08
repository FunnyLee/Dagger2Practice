package com.funny.geek.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.funny.geek.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Funny
 * Time: 2019/4/25
 * Description: This is TestFragment
 */
public class TestFragment extends Fragment {

    private RecyclerView mRecyclerView;

    public static TestFragment newInstance() {

        Bundle args = new Bundle();

        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, null);
        mRecyclerView = view.findViewById(R.id.recycler_view);

        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add("这是第" + i + "个");
        }

        TestAdapter adapter = new TestAdapter(R.layout.item_test_view, datas);
        mRecyclerView.setAdapter(adapter);
        return view;
    }

    class TestAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public TestAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.text_tv, item);
        }
    }
}
