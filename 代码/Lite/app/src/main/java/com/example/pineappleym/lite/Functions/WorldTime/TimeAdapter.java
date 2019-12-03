package com.example.pineappleym.lite.Functions.WorldTime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pineappleym.lite.R;

import java.util.ArrayList;

public class TimeAdapter extends BaseAdapter {
    private ArrayList<City> mList;
    private Context mContext;
    //MyAdapter需要一个Context，通过Context获得Layout.inflater，然后通过inflater加载item的布局
    public TimeAdapter(Context context, ArrayList<City> citys) {
        mList = citys;
        mContext = context;
    }

    //返回数据集的长度
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //这个方法才是重点，我们要为它编写一个ViewHolder
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.time_card, null);
            viewHolder.city_name = view.findViewById(R.id.city_name);
            viewHolder.city_area = view.findViewById(R.id.city_area);
            viewHolder.city_time = view.findViewById(R.id.city_time);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.city_name.setText(mList.get(i).getName());
        viewHolder.city_area.setText(mList.get(i).getArea());
        TimeThread timeThread = new TimeThread(viewHolder.city_time,mList.get(i).getArea(),30000);
        timeThread.start();

        return view;
    }


    public void setmList( ArrayList<City> citys) {
        mList = citys;
    }

    //这个ViewHolder只能服务于当前这个特定的adapter，因为ViewHolder里会指定item的控件，不同的ListView，item可能不同，所以ViewHolder写成一个私有的类
    private class ViewHolder {
        Context context;
        TextView city_name;
        TextView city_time;
        TextView city_area;
    }
}
