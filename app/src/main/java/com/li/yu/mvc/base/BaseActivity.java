package com.li.yu.mvc.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;


import com.li.yu.mvc.utils.ActivityCollector;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;



public abstract class BaseActivity extends SupportActivity {
    private Unbinder unBinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutId());
        unBinder = ButterKnife.bind(this);
        onViewCreated();
        ActivityCollector.getInstance().addActivity(this);
        EventBus.getDefault().register(this);
        initEventAndData();
    }

    protected void onViewCreated() {

    }
    /**
     * 获取当前Activity的UI布局
     *
     * @return 布局id
     */
    protected abstract int getLayoutId();


    /**
     * 初始化数据
     */
    protected abstract void initEventAndData();


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(String event) {

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        ActivityCollector.getInstance().removeActivity(this);
        unBinder.unbind();
    }
}
