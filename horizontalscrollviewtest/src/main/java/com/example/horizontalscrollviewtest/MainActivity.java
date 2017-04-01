package com.example.horizontalscrollviewtest;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getName();
    @BindView(R.id.ll)
    AbsoluteLayout ll;
    @BindView(R.id.hv)
    HorizontalScrollView hv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        List<String> titles = new ArrayList<>();

        List<List<String>> lists = new ArrayList<>();
        View view = LayoutInflater.from(this).inflate(R.layout.layout1, null, false);
//        TextView rl = (TextView) view.findViewById(R.id.tv);
        RecyclerView rl = (RecyclerView) view.findViewById(R.id.rl);
//        LinearLayout lll = (LinearLayout) view.findViewById(R.id.lll);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(306*2, 223*2);
        view.setLayoutParams(layoutParams);
        view.setBackgroundColor(Color.GRAY);
//        HorizontalScrollView.LayoutParams layoutParams1 = new HorizontalScrollView.LayoutParams(2000, 2000);
//        lll.setLayoutParams(layoutParams);
//        view.setBackgroundColor(Color.GRAY);

        for (int i = 0; i < 10; i++) {
            titles.add("呵呵 "+i);
//            titles.add(drawPic.getJGridData().get(i).getColumn());
//            lists.add(drawPic.getJGridData().get(i).getData());
//            lists.add(drawPic.getJGridData().get(i).getData());
//            Log.e(TAG, "initDataGridView: "+drawPic.getJGridData().get(i).getColumn() );
//            Log.e(TAG, "initDataGridView: "+drawPic.getJColumns().get(i) );
//
//
//            Log.e(TAG, "initDataGridView: "+drawPic.getSelect() );

        }
        List<List<String>> titleData = new ArrayList<>();
        titleData.add(titles);
//        String jGridData = drawPic.getJDataSource();
//        Log.e(TAG, "initDataGridView: "+jGridData.toString() );
//        JSONArray array = JSON.parseArray(jGridData);
//        Log.e(TAG, "initDataGridView: "+array.size() );
        List<String> strings = null;
        for (int j = 0; j < 30; j++) {
            strings = new ArrayList<>();
            for (int i = 0; i < 10; i++) {

//                JSONObject jsonObject = JSON.parseObject(array.get(j).toString());
//                String content = jsonObject.getString(drawPic.getJColumns().get(i));
                String content = "内容  "+i;
                Log.e(TAG, "initDataGridView:strings.size()  "+content );
                strings.add(content);
            }
            Log.e(TAG, "initDataGridView:strings.size()  "+strings.size() );
            lists.add(strings);
        }

        Log.e(TAG, "initDataGridView:titles.size() : "+titles.size() );
//        Log.e(TAG, "initDataGridView: "+strings.size() );
        Log.e(TAG, "initDataGridView: lists.size():"+lists.size() );
        rl.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        Log.e(TAG, "initDataGridView: 1:" );
        VerticalRecyclerAdapter adapter = new VerticalRecyclerAdapter(this,titleData,lists,new int[]{R.layout.layout_recycler,R.layout.layout_recycler});
        Log.e(TAG, "initDataGridView: 2:"+adapter );
        View view1 = LayoutInflater.from(this).inflate(R.layout.layout_recycler, null);
        Log.e(TAG, "initDataGridView: 3:"+view1 );
        adapter.addHeaderView(view);
        rl.setAdapter(adapter);
        AbsoluteLayout.LayoutParams params = new AbsoluteLayout.LayoutParams(306*2, 223*2, 147*2, 208*2);
        ll.addView(view, params);
        hv.requestDisallowInterceptTouchEvent(true);

    }
}
