package com.example.horizontalscrollviewtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yq895943339 on 2017/3/29.
 */

public class HorizontalRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private List<String> dataTitleList;
    private List<List<String>> dataContentList;
    private Context context;

    public HorizontalRecyclerAdapter(Context context, List<String> datas) {
        if (datas == null) {
            datas = new ArrayList<>();
        }
        this.dataTitleList = datas;
        this.context = context;
    }
    public HorizontalRecyclerAdapter(Context context, List<String> datas,List<List<String>> datas1) {
        if (datas == null) {
            datas = new ArrayList<>();
        }
        if (datas1 == null) {
            datas1 = new ArrayList<>();
        }
        this.dataTitleList = datas;
        this.dataContentList = datas1;
        this.context = context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.layout_content_item,parent,false);
         BaseViewHolder holder = new BaseViewHolder(itemView,context);
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        Log.e("setItemDataHeader水平布局","" );
        holder.setText(R.id.tv,dataTitleList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataTitleList.size();
    }

//    public static class VH extends RecyclerView.ViewHolder {
//        TextView mTextView;
////        TextView mDescTextView;
//
//        public VH(View itemView) {
//            super(itemView);
//            mTextView = (TextView) itemView.findViewById(R.id.tv);
////            mDescTextView = (TextView) itemView.findViewById(android.R.id.text2);
//        }
//    }
}
