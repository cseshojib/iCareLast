package com.example.shojib.project_moon.Doctor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.shojib.project_moon.R;


import java.util.List;


public class DoctorListAdapter extends BaseAdapter {

    private List<DoctorModule> mItems;
    private LayoutInflater mLayoutInflater;


    public DoctorListAdapter(Context mContex, List<DoctorModule> moduleList) {
        this.mLayoutInflater = LayoutInflater.from(mContex);
        this.mItems = moduleList;

    }
    @Override
    public int getCount() {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().size() : 0 ;
    }

    @Override
    public DoctorModule getItem(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position) : null ;
    }

    @Override
    public long getItemId(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position).getUserId() : position;
    }
    public List<DoctorModule> getItems()
    {
        return mItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=convertView;
        ViewHolder holder;
        if(v == null) {
            v = mLayoutInflater.inflate(R.layout.list_doctors_helper_xml, parent, false);
            holder = new ViewHolder();
            holder.tvDName = (TextView) v.findViewById(R.id.txt_doctors_title);

            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }
        DoctorModule currentItem=getItem(position);
        if(currentItem!=null)
        {
            // holder.tvPName.setText(currentItem.getProfileName());
            holder.tvDName.setText(currentItem.getDoctorName()+" \n"+currentItem.getSpeciality()+" \n"+currentItem.getPhone());
        }
        return v;




    }
    class ViewHolder
    {
        TextView tvDName;
    }
}
