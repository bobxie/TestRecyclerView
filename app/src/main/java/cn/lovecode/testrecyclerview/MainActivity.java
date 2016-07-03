package cn.lovecode.testrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.OvershootInterpolator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.lovecode.testrecyclerview.adpter.MyAdapter;
import cn.lovecode.testrecyclerview.animators.SlideInLeftAnimator;
import cn.lovecode.testrecyclerview.bean.User;

public class MainActivity extends AppCompatActivity {
    private List<User> users = new ArrayList<>();

    private RecyclerView mRecyclerView;

    private final String[] imageUrlArray = {
            "http://o6p4e1uhv.bkt.clouddn.com/tu24967_22.jpg",
            "http://o6p4e1uhv.bkt.clouddn.com/cce4b48f8c5494ee7008eb9528f5e0fe98257eca.jpg",
            "http://o6p4e1uhv.bkt.clouddn.com/tu25422_4.jpg",
            "http://o6p4e1uhv.bkt.clouddn.com/ad7fd688d43f879424026deed01b0ef41ad53a2e.jpg",
            "http://o6p4e1uhv.bkt.clouddn.com/tu25422_6.jpg",
            "http://o6p4e1uhv.bkt.clouddn.com/refsdg.jpg",
            "http://o6p4e1uhv.bkt.clouddn.com/ggfnvbn.jpg",
            "http://o6p4e1uhv.bkt.clouddn.com/sghgfsh.jpg",
            "http://o6p4e1uhv.bkt.clouddn.com/asfasdf.jpg",
            "http://o6p4e1uhv.bkt.clouddn.com/fsbgbsfg.jpg",
            "http://o6p4e1uhv.bkt.clouddn.com/gsfdtrysrtsfdg.jpg",
            "http://o6p4e1uhv.bkt.clouddn.com/sdfgsrters.jpg"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // 1. 这里用线性显示,类似于ListView
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 2. 这里用线性宫格显示,类似于GridView
//      mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        //默认动画效果
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.getItemAnimator().setAddDuration(1000);
        mRecyclerView.getItemAnimator().setRemoveDuration(1000);
        mRecyclerView.getItemAnimator().setMoveDuration(1000);
        mRecyclerView.getItemAnimator().setChangeDuration(1000);

        SlideInLeftAnimator animator = new SlideInLeftAnimator();
        animator.setInterpolator(new OvershootInterpolator());
        // or recyclerView.setItemAnimator(new SlideInUpAnimator(new OvershootInterpolator(1f));
        mRecyclerView.setItemAnimator(animator);
        // 3. 这里用线性宫格显示,类似于瀑布流
//      mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL));

        for (int i = 0; i < 100; i++) {
            Random rd = new Random();
            int number =  rd.nextInt(6);
            User user = new User();
            user.setName("张三");
            user.setPwd("123456");
            user.setUrl(imageUrlArray[number]);
            users.add(user);
        }
        MyAdapter mAdapter = new MyAdapter(this,users);
        mRecyclerView.setAdapter(mAdapter);
        //可以提高效率
        mRecyclerView.setHasFixedSize(true);
    }
}
