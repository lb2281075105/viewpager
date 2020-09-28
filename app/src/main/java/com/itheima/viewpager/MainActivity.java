package com.itheima.viewpager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager viewpager;
    private ArrayList<ImageView> imageViewArrayList;
    private LinearLayout ll_point_container;
    private String[] contentDescs;
    private TextView tv_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        // model
        initData();

        // controller
        initAdapter();

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) { // 新的条目被选中
        tv_desc.setText(contentDescs[position]);
        for (int i = 0;i < ll_point_container.getChildCount();i++){
            View childAt = ll_point_container.getChildAt(i);
            childAt.setEnabled(false);
        }
        View child = ll_point_container.getChildAt(position);
        child.setEnabled(true);
        Log.e("HHH",position+"");
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void initData() {
        int[] imageRes = new int[]{R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e};

        imageViewArrayList = new ArrayList<>();
        // 文本描述
        contentDescs = new String[]{
                "巩俐不低俗，我就不能低俗",
                "扑树又回来啦！再唱经典老歌引万人大合唱",
                "揭秘北京电影如何升级",
                "乐视网TV版大派送",
                "热血屌丝的反杀"
        };

        ImageView imageView;
        View pointView;
        LayoutParams layoutParams;
        for (int i = 0;i < imageRes.length;i++){
            imageView = new ImageView(this);
            imageView.setBackgroundResource(imageRes[i]);
            imageViewArrayList.add(imageView);

            pointView = new View(this);
            layoutParams = new LayoutParams(15, 15);
            if (i != 0)
                layoutParams.leftMargin = 10;
            pointView.setEnabled(false);
            pointView.setBackgroundResource(R.drawable.selector_bg_point);
            ll_point_container.addView(pointView,layoutParams);
        }

    }

    private void initAdapter() {
        viewpager.setAdapter(new MyAdapter());
    }


    private void initUI() {
        viewpager = findViewById(R.id.viewpager);

        tv_desc = findViewById(R.id.tv_desc);
        ll_point_container = findViewById(R.id.ll_point_container);
        viewpager.setOnPageChangeListener(this); //滚动监听
        ll_point_container.getChildAt(0).setEnabled(true);
        tv_desc.setText(contentDescs[0]);
    }

    class MyAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return imageViewArrayList.size();
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = imageViewArrayList.get(position);
            container.addView(imageView);

            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    }
}