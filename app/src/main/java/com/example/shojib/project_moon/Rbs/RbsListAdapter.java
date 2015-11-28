package com.example.shojib.project_moon.Rbs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.shojib.project_moon.R;

import java.util.List;

public class RbsListAdapter extends BaseAdapter {

    private List<RbsModule> mItems;
    private LayoutInflater mLayoutInflater;


    public RbsListAdapter(Context mContex, List<RbsModule> moduleList) {
        this.mLayoutInflater = LayoutInflater.from(mContex);
        this.mItems = moduleList;

    }
    @Override
    public int getCount() {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().size() : 0 ;
    }

    @Override
    public RbsModule getItem(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position) : null ;
    }

    @Override
    public long getItemId(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position).getRbsId() : position;
    }
    public List<RbsModule> getItems()
    {
        return mItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=convertView;
        ViewHolder holder;
        if(v == null) {
            v = mLayoutInflater.inflate(R.layout.list_rbs_helper_xml, parent, false);
            holder = new ViewHolder();
            holder.tvRName = (TextView) v.findViewById(R.id.txt_rbs_title);

            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }
       RbsModule currentItem=getItem(position);
        if(currentItem!=null)
        {
            holder.tvRName.setText("RBS Unit: "+currentItem.getRbsUnit()+" -Date: "+currentItem.getRbsDate()+" -Time: "+currentItem.getRbsTime());
        }
        return v;




    }
     class ViewHolder
    {
        TextView tvRName;
    }
}
