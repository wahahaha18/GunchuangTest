package com.example.cartest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by yq895943339 on 2017/3/27.
 */

public class AccsessPostAdapter extends BaseAdapter<Post.BBean> {

    public AccsessPostAdapter(Context context, List<Post.BBean> datas, List<Post.BBean> datas1, int[] layoutIds) {
        super(context, datas, datas1, layoutIds);
    }

//    /**
//     * @param context
//     * @param datas
//     * @param layoutIds :item 布局id 支持多种类型
//     */



    @Override
    public void setItemData(BaseViewHolder holder, Post.BBean data, int itemType) {

    }

    @Override
    public void setItemDataHeader(BaseViewHolder holder, Post.BBean data, int itemType) {

    }
}
