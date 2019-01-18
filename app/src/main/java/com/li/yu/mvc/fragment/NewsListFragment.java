package com.li.yu.mvc.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.li.yu.mvc.R;
import com.li.yu.mvc.activity.WebActivity;
import com.li.yu.mvc.adapter.NewListAdapter;
import com.li.yu.mvc.base.BaseFragment;
import com.li.yu.mvc.entity.NewsItem;
import com.li.yu.mvc.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

public class NewsListFragment extends BaseFragment {


    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    private String type;
    private String channelId;
    private String requestUrl;
    private List<NewsItem> mData = new ArrayList<>();
    private NewListAdapter adapter;

    public static NewsListFragment getInstance(String parms) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("parms", parms);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_new_list;
    }

    @Override
    protected void initEventAndData() {
        type = getArguments().getString("parms", "头条");
        requestUrl = getUrlByType(type);
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setFocusable(false);
        adapter = new NewListAdapter(R.layout.item_list_index, mData);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            NewsItem item = (NewsItem) adapter.getItem(position);
            if (!TextUtils.isEmpty(item.getUrl())) {
                WebActivity.start(getActivity(), item.getUrl());
            }else{
                ToastUtils.showToast(getActivity(),"数据丢失了！");
            }
        });
    }

    private String getUrlByType(String type) {
        String url = null;
        switch (type) {
            case "头条":
                channelId = "T1348647853363";
                url = "http://c.m.163.com/nc/article/headline/" + channelId + "/0-20.html";
                break;
            case "精选":
                channelId = "T1467284926140";
                url = "http://c.3g.163.com/nc/article/list/" + channelId + "/0-20.html";
                break;
            case "娱乐":
                channelId = "T1348648517839";
                url = "http://c.3g.163.com/nc/article/list/" + channelId + "/0-20.html";
                break;
            case "汽车":
                channelId = "5bmz6aG25bGx";
                url = "http://c.m.163.com/nc/auto/list/" + channelId + "/0-20.html";
                break;
            case "运动":
                channelId = "T1348649079062";
                url = "http://c.3g.163.com/nc/article/list/" + channelId + "/0-20.html";
                break;
        }
        return url;
    }

    @Override
    public void loadData() {
        OkHttpUtils.get().url(requestUrl).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtils.showToast(getActivity(), e.getMessage().toString());
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.optJSONArray(channelId);
                    Gson gson = new Gson();
                    if (jsonArray != null) {
                        Type type = new TypeToken<List<NewsItem>>() {
                        }.getType();
                        List<NewsItem> list = gson.fromJson(jsonArray.toString(), type);
                        mData.clear();
                        mData.addAll(list);
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
