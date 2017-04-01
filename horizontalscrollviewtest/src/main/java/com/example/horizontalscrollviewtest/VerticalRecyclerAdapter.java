package com.example.horizontalscrollviewtest;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

/**
 * Created by yq895943339 on 2017/3/29.
 */

public class VerticalRecyclerAdapter extends BaseAdapter<List<String>,List<String>> {
    private Context context;

    public VerticalRecyclerAdapter(Context context, List<List<String>> datas, List<List<String>> datas1, int[] layoutIds) {
        super(context, datas, datas1, layoutIds);
        this.context = context;
    }

//    @Override
//    public void setItemData(BaseViewHolder holder, String data, int itemType) {
//
//    }
//
//    @Override
//    public void setItemDataHeader(BaseViewHolder holder, String data, int itemType) {
//
//    }
//
//    /**
//     *
//     * @param context
//     * @param datas  头布局的数据
//     * @param datas1 剩余部分的数据
//     * @param layoutIds
//     */
//    public VerticalRecyclerAdapter(Context context, List<String> datas, List<String> datas1, int[] layoutIds) {
//        super(context, datas, datas1, layoutIds);
//        this.context = context;
//
//        Log.e(TAG, "setItemData: "+"VerticalRecyclerAdapter" );
//        Log.e(TAG, "setItemData: "+"datas.size()" +datas.size());
//        Log.e(TAG, "setItemData: "+"datas1.size()"+datas1.size() );
//    }
//
//    @Override
//    public void setItemData(BaseViewHolder holder, String data, int itemType) {
//        Log.e(TAG, "setItemData: "+"进入内容布局" +data);
//    }
//
//    @Override
//    public void setItemDataHeader(BaseViewHolder holder, String data, int itemType) {
//        Log.e(TAG, "setItemDataHeader: "+"进入头布局"+data );
//    }

//    @Override
//    public void setItemData(BaseViewHolder holder, List<String> data, int itemType) {
//
//    }
//
//    @Override
//    public void setItemDataHeader(BaseViewHolder holder, List<String> data, int itemType) {
//
//    }

    @Override
    public void setItemData(BaseViewHolder holder, List<String> data, int itemType) {
//        holder.setText(R.id.tv,data);
        Log.e(TAG, "setItemData: "+"进入内容布局" );
        RecyclerView from_line = (RecyclerView)holder.getViewById(R.id.from_line);
        from_line.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        HorizontalRecyclerAdapter adapter = new HorizontalRecyclerAdapter(context,data);
        from_line.setAdapter(adapter);
    }

    @Override
    public void setItemDataHeader(BaseViewHolder holder, List<String> data, int itemType) {
        Log.e(TAG, "setItemDataHeader: "+"进入头布局" );
        RecyclerView from_line = (RecyclerView)holder.getViewById(R.id.from_line);
        from_line.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        HorizontalRecyclerAdapter adapter = new HorizontalRecyclerAdapter(context,data);
        from_line.setAdapter(adapter);

    }
}
