package com.li.yu.mvc.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.li.yu.mvc.R;
import com.li.yu.mvc.base.BaseActivity;
import com.li.yu.mvc.base.BaseFragment;
import com.li.yu.mvc.fragment.IndexFragment;
import com.li.yu.mvc.fragment.MeFragment;
import com.li.yu.mvc.fragment.ProjectFragment;
import com.li.yu.mvc.utils.BottomNavigationViewHelper;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView bottomNavigationView;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
    private ArrayList<BaseFragment> mFragments = new ArrayList<>();
    private int mLastFgIndex;

    @Override
    protected void initEventAndData() {
       BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
       initData();
       initBottomNav();
       switchFragment(0);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
//        super.onSaveInstanceState(outState, outPersistentState);
    }

    private void initBottomNav() {
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.tab_main_pager:
                    switchFragment(0);
                    break;
                case R.id.tab_kind:
                    switchFragment(1);
                    break;
                case R.id.tab_me:
                    switchFragment(2);
                    break;
                default:
                    break;
            }
            return true;
        });
    }

    /**
     * 切换fragment
     *
     * @param position 要显示的fragment的下标
     */
    private void switchFragment(int position) {
        if (position >= mFragments.size()) {
            return;
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment targetFg = mFragments.get(position);
        Fragment lastFg = mFragments.get(mLastFgIndex);
        mLastFgIndex = position;
        ft.hide(lastFg);
        if (!targetFg.isAdded()) {
            ft.add(R.id.fragment_group, targetFg);
        }
        ft.show(targetFg);
        ft.commitAllowingStateLoss();
    }

    private void initData() {
        IndexFragment indexFragment  = IndexFragment.getInstance();
        ProjectFragment mProjectFragment = ProjectFragment.getInstance();
        MeFragment meFragment = MeFragment.getInstance();
        mFragments.add(indexFragment);
        mFragments.add(mProjectFragment);
        mFragments.add(meFragment);
    }

}
