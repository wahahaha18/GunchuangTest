package com.example.cartest;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getName();
    @BindView(R.id.rv_access_post)
    RecyclerView rvAccessPost;
    @BindView(R.id.post_swipe_refresh)
    SwipeRefreshLayout postSwipeRefresh;
    @BindView(R.id.ll_post_comment)
    LinearLayout llPostComment;
    AccsessPostAdapter accsessPostAdapter;
    private List<Post.BBean> datas = new ArrayList<>();
    private List<Post.BBean> datasHeader = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_access_post);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        datas.add(new Post.BBean());
        datas.add(new Post.BBean());
        datas.add(new Post.BBean());
        datas.add(new Post.BBean());
        datasHeader.add(new Post.BBean());
        Log.e(TAG, "initView: datasHeader.size():"+datasHeader.size() );
        Log.e(TAG, "initView: datas.size():"+datas.size() );
        accsessPostAdapter = new AccsessPostAdapter(this,datas,datasHeader,new int[]{R.layout.layout_post_content,R.layout.layout_post_comment_item});
        rvAccessPost.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        accsessPostAdapter.addHeaderView(LayoutInflater.from(this).inflate(R.layout.layout_post_content,rvAccessPost,false));
        rvAccessPost.setAdapter(accsessPostAdapter);
        List<Post.BBean> a = new ArrayList<>();
        a.add(new Post.BBean());
        a.add(new Post.BBean());
        a.add(new Post.BBean());
        a.add(new Post.BBean());


        accsessPostAdapter.addData(a);
    }
}
