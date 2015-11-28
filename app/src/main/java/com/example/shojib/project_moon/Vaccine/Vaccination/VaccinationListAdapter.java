package com.example.shojib.project_moon.Vaccine.Vaccination;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.shojib.project_moon.R;

import java.util.List;

public class VaccinationListAdapter extends BaseAdapter {

    private List<VaccinationModule> mItems;
    private LayoutInflater mLayoutInflater;


    public VaccinationListAdapter(Context mContex, List<VaccinationModule> moduleList) {
        this.mLayoutInflater = LayoutInflater.from(mContex);
        this.mItems = moduleList;

    }
    @Override
    public int getCount() {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().size() : 0 ;
    }

    @Override
    public VaccinationModule getItem(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position) : null ;
    }

    @Override
    public long getItemId(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position).getUserId() : position;
    }
    public List<VaccinationModule> getItems()
    {
        return mItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=convertView;
        ViewHolder holder;
        if(v == null) {
            v = mLayoutInflater.inflate(R.layout.list_vaccine_helper_xml, parent, false);
            holder = new ViewHolder();
            holder.tvVName = (TextView) v.findViewById(R.id.txt_vaccine_title);

            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }
       VaccinationModule currentItem=getItem(position);
        if(currentItem!=null)
        {
            holder.tvVName.setText(currentItem.getVaccineName()+" \n"+currentItem.getVaccineReason()+" \n"+currentItem.getReminder());
        }
        return v;




    }
     class ViewHolder
    {
        TextView tvVName;
    }
}
