package com.example.cartest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**

 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private final Context context;
    private List<T> datas;
    private List<T> datasHeader;
    private final int[] layoutIds;
    private OnItemClickListener listener;
    private View mHeaderView;
    private int ITEM_TYPE_HEADER = 0;
    private int ITEM_TYPE_NORMAL = 1;

    /**
     * @param context
     * @param datas
     * @param layoutIds :item 布局id 支持多种类型
     */
    public BaseAdapter(Context context, List<T> datas, List<T> datas1,int[] layoutIds) {
        this.context = context;
        this.datas = datas;
        this.datasHeader = datas1;
        this.layoutIds = layoutIds;
    }
    public BaseAdapter(Context context, List<T> datas, int[] layoutIds) {
        this.context = context;
        this.datas = datas;
        this.layoutIds = layoutIds;
    }
    public BaseAdapter(Context context, int[] layoutIds) {
        this.context = context;
        this.layoutIds = layoutIds;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(layoutIds[viewType],parent,false);
        BaseViewHolder holder = new BaseViewHolder(itemView,context);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        int type = getItemViewType(position);
        if (type == ITEM_TYPE_HEADER) {
            setItemDataHeader(holder,datasHeader.get(position),getItemViewType(position));
            return;
        }
        setItemData(holder,datas.get(position-1),getItemViewType(position));
        //设置点击监听
        if(listener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(view,position);
                }
            });
        }


    }

//    @Override
//    public int getItemCount() {
//        return datas == null ? 0 : datas.size();
//    }
@Override
public int getItemCount() {
    int itemCount = datas.size();
    if (null != mHeaderView) {
        itemCount++;
    }

    return itemCount;
}

    public void addHeaderView(View view) {
        mHeaderView = view;
        notifyItemInserted(0);
    }
    @Override
    public int getItemViewType(int position) {
        if (null != mHeaderView && position == 0) {
            return ITEM_TYPE_HEADER;
        }

        return ITEM_TYPE_NORMAL;
    }

    /**
     * 设置item数据
     * @param holder
     * @param data
     * @param itemType ：item对应的数据类型
     */
    public abstract void setItemData(BaseViewHolder holder,T data,int itemType);
    public abstract void setItemDataHeader(BaseViewHolder holder,T data,int itemType);

    /** item点击监听接口 */
    public interface OnItemClickListener{

        /**
         * @param view
         * @param position
         */
        void onItemClick(View view, int position);
    }

    /**
     * 设置item点击监听
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }


//    public void setData(List<T> data){
//        datas = data;
//        notifyDataSetChanged();
//    }
//    public void setDatasHeader(List<T> data){
//        datasHeader = data;
//        notifyDataSetChanged();
//    }
    /**
     * 添加数据
     * @param data
     */
    public void addData(List<T> data){
        datas.addAll(data);
        notifyDataSetChanged();
    }
    public void addDataHeader(List<T> data){
        datasHeader.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 更新数据
     * @param data
     */
    public void updateData(List<T> data){
        datas.clear();
        datas.addAll(data);
        notifyDataSetChanged();
    }
    public void updateDataHeader(List<T> data){
        datasHeader.clear();
        datasHeader.addAll(data);
        notifyDataSetChanged();
    }
}
