package com.li.yu.mvc.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;



public abstract class BaseFragment extends SupportFragment {

    private Unbinder unBinder;
    private View view;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        loadData();
    }

    /**
     * setContentView;
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        unBinder = ButterKnife.bind(this, view);
        initEventAndData();
        return view;
    }

    public View getFragmentView() {
        return view;
    }

    /**
     * 获取当前Activity的UI布局
     *
     * @return 布局id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化view数据
     */
    protected abstract void initEventAndData();

    /**
     * 初始化数据
     */
    public abstract void loadData();


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(String event) {

    }

    @Override
    public void onDetach() {
        super.onDetach();
        //反注册EventBus
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unBinder != null)
            unBinder.unbind();
    }
}
