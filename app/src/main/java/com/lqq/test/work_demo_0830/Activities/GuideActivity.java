package com.lqq.test.work_demo_0830.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lqq.test.work_demo_0830.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_guide)
public class GuideActivity extends Activity {

    @ViewInject(R.id.iv_dot01)
    private ImageView iv_1;
    @ViewInject(R.id.iv_dot02)
    private ImageView iv_2;
    @ViewInject(R.id.iv_dot03)
    private ImageView iv_3;
    @ViewInject(R.id.viewpager)
    private ViewPager vp;
    @ViewInject(R.id.tv_start)
    private TextView tv_start;

    private List<View> pageView = new ArrayList<View>();
    private PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_guide);

        x.view().inject(this);

        initView();

    }

    private void initView() {

        adapter = new MyAdapter();

        initDate();
        vp.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        vp.setPageMargin(2);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                iv_1.setImageResource(R.drawable.page_indicator_focused);
                iv_2.setImageResource(R.drawable.page_indicator_focused);
                iv_3.setImageResource(R.drawable.page_indicator_focused);
                if (position == 0){
                    iv_1.setImageResource(R.drawable.page_indicator_unfocused);
                }else if (position == 1){
                    iv_2.setImageResource(R.drawable.page_indicator_unfocused);
                }else if (position == 2){
                    iv_3.setImageResource(R.drawable.page_indicator_unfocused);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Intent it = new Intent(GuideActivity.this,LoginActivity.class);
                            startActivity(it);
                            finish();

                        }
                    }, 1000);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initDate() {

        LayoutInflater inflater = LayoutInflater.from(this);

        View view01 = inflater.inflate(R.layout.guide_view_item01, null);
        View view02 = inflater.inflate(R.layout.guide_view_item01, null);
        view02.setBackgroundResource(R.drawable.guide_2);
        View view03 = inflater.inflate(R.layout.guide_view_item03, null);

//        tv_start = (TextView) findViewById(R.id.tv_start);
//        tv_start.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(GuideActivity.this,"=========",Toast.LENGTH_SHORT).show();
//            }
//        });

        pageView.add(view01);
        pageView.add(view02);
        pageView.add(view03);

    }


    private class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return pageView == null ? 0 : pageView.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView(pageView.get(position));
        }

        @Override
        public Object instantiateItem(View container, int position) {
            ((ViewPager) container).addView(pageView.get(position));
            return pageView.get(position);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        finish();
    }
}
