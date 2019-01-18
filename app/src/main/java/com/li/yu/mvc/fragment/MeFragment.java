package com.li.yu.mvc.fragment;

import android.content.Intent;
import android.view.View;

import com.li.yu.mvc.R;
import com.li.yu.mvc.activity.TestActivity;
import com.li.yu.mvc.base.BaseFragment;

import butterknife.OnClick;

public class MeFragment extends BaseFragment {

    public static MeFragment getInstance(){
        MeFragment fragment = new MeFragment();
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void loadData() {

    }
    @OnClick(R.id.ll_about)
    public void onClickView(View view){
        startActivity(new Intent(getActivity(), TestActivity.class));
    }
}
