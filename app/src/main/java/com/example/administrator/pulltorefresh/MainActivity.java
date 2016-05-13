package com.example.administrator.pulltorefresh;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class MainActivity extends AppCompatActivity  {

    private PullToRefreshListView pullToRefreshListView;
    private MyAdapter myAdapter;
    private List<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_to_refresh_listview);
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                Log.v("aaaa", "aaaa");
                new GetDataTask().execute();
            }
        });

        mData = new ArrayList<>();
        mData.add("111");
        mData.add("222");
        myAdapter = new MyAdapter(this, mData);
        pullToRefreshListView.setAdapter(myAdapter);

    }

    private class GetDataTask extends AsyncTask<Void, Void, String[]>{

        @Override
        protected String[] doInBackground(Void... params) {
            try {
                Log.v("aaaa","in");
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
