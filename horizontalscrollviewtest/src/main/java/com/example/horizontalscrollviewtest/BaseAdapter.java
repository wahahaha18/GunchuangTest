package com.example.horizontalscrollviewtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**

 */
public abstract class BaseAdapter<T,E> extends RecyclerView.Adapter<BaseViewHolder> {
    public static final String TAG = BaseAdapter.class.getName();



    private final Context context;
    private List<E> datas;
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
    public BaseAdapter(Context context, List<T> datas, List<E> datas1,int[] layoutIds) {
//
//        Log.e(TAG, "BaseAdapter: ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,," );
//        Log.e(TAG, "BaseAdapter: ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,"+ datas1.size());
        Log.e(TAG, "BaseAdapter: 111111111111111111" );
        this.context = context;
        this.datas = datas1;
        this.datasHeader = datas;
        this.layoutIds = layoutIds;
        Log.e(TAG, "BaseAdapter: datasHeader.size():"+datasHeader.size());
        Log.e(TAG, "BaseAdapter: datas.size():"+this.datas.size());
    }
    public BaseAdapter(Context context, List<E> datas, int[] layoutIds) {
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
        Log.e(TAG, "onCreateViewHolder: 222222222222222222222222222222" );
        View itemView = LayoutInflater.from(context).inflate(layoutIds[viewType],parent,false);
        BaseViewHolder holder = new BaseViewHolder(itemView,context);
        Log.e(TAG, "onCreateViewHolder: "+holder );
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        Log.e(TAG, "onBindViewHolder: 3333333333333333333333333333333" );
        Log.e(TAG, "onBindViewHolder: "+position );
        int type = getItemViewType(position);
//        Log.e(TAG, "onBindViewHolder: type："+type);
//        Log.e(TAG, "onBindViewHolder: datasHeader.size():"+datasHeader.size() );
        if (type == ITEM_TYPE_HEADER) {
            setItemDataHeader(holder,datasHeader.get(position),getItemViewType(position));
            Log.e(TAG, "onBindViewHolder: "+"头布局");
            return;
        }
        Log.e(TAG, "onBindViewHolder: "+position);
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
    Log.e(TAG, "getItemCount: 4444444444444444444444444" );
    int itemCount = datas.size();
    Log.e(TAG, "BaseAdapter: ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,itemCount"+itemCount);
    if (null != mHeaderView) {
        itemCount++;
        Log.e(TAG, "BaseAdapter: ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,itemCount1"+itemCount);
    }
    Log.e(TAG, "BaseAdapter: ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,itemCount2"+itemCount);
    return itemCount;
}

    public void addHeaderView(View view) {
        mHeaderView = view;
        Log.e(TAG, "addHeaderView: "+view );
        notifyItemInserted(0);
    }
    @Override
    public int getItemViewType(int position) {
        Log.e(TAG, "getItemViewType: 55555555555555555555555555555" );
//        if (position == 0 && mHeaderView!=null) {
//            return ITEM_TYPE_HEADER;
//        }else {
//            return ITEM_TYPE_NORMAL;
//        }
        Log.e(TAG, "getItemViewType: "+position );
        if (position == 0 ) {
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
    public abstract void setItemData(BaseViewHolder holder,E data,int itemType);
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
    public void addData(List<E> data){
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
    public void updateData(List<E> data){
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
