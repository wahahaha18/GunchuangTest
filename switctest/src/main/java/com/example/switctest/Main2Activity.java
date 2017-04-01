package com.example.switctest;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.tv_r_off)
    TextView tvROff;
    @BindView(R.id.ll_update_bg_color_off)
    LinearLayout llUpdateBgColorOff;
    @BindView(R.id.r_off)
    LinearLayout rOff;
    @BindView(R.id.tv_r_on)
    TextView tvROn;
    @BindView(R.id.ll_update_bg_color_on)
    LinearLayout llUpdateBgColorOn;
    @BindView(R.id.r_on)
    LinearLayout rOn;
    @BindView(R.id.ll)
    RelativeLayout ll;
    private String tv_R_Off_Color = "#818181";
    private String tv_R_On_Color = "#ffffff";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_c_switch);
        ButterKnife.bind(this);
        rOff.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                GradientDrawable drawable = new GradientDrawable();
                drawable.setCornerRadius(dip2px(Main2Activity.this,99));
                drawable.setStroke(dip2px(Main2Activity.this,1.4f), Color.parseColor("#7E7E7F"));
                drawable.setColor(Color.rgb((15066595 & 0xff0000) >> 16, (15066595 & 0x00ff00) >> 8, (15066595 & 0x0000ff)));
                llUpdateBgColorOn.setBackgroundDrawable(drawable);
                tvROn.setTextColor(Color.rgb((3977524 & 0xff0000) >> 16, (3977524 & 0x00ff00) >> 8, (3977524 & 0x0000ff)));
                rOn.setVisibility(View.VISIBLE);
                rOff.setVisibility(View.GONE);
                return true;
            }
        });
        rOn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                GradientDrawable drawable1 = new GradientDrawable();
                drawable1.setCornerRadius(dip2px(Main2Activity.this,99));
                drawable1.setStroke(dip2px(Main2Activity.this,1.4f), Color.parseColor("#7E7E7F"));
                drawable1.setColor(Color.rgb((15066595 & 0xff0000) >> 16, (15066595 & 0x00ff00) >> 8, (15066595 & 0x0000ff)));
                llUpdateBgColorOff.setBackgroundDrawable(drawable1);
                Log.e("................", "onClick: "+Color.rgb((2521791 & 0xff0000) >> 16, (2521791 & 0x00ff00) >> 8, (2521791 & 0x0000ff)));
                tvROff.setTextColor(Color.rgb((2521791 & 0xff0000) >> 16, (2521791 & 0x00ff00) >> 8, (2521791 & 0x0000ff)));
                rOff.setVisibility(View.VISIBLE);
                rOn.setVisibility(View.GONE);
                return true;
            }
        });
    }

//    @OnClick({R.id.r_off, R.id.r_on})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.r_off:
//                GradientDrawable drawable = new GradientDrawable();
//                drawable.setCornerRadius(dip2px(this,99));
//                drawable.setStroke(dip2px(this,1.4f), Color.parseColor("#7E7E7F"));
//                drawable.setColor(Color.rgb((15066595 & 0xff0000) >> 16, (15066595 & 0x00ff00) >> 8, (15066595 & 0x0000ff)));
//                llUpdateBgColorOn.setBackgroundDrawable(drawable);
//                tvROn.setTextColor(Color.rgb((3977524 & 0xff0000) >> 16, (3977524 & 0x00ff00) >> 8, (3977524 & 0x0000ff)));
//                rOn.setVisibility(View.VISIBLE);
//                rOff.setVisibility(View.GONE);
//                break;
//            case R.id.r_on:
//                GradientDrawable drawable1 = new GradientDrawable();
//                drawable1.setCornerRadius(dip2px(this,99));
//                drawable1.setStroke(dip2px(this,1.4f), Color.parseColor("#7E7E7F"));
//                drawable1.setColor(Color.rgb((15066595 & 0xff0000) >> 16, (15066595 & 0x00ff00) >> 8, (15066595 & 0x0000ff)));
//                llUpdateBgColorOff.setBackgroundDrawable(drawable1);
//                Log.e("................", "onClick: "+Color.rgb((2521791 & 0xff0000) >> 16, (2521791 & 0x00ff00) >> 8, (2521791 & 0x0000ff)));
//                tvROff.setTextColor(Color.rgb((2521791 & 0xff0000) >> 16, (2521791 & 0x00ff00) >> 8, (2521791 & 0x0000ff)));
//                rOff.setVisibility(View.VISIBLE);
//                rOn.setVisibility(View.GONE);
//                break;
//        }
//    }
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
