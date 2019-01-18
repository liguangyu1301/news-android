package com.li.yu.mvc.fragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import com.li.yu.mvc.R;
import com.li.yu.mvc.activity.WebActivity;
import com.li.yu.mvc.adapter.IndexListAdapter;
import com.li.yu.mvc.base.BaseFragment;
import com.li.yu.mvc.entity.NewsList;
import com.li.yu.mvc.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

public class IndexFragment extends BaseFragment {
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.mRecyclerView)
    RecyclerView recyclerView;
    private List<NewsList.ItemBean> data = new ArrayList<>();
    private IndexListAdapter adapter;
    private static final String TAG = "IndexFragment";
    public static IndexFragment getInstance() {
        IndexFragment fragment = new IndexFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_index;
    }

    @Override
    protected void initEventAndData() {
        initBanner();
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setFocusable(false);
        recyclerView.setNestedScrollingEnabled(false);
        adapter = new IndexListAdapter(R.layout.item_list_index, data,getActivity());
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view, position) -> {
           NewsList.ItemBean bean = (NewsList.ItemBean) adapter.getItem(position);
           if(!TextUtils.isEmpty(bean.getUrl())){
               WebActivity.start(getActivity(),bean.getUrl());
           }else{
               ToastUtils.showToast(getActivity(),"数据丢失了！");
           }
        });
    }

    private void initBanner() {
        List<String> list = new ArrayList<>();
        list.add("http://pic2.ooopic.com/12/40/58/18bOOOPIC9c.jpg");
        list.add("http://www.5tu.cn/attachments/image_files/month_1812/201811292148343073.jpg");
        list.add("http://pic.rmb.bdstatic.com/9178c2e0a09e4c26e2b4fde6de3b38ed.jpeg");
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(list);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    public class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).centerCrop().into(imageView);
        }
    }

    @Override
    public void loadData() {
        OkHttpUtils.get()
                .url("http://c.m.163.com/nc/article/headline/T1348647853363/0-40.html")
                .build().
                execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.showToast(getActivity(),e.getMessage().toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson= new Gson();
                       NewsList newsList = gson.fromJson(response, NewsList.class);
                       data.clear();
                       data.addAll(newsList.getT1348647853363());
                       adapter.notifyDataSetChanged();
                    }
                });
    }
}
