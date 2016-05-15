package com.example.administrator.pulltorefresh;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, PullToRefreshBase.OnRefreshListener {

    private PullToRefreshListView pullToRefreshListView;
    private SlidingMenu slidingMenu;
    private MyAdapter myAdapter;
    private List<String> mData;
    private ImageView ivMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();
        initData();
    }

    private void initUi(){
        pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_to_refresh_listview);
        ivMenu = (ImageView) findViewById(R.id.sildingmenu);
        slidingMenu = new SlidingMenu(this);
    }

    private void initData(){
        pullToRefreshListView.setOnRefreshListener(this);
        ivMenu.setOnClickListener(this);
        mData = new ArrayList<>();
        mData.add("111");
        mData.add("222");
        myAdapter = new MyAdapter(this, mData);
        pullToRefreshListView.setAdapter(myAdapter);

        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setTouchModeAbove(SlidingMenu.LEFT);
        slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        slidingMenu.setBehindWidth(dm.widthPixels / 3);
        slidingMenu.setFadeDegree(0.35f);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setMenu(R.layout.layout_left_menu);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sildingmenu:
                slidingMenu.showMenu();
                break;
        }
    }

    @Override
    public void onRefresh(PullToRefreshBase refreshView) {
        new GetDataTask().execute();
    }

    private class GetDataTask extends AsyncTask<Void, Void, String[]>{

        @Override
        protected String[] doInBackground(Void... params) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new String[0];
        }

        @Override
        protected void onPostExecute(String[] strings) {
            super.onPostExecute(strings);
            pullToRefreshListView.onRefreshComplete();
        }
    }
}
