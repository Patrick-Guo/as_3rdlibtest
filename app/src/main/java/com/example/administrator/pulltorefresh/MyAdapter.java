package com.example.administrator.pulltorefresh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Patrick on 16/5/13.
 */
public class MyAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private Context context;
    private List<String> mData;

    MyAdapter(Context context, List<String> mData){
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.item, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.tv);
        String s = mData.get(position);
        if (s != null){
            tv.setText(s);
        }
        return convertView;
    }
}
