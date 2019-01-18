package com.li.yu.mvc.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.li.yu.mvc.R;
import com.li.yu.mvc.entity.NewsList;

import java.util.List;


public class IndexListAdapter extends BaseQuickAdapter<NewsList.ItemBean,BaseViewHolder>{
    private Context mContext;
    public IndexListAdapter(int layoutResId, @Nullable List<NewsList.ItemBean> data, Context context) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsList.ItemBean item) {
        if(!TextUtils.isEmpty(item.getImgsrc())){
            Glide.with(mContext).load(item.getImgsrc()).into((ImageView) helper.getView(R.id.iv_news));
        }else{
            Glide.with(mContext).load(R.mipmap.bg_default).into((ImageView) helper.getView(R.id.iv_news));
        }
        if(!TextUtils.isEmpty(item.getTitle())){
            helper.setText(R.id.tv_title,item.getTitle());
        }
        helper.setText(R.id.tv_see_count,"阅读 "+item.getVotecount());
        helper.setText(R.id.tv_reply_count,"回复 "+item.getReplyCount());

    }
}
