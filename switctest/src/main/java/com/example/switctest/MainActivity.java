package com.example.switctest;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.ll)
    RelativeLayout ll;
    @BindView(R.id.tv_r_off)
    TextView tvROff;
    @BindView(R.id.r_off)
    AbsoluteLayout rOff;
    @BindView(R.id.tv_r_on)
    TextView tvROn;
    @BindView(R.id.r_on)
    AbsoluteLayout rOn;
    @BindView(R.id.ll_update_bg_color_off)
    LinearLayout llUpdateBgColorOff;
    @BindView(R.id.ll_update_bg_color_on)
    LinearLayout llUpdateBgColorOn;
    private String tv_R_Off_Color = "#2D7DC0";
    private String tv_R_On_Color = "#3CB134";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_r_switch);
        ButterKnife.bind(this);
        ll.setBackgroundResource(R.color.spadvicec_color);
    }

    @OnClick({R.id.r_off, R.id.r_on})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.r_off:
//                tvROn.setBackgroundColor(Color.RED);
                GradientDrawable drawable = new GradientDrawable();
                drawable.setCornerRadius(dip2px(this,9));
                drawable.setStroke(dip2px(this,1.4f), Color.parseColor("#7E7E7F"));
                drawable.setColor(Color.parseColor("#E5E5E3"));
                llUpdateBgColorOn.setBackgroundDrawable(drawable);
                tvROn.setTextColor(Color.parseColor(tv_R_On_Color));
                rOn.setVisibility(View.VISIBLE);
                rOff.setVisibility(View.GONE);
                break;
            case R.id.r_on:
//                llUpdateBgColorOn.setBackgroundColor(Color.GREEN);
                GradientDrawable drawable1 = new GradientDrawable();
                drawable1.setCornerRadius(dip2px(this,9));
                drawable1.setStroke(dip2px(this,1.4f), Color.parseColor("#7E7E7F"));
                drawable1.setColor(Color.parseColor("#E5E5E3"));
                llUpdateBgColorOff.setBackgroundDrawable(drawable1);
                tvROff.setTextColor(Color.parseColor(tv_R_Off_Color));
                rOff.setVisibility(View.VISIBLE);
                rOn.setVisibility(View.GONE);
                break;
        }
    }
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
