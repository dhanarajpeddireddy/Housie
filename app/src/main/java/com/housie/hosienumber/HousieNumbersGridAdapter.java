package com.housie.hosienumber;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class HousieNumbersGridAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Integer> randomList;
    private List<Integer> randomPickedList=new ArrayList<>();

    LayoutInflater inflater;

    public HousieNumbersGridAdapter(Context context, ArrayList<Integer> randomList){
        this.context = context;
        this.randomList = randomList;
        inflater=LayoutInflater.from(context);
    }
    public void setRandomlist( ArrayList<Integer> randomlist)
    {
        this.randomList=randomlist;
        notifyDataSetChanged();
    }
    public void setRandomPickedlist( List<Integer> randomPickedList)
    {
       this.randomPickedList=randomPickedList;
       notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        if (randomList==null)
            return 0;
        return randomList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
         view=inflater.inflate(R.layout.houisenumberitemlayout,null);
        TextView tv_housieNumberItemId = view.findViewById(R.id.tv_housieNumberItemId);
        tv_housieNumberItemId.setText(String.valueOf(randomList.get(i)));
        if (randomPickedList.contains(randomList.get(i)))
        {
            tv_housieNumberItemId.setBackgroundColor((Color.GREEN));
        }
        return view;
    }
}
