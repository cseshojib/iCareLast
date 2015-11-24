package com.example.shojib.project_moon.Vaccine.VaccineDate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.shojib.project_moon.R;


import java.util.List;

/**
 * Created by Suuny on 11/23/2015.
 */
public class VaccineDateListAdapter extends BaseAdapter {

    private List<VaccineDateModule> mItems;
    private LayoutInflater mLayoutInflater;


    public VaccineDateListAdapter(Context mContex, List<VaccineDateModule> moduleList) {
        this.mLayoutInflater = LayoutInflater.from(mContex);
        this.mItems = moduleList;

    }
    @Override
    public int getCount() {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().size() : 0 ;
    }

    @Override
    public VaccineDateModule getItem(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position) : null ;
    }

    @Override
    public long getItemId(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position).getVaccineId() : position;
    }
    public List<VaccineDateModule> getItems()
    {
        return mItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=convertView;
        ViewHolder holder;
        if(v == null) {
            v = mLayoutInflater.inflate(R.layout.list_profile_helper_xml, parent, false);
            holder = new ViewHolder();
            holder.tvPName = (TextView) v.findViewById(R.id.txt_profile_title);

            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }
       VaccineDateModule currentItem=getItem(position);
        if(currentItem!=null)
        {
           // holder.tvPName.setText(currentItem.getProfileName());
        }
        return v;




    }
     class ViewHolder
    {
        TextView tvPName;
    }
}
